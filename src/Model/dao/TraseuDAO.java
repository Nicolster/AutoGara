package Model.dao;

import Model.DatabaseConnection;
import Model.Pasageri;
import Model.Traseuri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TraseuDAO {

    private final Connection connection;

    public TraseuDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public void deleteTraseu(int traseuID) throws SQLException {
        String query = "DELETE FROM Traseuri WHERE TraseulID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, traseuID);

            statement.executeUpdate();
        }
    }

    public void addTraseu(Traseuri traseu) throws SQLException {
        String query = "INSERT INTO Traseuri (Nume) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, traseu.getNume());


            statement.executeUpdate();
        }
    }

    public List<Traseuri> getAllTraseuri() throws SQLException {
        List<Traseuri> traseuri = new ArrayList<>();
        String query = "SELECT * FROM Traseuri";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("TraseulID");
                String nume = resultSet.getString("Nume");

                traseuri.add(new Traseuri(id, nume));
            }
        }
        return traseuri;
    }

    public void updateTraseu(Connection connection, int traseulID, String Nume) throws SQLException {
        String query = "UPDATE Traseuri SET Nume = ? WHERE TraseulID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, Nume);
            preparedStatement.setInt(2, traseulID);

            preparedStatement.executeUpdate();
        }
    }
}
