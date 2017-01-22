package db.essence;

import db.essence.Ganre;
import db.essence.Star;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Film implements DaoId {
   private String title;
   private Ganre ganre;
   private Star actor;
   private Star director;
   private LocalDate releaseDate;
   private String country;
   private List<Star> personList = new ArrayList();
   private List<Film> filmList = new ArrayList();
   private long id;


   public Film setTitle(String title) {
      this.title = title;
      return this;
   }

   public Film setActor(Star actor) {
      this.actor = actor;
      return this;
   }

   public Film setDirector(Star director) {
      this.director = director;
      return this;
   }

   public Film setReleaseDate(LocalDate releaseDate) {
      this.releaseDate = releaseDate;
      return this;
   }

   public Film setCountry(String country) {
      this.country = country;
      return this;
   }

   public Film setId(long id) {
      this.id = id;
      return this;
   }

   public Film setGanre(Ganre ganre) {
      this.ganre = ganre;
      return this;
   }

   public Film setFilmList(List<Film> filmList) {
      this.filmList = filmList;
      return this;
   }

   public Film setPersonList(List<Star> personList) {
      this.personList = personList;
      return this;
   }


   public long getId() {
      return id;
   }

   public List<Star> getPersonList() {
      return personList;
   }

   public List<Film> getFilmList() {
      return filmList;
   }

   public Star getActor() {
      return this.actor;
   }

   public String getTitle() {
      return this.title;
   }

   public Star getDirector() {
      return this.director;
   }

   public LocalDate getReleaseDate() {
      return this.releaseDate;
   }

   public String getCountry() {
      return this.country;
   }

   public Ganre getGanre() {
      return this.ganre;
   }
}
