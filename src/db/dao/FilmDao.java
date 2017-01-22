package db.dao;

import db.connection.DbConnection;
import db.essence.Film;
import db.essence.Ganre;
import db.essence.Star;

import java.sql.*;
import java.time.LocalDate;


public class FilmDao extends AbstractDao<Film> {
    private static final String addFilm = "INSERT INTO film (Title, Ganre, Release_Date, Country) VALUES(?, ?, ?, ?)";
    private static final String updateForFilm = "UPDATE film SET Ganre = ? , Release_Date = ?, Country = ? WHERE Title = ?";
    private static final String actorInFilm = "SELECT film.Title , stars.* FROM film JOIN films_stars ON film.id_Film = films_stars.id_Film JOIN stars ON stars.id_Stars = films_stars.id_Stars WHERE film.Title = ? ";
    private static final String forDelete = "DELETE FROM film WHERE Title = ?";
    private LocalDate releaseDate;
    private static String nameTable = "film";
    private static String nameId = "id_Film";
    Connection connection;


    public void addFilmInDb(String title, Ganre ganre, int year, int month, int day, String Country) throws SQLException {
        DbConnection.getConnectionDb();
        PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(addFilm);
        statement.setString(1, title);
        statement.setString(2, ganre.toString().toUpperCase());
        statement.setObject(3, LocalDate.of(year, month, day));
        statement.setString(4, Country);
        statement.execute();
        statement.close();

    }

    @Override
    public Film getById(long id) {
        Film film = new Film();
        connection = DbConnection.getConnectionDb();
        ResultSet resultSet = getResultsById(nameTable, nameId, id);
        try {

            if (resultSet != null && resultSet.next()) {

                film.setId(resultSet.getLong("id_Film")).setTitle(resultSet.getString("Title"))
                        .setGanre(Ganre.valueOf(resultSet.getString("Ganre").toUpperCase()))
                        .setCountry(resultSet.getString("Country") != null ? resultSet.getString("Country") : null)
                        .setReleaseDate(resultSet.getObject("Release_Date") != null ? resultSet.getObject("Release_Date", LocalDate.class) : null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }
    //вынести в абстракт
    @Override
    public Film deleteByName(String name) {
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

    public void updateByName(String name, Ganre ganre, int year, int month, int day, String Country) throws SQLException {
        DbConnection.getConnectionDb();
        PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(updateForFilm);
        statement.setString(4, name);
        statement.setString(1, ganre.toString().toUpperCase());
        statement.setObject(2, LocalDate.of(year, month, day));
        statement.setString(3, Country);
        statement.execute();
        statement.close();
    }

    public Film getActorInFilm(String name) {
        DbConnection.getConnectionDb();
        Film film = new Film();
        try {
            PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(actorInFilm);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Star star = new Star();
                film.setTitle(resultSet.getString("Title"))
                        .setActor(star.setFirstName(resultSet.getString("FirstName"))
                                .setLastName(resultSet.getString("LastName"))
                                .setMiddleName(resultSet.getString("MidleName") != null ? resultSet.getString("MidleName") : null));
                film.getPersonList().add(film.getActor());
            }
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }
}
