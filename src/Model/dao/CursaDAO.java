package Model.dao;

import Model.Curse;
import Model.DatabaseConnection;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursaDAO {

    private final Connection connection;

    public CursaDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    private static final String GETINFOCURSA = "SELECT TipTransport, Capacitate FROM Curse WHERE Nume = ?";

    private static final String GETLISTACURSURIBYTRASEU = "SELECT C.Nume FROM Curse C JOIN Traseuri T ON C.TraseulID = T.TraseulID WHERE T.Nume = ?";

    private static final String GETLISTACURSURIBYDESTINATIE = "SELECT Nume FROM Curse WHERE Destinatie = ?";

    private static final String EXPORTLISTACURSESOLICITATE = "SELECT Nume, COUNT(*) AS NumarRezervari FROM Curse C JOIN Rezervari R ON C.CursaID = R.CursaID GROUP BY C.CursaID ORDER BY COUNT(*) DESC";

    private static final String GETCURSABYLOCALITATE = "SELECT Nume FROM Curse WHERE Destinatie = ?";

    private static final String GETLISTAPASAGERIBYLUNASIAN = "SELECT DISTINCT p.Nume FROM Pasageri p JOIN Rezervari r ON p.PasagerID = r.PasagerID WHERE MONTH(r.DataRezervare) = ? AND YEAR(r.DataRezervare) = ?";
    public Curse getInfoCursa(Connection connection, String numeCursa) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GETINFOCURSA)) {
            statement.setString(1, numeCursa);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String tipTransport = resultSet.getString("TipTransport");
                int capacitate = resultSet.getInt("Capacitate");
                return new Curse(tipTransport, capacitate);
            }
        }
        return null;
    }

    public String getListaCursuriByTraseu(Connection connection, String numeTraseu) throws SQLException {
        String listaCursuri = "";
        try (PreparedStatement statement = connection.prepareStatement(GETLISTACURSURIBYTRASEU)) {
            statement.setString(1, numeTraseu);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String numeCursa = resultSet.getString("Nume");
                listaCursuri += numeCursa + "\n";
            }
        }
        return listaCursuri.trim();
    }

    public String getListaCursuriByDestinatie(Connection connection, String destinatie) throws SQLException {
        String listaCursuri = "";
        try (PreparedStatement statement = connection.prepareStatement(GETLISTACURSURIBYDESTINATIE)) {
            statement.setString(1, destinatie);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String numeCursa = resultSet.getString("Nume");
                listaCursuri += numeCursa + "\n";
            }
        }
        return listaCursuri.trim();
    }


    public void exportListaCurselorSolicitate(Connection connection, String numeFisier) throws SQLException, IOException {
        try (PreparedStatement statement = connection.prepareStatement(EXPORTLISTACURSESOLICITATE);
             ResultSet resultSet = statement.executeQuery();
             FileWriter writer = new FileWriter(numeFisier)) {
            while (resultSet.next()) {
                String numeCursa = resultSet.getString("Nume");
                int numarRezervari = resultSet.getInt("NumarRezervari");
                writer.write(numeCursa + ": " + numarRezervari + "\n");
            }
        }
    }



    public List<String> getCursaByLocalitate(String localitate) throws SQLException {
        List<String> numeCursuri = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GETCURSABYLOCALITATE)) {
            statement.setString(1, localitate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    numeCursuri.add(resultSet.getString("Nume"));
                }
            }
        }
        return numeCursuri;
    }


    private Curse extractCursaFromResultSet(ResultSet resultSet) throws SQLException {
        Curse cursa = new Curse();
        cursa.setCursaID(resultSet.getInt("CursaID"));
        cursa.setNume(resultSet.getString("Nume"));
        cursa.setDestinatie(resultSet.getString("Destinatie"));
        cursa.setCapacitate(resultSet.getInt("Capacitate"));
        cursa.setTipTransport(resultSet.getString("TipTransport"));
        return cursa;
    }


    public List<String> getDestinatiiDisponibile() throws SQLException {
        List<String> destinatii = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT DISTINCT Destinatie FROM Curse")) {

            while (resultSet.next()) {
                destinatii.add(resultSet.getString("destinatie"));
            }
        }

        return destinatii;
    }



    public List<String> getListaPasageriByLunaSiAn(int luna, int an) throws SQLException {
        List<String> pasageri = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(GETLISTAPASAGERIBYLUNASIAN)) {
            stmt.setInt(1, luna);
            stmt.setInt(2, an);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pasageri.add(rs.getString("Nume"));
                }
            }
        }

        return pasageri;
    }

    public void addCursa(Curse cursa) throws SQLException {
        String query = "INSERT INTO Curse (Nume, Destinatie, Capacitate, TipTransport, TraseulID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, cursa.getNume());
            statement.setString(2, cursa.getDestinatie());
            statement.setInt(3, cursa.getCapacitate());
            statement.setString(4, cursa.getTipTransport());
            statement.setString(5, cursa.getTraseulNume());

            statement.executeUpdate();
        }
    }

    // Metodă pentru obținerea tuturor curselor din baza de date
    public List<Curse> getAllCurse() throws SQLException {
        List<Curse> curse = new ArrayList<>();
        String query = "SELECT c.CursaID, c.Nume, c.Destinatie, c.Capacitate, c.TipTransport, t.Nume AS NumeTraseu " +
                "FROM Curse c " +
                "JOIN Traseuri t ON c.TraseulID = t.TraseulID";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("CursaID");
                String nume = resultSet.getString("Nume");
                String destinatie = resultSet.getString("Destinatie");
                int capacitate = resultSet.getInt("Capacitate");
                String tipTransport = resultSet.getString("TipTransport");
                String numeTraseu = resultSet.getString("NumeTraseu");
                curse.add(new Curse(id, nume, destinatie, capacitate, tipTransport, numeTraseu));
            }
        }
        return curse;
    }

    public void updateCursa(Connection connection, int cursaID, String numeNou, String destinatie, int capacitate, String tipTransport) throws SQLException {
        String query = "UPDATE Curse SET Nume = ?, Destinatie = ?, Capacitate = ?, TipTransport = ? WHERE CursaID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, numeNou);
            preparedStatement.setString(2, destinatie);
            preparedStatement.setInt(3, capacitate);
            preparedStatement.setString(4, tipTransport);
            preparedStatement.setInt(5, cursaID);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteCursa(int cursaID) throws SQLException {
        String query = "DELETE FROM Curse WHERE CursaID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, cursaID);

            statement.executeUpdate();
        }
    }


}
