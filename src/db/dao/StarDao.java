package db.dao;

import db.connection.DbConnection;
import db.dao.AbstractDao;
import db.essence.Film;
import db.essence.Ganre;
import db.essence.Star;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StarDao extends AbstractDao<Star> {
    private static String nameTable = "stars";
    private static String nameID = "id_Stars";
    private static String addStar = " INSERT INTO stars (FirstName, MidleName,LastName,Date_of_Birth ) VALUES(?,?,?,?)";
    private static String updateStar = "UPDATE stars SET FirstName = ?, MidleName = ?, Date_of_Birth = ? WHERE LastName = ?";
    private static final String forDelete = "DELETE FROM stars WHERE LastName = ?";
    private static final String forGetFilmInActor = "SELECT  stars.FirstName,stars.LastName, film.* FROM films_stars JOIN stars ON stars.id_Stars = films_stars.id_Stars JOIN film ON film.id_Film = films_stars.id_Film WHERE stars.LastName = ?";


    public void addStarInDb(String firstName, String midleName, String lastName, int year, int month, int day) {
        DbConnection.getConnectionDb();
        try {
            PreparedStatement preparedStatement = forAdd(firstName, midleName, lastName, year, month, day, addStar);
            preparedStatement.execute();
            preparedStatement.close();
            // DbConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Star getById(long id) {
        DbConnection.getConnectionDb();
        Star star = new Star();
        ResultSet resultSet = getResultsById(nameTable, nameID, id);

        try {
            if (resultSet.next()) {
                star.setId(resultSet.getLong("id_Stars")).setFirstName(resultSet.getString("FirstName"))
                        .setLastName(resultSet.getString("LastName")).
                        setMiddleName(resultSet.getString("MidleName") != null ? resultSet.getString("MidleName") : null);
                star.setDateOfBirth(resultSet.getObject("Date_of_Birth") != null ? resultSet.getObject("Date_of_Birth", LocalDate.class) : null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return star;
    }

    public Star deleteByName(String name) {

        try {
            PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(forDelete);
            statement.setString(1, name);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateByName(String firstName, String midleName, String lastName, int year, int month, int day) {
        DbConnection.getConnectionDb();
        try {
            PreparedStatement preparedStatement = forUpdate(firstName, midleName, lastName, year, month, day, updateStar);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Star getFilmInActor(String lastName) {
        DbConnection.getConnectionDb();
        Star star = new Star();
        try {
            PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(forGetFilmInActor);
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                star.setFirstName(resultSet.getString("FirstName")).setLastName(resultSet.getString("LastName"))
                        .setFilm(film.setId(resultSet.getLong("id_Film")).setTitle(resultSet.getString("Title"))
                                .setGanre(Ganre.valueOf(resultSet.getString("Ganre")))
                                .setCountry(resultSet.getString("Country") != null ? resultSet.getString("Country") : null)
                                .setReleaseDate(resultSet.getObject("Release_Date") != null ? resultSet.getObject("Release_Date", LocalDate.class) : null));
                star.getFilmList().add(star.getFilm());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return star;
    }

}
