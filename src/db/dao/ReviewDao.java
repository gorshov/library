package db.dao;


import db.connection.DbConnection;
import db.essence.Film;
import db.essence.Review;
import db.essence.User;

import java.sql.*;
import java.sql.SQLException;


/**
 * Created by Admin on 21.01.2017.
 */
public class ReviewDao extends AbstractDao<Review> {
    private static final String forAdd = "INSERT INTO review (id_User, id_Film , Text , Mark ) VALUES (?,?,?,?)";
    private static final String forUpdate = "UPDATE review SET id_Film = ?,Text = ?, Mark = ? WHERE id_User = ?";
    private static String nameTable = "review";
    private static String nameID = "id_Review";

    public void addReviewInDb(UserDao userDao, long idUser, FilmDao filmDao, long idFilm, String text, double mark) {
        try {
            PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(forAdd);
            statement.setLong(1, userDao.getById(idUser).getId());
            statement.setLong(2, filmDao.getById(idFilm).getId());
            statement.setString(3, text);
            statement.setDouble(4, mark);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updaneByUserId(UserDao userDao, long idUser, FilmDao filmDao, long idFilm, String text, double mark) {
        try {
            PreparedStatement statement = DbConnection.getConnectionDb().prepareStatement(forUpdate);
            statement.setLong(1, filmDao.getById(idFilm).getId());
            statement.setString(2, text);
            statement.setDouble(3, mark);
            statement.setLong(4, userDao.getById(idUser).getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Review getById(long id) {
        DbConnection.getConnectionDb();
        Review review = new Review();
        User user = new User();
        Film film = new Film();
        ResultSet resultSet = getResultsById(nameTable, nameID, id);
        try {
            if (resultSet != null && resultSet.next()) {
                review.setId(resultSet.getLong("id_Review")).setUsers(user.setId(resultSet.getLong("id_User")))
                        .setFilms(film.setId(resultSet.getLong("id_Film"))).setText(resultSet.getString("Text"))
                        .setMark(resultSet.getDouble("Mark"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    @Override
    public Review deleteByName(String name) {
        Statement statement = getForDelete(nameTable,nameID,name);
        return null;
    }
}
