package Model.dao;

import Model.Curse;
import Model.DatabaseConnection;
import Model.Rezervari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class RezervareDAO {

    private Connection connection;

    public RezervareDAO() throws SQLException{
        this.connection = DatabaseConnection.getConnection();;
    }

    public void updateRezervare(Connection connection, int RezervareID, String NumePasager, String NumeCursa, int NumarLocuri, Date DataRezervare) throws SQLException {
        String query = "UPDATE Rezervari r " +
                "JOIN Pasageri p ON r.PasagerID = p.PasagerID " +
                "JOIN Curse c ON r.CursaID = c.CursaID " +
                "SET r.NumarLocuri = ?, r.DataRezervare = ? " +
                "WHERE r.RezervareID = ? AND p.Nume = ? AND c.Nume = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, NumarLocuri);
            preparedStatement.setDate(2, DataRezervare);
            preparedStatement.setInt(3, RezervareID);
            preparedStatement.setString(4, NumePasager);
            preparedStatement.setString(5, NumeCursa);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteRezervare(int RezervareID) throws SQLException {
        String query = "DELETE FROM Rezervari WHERE RezervareID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, RezervareID);

            statement.executeUpdate();
        }
    }


    public static void addRezervare(Connection conn, String numePasager, String numeCursa, int numarLocuri, Date dataRezervare) throws SQLException {
        String query = "INSERT INTO Rezervari (PasagerID, CursaID, NumarLocuri, DataRezervare) " +
                "SELECT p.PasagerID, c.CursaID, ?, ? " +
                "FROM Pasageri p CROSS JOIN Curse c " +
                "WHERE p.Nume = ? AND c.Nume = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, numarLocuri);
            statement.setDate(2, dataRezervare);
            statement.setString(3, numePasager);
            statement.setString(4, numeCursa);

            statement.executeUpdate();
        }
    }


    public List<Rezervari> getAllRezervari() throws SQLException {
        List<Rezervari> rezervari = new ArrayList<>();
        String query = "SELECT\n" +
                "    r.RezervareID,\n" +
                "    p.Nume AS NumePasager,\n" +
                "    c.Nume AS NumeCursa,\n" +
                "    r.NumarLocuri,\n" +
                "    r.DataRezervare\n" +
                "FROM Rezervari r\n" +
                "JOIN Pasageri p ON r.PasagerID = p.PasagerID\n" +
                "JOIN Curse c ON r.CursaID = c.CursaID\n" +
                "ORDER BY r.RezervareID;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int rezervareID = resultSet.getInt("RezervareID");
                String numePasager = resultSet.getString("NumePasager");
                String numeCursa = resultSet.getString("NumeCursa");
                int numarLocuri = resultSet.getInt("NumarLocuri");
                Date dataRezervare = resultSet.getDate("DataRezervare");
                rezervari.add(new Rezervari(rezervareID, numePasager, numeCursa, numarLocuri, dataRezervare));
            }
        }
        return rezervari;
    }

    private static final String GETREZERVARIBYPASAGERIANDCURSA = "SELECT NumarLocuri FROM Rezervari R JOIN Pasageri P ON R.PasagerID = P.PasagerID JOIN Curse C ON R.CursaID = C.CursaID WHERE P.Nume = ? AND C.Nume = ?";

    public List<Rezervari> getRezervariByPasagerAndCursa(Connection connection, String numePasager, String numeCursa) throws SQLException {
        List<Rezervari> rezervari = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(GETREZERVARIBYPASAGERIANDCURSA)) {
            statement.setString(1, numePasager);
            statement.setString(2, numeCursa);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int numarLocuri = resultSet.getInt("NumarLocuri");
                Rezervari rezervare = new Rezervari(numePasager, numeCursa, numarLocuri);
                rezervari.add(rezervare);
            }
        }
        return rezervari;
    }
}
