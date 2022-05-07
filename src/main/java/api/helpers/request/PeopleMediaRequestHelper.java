package api.helpers.request;

import api.helpers.enums.RoleType;

import java.time.LocalDate;

public class PeopleMediaRequestHelper {
    private Integer userid;
    private Integer personid;
    private String name;
    private LocalDate birthdate;
    private String picture;
    private String role;
    private RoleType roleType;

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

    public PeopleMediaRequestHelper(Integer userid, Integer personid, String name, LocalDate birthdate, String picture, String role, RoleType roleType) {
        this.userid = userid;
        this.personid = personid;
        this.name=name;
        this.birthdate=birthdate;
        this.picture=picture;
        this.role = role;
        this.roleType = roleType;
    }

    public Byte getRoleTypeByte() {
        switch (this.roleType){
            case CREW: return Byte.valueOf("0");
            case CAST: return Byte.valueOf("1");
        }
        return null;
    }
}
