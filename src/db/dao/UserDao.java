package db.dao;

import db.connection.DbConnection;
import db.essence.Role;
import db.essence.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao extends AbstractDao {
    private static final String forAdd = "INSERT INTO user (First_Name, Midle_Name, Last_Name, Passwords, Email, Role) VALUES(?,?,?,?,?,?)";
    private static final String forUpdate = "UPDATE user SET First_Name = ?,Midle_Name = ?, Passwords = ?, Role = ? WHERE Last_Name = ?";
    private static final String forDelete = "DELETE FROM user WHERE Last_Name = ? ";
    private static final String nameTable = "user";
    private static final String nameIdforGetById = "id_User";
    private static final String nameforDelete = "Last_Name";

    public void addUserInDb(String firstname, String midleName, String lastName, String passwords, String email, String role) {
        try {
            PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(forAdd);
            statement.setString(1, firstname);
            statement.setString(2, midleName);
            statement.setString(3, lastName);
            statement.setString(4, passwords);
            statement.setString(5, email);
            statement.setString(6, role);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateByName(String firstname, String midleName, String lastName, String passwords, String role) {
        try {
            PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(forUpdate);
            statement.setString(1, firstname);
            statement.setString(2, midleName);
            statement.setString(3, passwords);
            statement.setString(4, role);
            statement.setString(5, lastName);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getById(long id) {
        DbConnection.getConnectionDb();
        User user = new User();
        ResultSet resultSet = getResultsById(nameTable, nameIdforGetById, id);
        try {
            if (resultSet != null && resultSet.next()) {
                user.setFirstName(resultSet.getString("First_Name") != null ? resultSet.getString("First_Name") : null)
                        .setMiddleName(resultSet.getString("Midle_Name") != null ? resultSet.getString("Midle_Name") : null)
                        .setLastName(resultSet.getString("Last_Name")).setPasswords(resultSet.getString("Passwords"))
                        .setEmail(resultSet.getString("Email")).setRole(Role.valueOf(resultSet.getString("Role").toString().toUpperCase()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User deleteByName(String name) {
        try {
            PreparedStatement preparedStatement = DbConnection.getConnectionDb().prepareStatement(forDelete);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
