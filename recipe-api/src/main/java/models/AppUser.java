package models;

public class AppUser {

    private int user_id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public AppUser() {
        super();
    }

    public AppUser(String firstName, String lastName, String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //Maybe I'd prefer the database to handle user_id's, why should we handle it here at the AppUser stage?
    public AppUser(int user_id, String firstName, String lastName, String username, String password, String email) {
        System.out.println("AppUser constructor invoked!");
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        // you do not have to include "this." here because there is no other variable
        // with the same name in this scope
        return username;
    }

    public void setUsername(String username) {
        // "this." is required here, otherwise you do not target the correct variable
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String toFileString() {
        return String.format("%s;%s;%s;%s;%s;%d", firstName, lastName, username, password, email);
    }

//    @Override
//    public String toString() {
//        return "AppUser{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", age=" + age +
//                '}';
//    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("id=").append(user_id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
