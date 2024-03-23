package Model.dao;


import Model.Curse;
import Model.DatabaseConnection;
import Model.Pasageri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasagerDAO {

    private final Connection connection;

    public PasagerDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public void updatePasager(Connection connection, int pasagerID, String numeNou) throws SQLException {
        String query = "UPDATE Pasageri SET Nume = ? WHERE PasagerID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, numeNou);
            preparedStatement.setInt(2, pasagerID);

            preparedStatement.executeUpdate();
        }
    }

    public void deletePasager(int pasagerID) throws SQLException {
        String query = "DELETE FROM Pasageri WHERE Pasageri.PasagerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, pasagerID);

            statement.executeUpdate();
        }
    }

    public void addPasager(Pasageri pasager) throws SQLException {
        String query = "INSERT INTO Pasageri (Nume) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, pasager.getNume());


            statement.executeUpdate();
        }
    }

    public List<Pasageri> getAllPasageri() throws SQLException {
        List<Pasageri> curse = new ArrayList<>();
        String query = "SELECT * FROM Pasageri";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("PasagerID");
                String nume = resultSet.getString("Nume");

                curse.add(new Pasageri(id, nume));
            }
        }
        return curse;
    }

    private static final String GETPASAGERIBYCURSA = "SELECT P.Nume FROM Pasageri P JOIN Rezervari R ON P.PasagerID = R.PasagerID JOIN Curse C ON R.CursaID = C.CursaID WHERE C.Nume = ?";
    public List<Pasageri> getPasageriByCursa(Connection connection, String numeCursa) throws SQLException {
        List<Pasageri> pasageri = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(GETPASAGERIBYCURSA)) {
            statement.setString(1, numeCursa);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String numePasager = resultSet.getString("Nume");
                Pasageri pasager = new Pasageri(numePasager);
                pasageri.add(pasager);
            }
        }
        return pasageri;
    }

    private static final String GETLISTAPASAGERIBYCURSASIDATA = "SELECT P.Nume FROM Pasageri P JOIN Rezervari R ON P.PasagerID = R.PasagerID JOIN Curse C ON R.CursaID = C.CursaID WHERE C.Nume = ? AND R.DataRezervare = ?";

    public String getListaPasageriByCursaSiData(Connection connection, String numeCursa, String data) throws SQLException {
        String listaPasageri = "";

        try (PreparedStatement statement = connection.prepareStatement(GETLISTAPASAGERIBYCURSASIDATA)) {
            statement.setString(1, numeCursa);
            statement.setString(2, data);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String numePasager = resultSet.getString("Nume");
                listaPasageri += numePasager + "\n";
            }
        }
        return listaPasageri.trim();
    }
}
