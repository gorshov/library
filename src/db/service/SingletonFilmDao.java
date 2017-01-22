package db.service;

import db.dao.FilmDao;

/**
 * Created by Admin on 22.01.2017.
 */
public class SingletonFilmDao {
    private static FilmDao filmDao;

    public static FilmDao getFilmDao(){
        FilmDao newFilmDao = filmDao;
        if(newFilmDao == null){
            synchronized (FilmDao.class){
                newFilmDao = filmDao;
                if (newFilmDao == null){
                    filmDao = newFilmDao = new FilmDao();
                }
            }
        }
        return newFilmDao;
    }
}
