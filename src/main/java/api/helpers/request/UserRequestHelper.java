package api.helpers.request;

import java.time.LocalDate;

/**
 * UserRequestHelper
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/
public class UserRequestHelper {
    private String username;
    private String email;
    private String password;
    private LocalDate creationDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public UserRequestHelper(String username, String email, String password, String creationDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationDate = (creationDate==null ? null: LocalDate.parse(creationDate));
    }
}
