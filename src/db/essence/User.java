package db.essence;


public class User implements DaoId {

   private String firstName;
   private String middleName;
   private String lastName;
   private String passwords;
   private String email;
   private Role role;
   private long id;

   public User setRole(Role role) {
      this.role = role;
      return this;
   }

   public User setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public User setMiddleName(String middleName) {
      this.middleName = middleName;
      return this;
   }

   public User setLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public User setPasswords(String passwords) {
      this.passwords = passwords;
      return this;
   }

   public User setEmail(String email) {
      this.email = email;
      return this;
   }

   public User setId(long id) {
      this.id = id;
      return this;
   }

   public Role getRole() {
      return role;
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

   public String getPasswords() {
      return passwords;
   }

   public String getEmail() {
      return email;
   }

   public long getId() {
      return id;
   }
}
