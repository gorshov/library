package daoTest;

import db.connection.DbConnection;
import db.dao.FilmDao;
import db.dao.StarDao;
import db.dao.UserDao;
import db.essence.Film;
import db.essence.Ganre;
import db.essence.Star;
import db.essence.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 10.01.2017.
 */
public class libraryTest {
    FilmDao filmDao = new FilmDao();
    Film film = new Film();
    StarDao starDao = new StarDao();
    Star star = new Star();
    UserDao userDao = new UserDao();
    User user = new User();
    Connection connection;

    @Before
    public void initDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DbConnection.setDbName("library");
        connection = DbConnection.getConnectionDb();
        Assert.assertNotNull(connection);
    }

    @Test
    public void connectionTest() throws SQLException {
        connection = DbConnection.getConnectionDb();
        connection.close();
    }

    @Test
    public void addFilmTest() throws SQLException {
        filmDao.addFilmInDb("Full", Ganre.DRAMA, 1998, 12, 12, "USA");
    }

    @Test
    public void getByIdTest() throws SQLException {
        film = filmDao.getById(2L);
        Assert.assertEquals("USA", film.getCountry());
    }

    @Test
    public void updateByNameFilmTest() throws SQLException {
        filmDao.updateByName("Full", Ganre.ACTION, 2016, 1, 2, "UK");
    }

    @Test
    public void deleteByNameFilmTest() {
        filmDao.deleteByName("Full");
    }

    @Test
    public void getActorInFilmTest() {
        film = filmDao.getActorInFilm("Fight Club");
        Assert.assertEquals("Pit", film.getPersonList().get(1).getLastName());
        Assert.assertEquals("Norton", film.getPersonList().get(0).getLastName());
    }

    @Test
    public void getByIdStar() {
        star = starDao.getById(1l);
        Assert.assertEquals("Arnold", star.getFirstName());
    }

    @Test
    public void addStarInDbTest() {
        starDao.addStarInDb("Actor", "Actor", "Actor", 1998, 12, 12);
    }

    @Test
    public void deletByNameStar() {
        starDao.deleteByName("Actor");
    }

    @Test
    public void updateByNameStarTest() {
        starDao.updateByName("Actor", "NtActor","Actor", 1989, 10, 10);
    }
    @Test
    public void getFilmInActorTest(){
        star = starDao.getFilmInActor("Pitt");
        Assert.assertEquals("Fight Club",star.getFilmList().get(0).getTitle());
    }
    @Test
    public void addUserInDbTest(){
       userDao.addUserInDb("Alex","OverAlex","LastAlex","12345","alex@tutnet.by","user");
    }
    @Test
    public void updateByNameUserTest(){
        userDao.updateByName("Alex","AlexOverAlex","LastAlex","12345","user");
    }

    @Test
    public void getByIdUserTest(){
        user = userDao.getById(1L);
        Assert.assertEquals("Admin",user.getFirstName());
    }
    @Test
    public void deleteByNameUserTest(){
        userDao.deleteByName("LastAlex");
    }

}
