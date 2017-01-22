package db.essence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Star implements DaoId {

   private String firstName;
   private String middleName;
   private String lastName;
   private LocalDate dateOfBirth;
   private Film film;
   private long id;
   private List<Film> filmList = new ArrayList();

   public Star setFilm(Film film) {
      this.film = film;
      return this;
   }

   public Star setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public Star setMiddleName(String middleName) {
      this.middleName = middleName;
      return this;
   }

   public Star setLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public Star setDateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
      return this;
   }

   public Star setId(long id) {
      this.id = id;
      return this;
   }

   public Star setFilmList(List<Film> filmList) {
      this.filmList = filmList;
      return this;
   }

   public Film getFilm() {
      return film;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getMiddleName() {
      return middleName;
   }

   public String getLastName() {
      return lastName;
   }

   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public long getId() {
      return id;
   }

   public List<Film> getFilmList() {
      return filmList;
   }
}
