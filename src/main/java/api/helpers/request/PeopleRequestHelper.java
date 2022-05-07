package api.helpers.request;

import java.time.LocalDate;

public class PeopleRequestHelper {
    private Integer userid;
    private Integer personid;
    private String name;
    private LocalDate birthdate;
    private String picture;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name=name;}

    public LocalDate getBirthdate() {return birthdate;}

    public void setBirthdate(LocalDate birthdate) {this.birthdate=birthdate;}

    public String getPicture() { return picture;}

    public void setPicture(String picture) { this.picture = picture;}

    public PeopleRequestHelper(Integer userid, Integer personid, String name, LocalDate birthdate, String picture) {
        this.userid = userid;
        this.personid = personid;
        this.name = name;
        this.birthdate = birthdate;
        this.picture = picture;
    }

}
