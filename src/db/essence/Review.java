package db.essence;

import db.essence.Film;
import db.essence.User;

public class Review implements DaoId {

   private Film films;
   private User users;
   private String text;
   private double mark;
   private long id;

   public Review setFilms(Film films) {
      this.films = films;
       return this;
   }

   public Review setUsers(User users) {
      this.users = users;
       return this;
   }

   public Review setText(String text) {
      this.text = text;
       return this;
   }

   public Review setMark(double mark) {
      this.mark = mark;
       return this;
   }

   public Review setId(long id) {
      this.id = id;
       return this;
   }

    public Film getFilms() {
        return films;
    }

    public User getUsers() {
        return users;
    }

    public String getText() {
        return text;
    }

    public double getMark() {
        return mark;
    }

    public long getId() {
        return id;
    }
}
