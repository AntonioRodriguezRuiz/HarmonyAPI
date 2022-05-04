package api.helpers.response;

import api.helpers.enums.RoleType;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;

import java.time.LocalDate;

import static src.main.java.model.Tables.*;

public class PeopleMediaResponseHelper {
    private Integer mediaid;
    private Integer personid;
    private String name;
    private LocalDate birthdate;
    private String role;
    private RoleType roleType;

    public Integer getMediaid() {
        return mediaid;
    }

    public void setMediaid(Integer mediaid) {
        this.mediaid = mediaid;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public PeopleMediaResponseHelper(Integer mediaid, Integer personid, String name, LocalDate birthdate, String role, RoleType roleType) {
        this.mediaid = mediaid;
        this.personid = personid;
        this.name = name;
        this.birthdate = birthdate;
        this.role = role;
        this.roleType = roleType;
    }

    public PeopleMediaResponseHelper(Record record, Table type) {
        this.mediaid = record.getValue(MEDIA.MEDIAID);
        this.personid = record.getValue(PEOPLE.PERSONID);
        this.name = record.getValue(PEOPLE.NAME);
        this.birthdate = record.getValue(PEOPLE.BIRTHDATE);

        switch (type.getName()){
            case "movies": this.role = record.getValue(PEOPLEMOVIES.ROLE);
                this.roleType = RoleType.of(record.getValue((PEOPLEMOVIES.ROLETYPE)));
                break;

            case "books": this.role = record.getValue(PEOPLEBOOKS.ROLE);
                this.roleType = RoleType.of(record.getValue((PEOPLEBOOKS.ROLETYPE)));
                break;

            case "videogames": this.role = record.getValue(PEOPLEVIDEOGAMES.ROLE);
                this.roleType = RoleType.of(record.getValue((PEOPLEBOOKS.ROLETYPE)));
                break;
        }

    }
}