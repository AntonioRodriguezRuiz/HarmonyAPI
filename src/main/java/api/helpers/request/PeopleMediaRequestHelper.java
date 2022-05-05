package api.helpers.request;

import api.helpers.enums.RoleType;

public class PeopleMediaRequestHelper {
    private Integer userid;
    private Integer personid;
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

    public PeopleMediaRequestHelper(Integer userid, Integer personid, String role, RoleType roleType) {
        this.userid = userid;
        this.personid = personid;
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
