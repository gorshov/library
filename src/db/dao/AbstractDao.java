package db.dao;

import db.connection.DbConnection;
import db.essence.DaoId;

import java.sql.*;
import java.time.LocalDate;
import java.util.Formatter;

public abstract class AbstractDao<T extends DaoId> {
    private static final String selectOfTable1 = "SELECT * FROM %s WHERE %s=%d";
    private static final String forDelete = "DELETE FROM %s WHERE %s = %s";

    public abstract T getById(long id) throws SQLException;

    public abstract T deleteByName(String name) throws SQLException;

    protected ResultSet getResultsById(String nameTable, String nameID, long id) {
        Connection connection = DbConnection.getConnectionDb();
        try {
            Formatter formatter = new Formatter();
            Statement statement = connection.createStatement();
            return statement.executeQuery(formatter.format(selectOfTable1, nameTable, nameID, id).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //вынести из абстракт в стардао
    protected PreparedStatement forAdd(String firstName, String midleName, String lastName, int year, int month, int day, String request) throws SQLException {
        PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(request);
        try {
            statement.setString(1, firstName);
            statement.setString(2, midleName);
            statement.setString(3, lastName);
            statement.setObject(4, LocalDate.of(year, month, day));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    //вынести из абстракт в стардао
    protected PreparedStatement forUpdate(String firstName, String midleName, String lastName, int year, int month, int day, String request) throws SQLException {
        PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(request);
        try {
            statement.setString(1, firstName);
            statement.setString(2, midleName);
            statement.setObject(3, LocalDate.of(year, month, day));
            statement.setString(4, lastName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    protected Statement getForDelete(String nameTable, String nameId, String name) {
        Connection connection = DbConnection.getConnectionDb();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(forDelete, nameTable, nameId, name));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
