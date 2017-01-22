package db.dao;


import db.essence.Review;

import java.sql.SQLException;

/**
 * Created by Admin on 21.01.2017.
 */
public class ReviewDao extends AbstractDao<Review> {
    public void addUserInDb(){

    }
    @Override
    public Review getById(long id) throws SQLException {
        return null;
    }

    @Override
    public Review deleteByName(String name) throws SQLException {
        return null;
    }
}
