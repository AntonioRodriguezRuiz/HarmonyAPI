package api.helpers.response;

import org.jooq.Record;

import java.time.LocalDate;

import static src.main.java.model.Tables.*;

public class PeopleResponseHelper {
    private Integer personid;
    private String name;
    private LocalDate birthdate;
    private String picture;



    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public PeopleResponseHelper(Integer personid, String name, LocalDate birthdate, String picture) {
        this.personid = personid;
        this.name = name;
        this.birthdate = birthdate;
        this.picture = picture;
    }

    public PeopleResponseHelper(Record record) {
        this.personid=record.getValue(PEOPLE.PERSONID);
        this.name=record.getValue(PEOPLE.NAME);
        this.birthdate=record.getValue(PEOPLE.BIRTHDATE);
        this.picture=record.getValue(PEOPLE.PICTURE);
    }
}
