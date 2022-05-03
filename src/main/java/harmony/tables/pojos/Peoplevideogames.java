/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Peoplevideogames implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer peoplevideogamesid;
    private Integer videogameid;
    private Integer personid;
    private String  role;
    private Byte    roletype;

    public Peoplevideogames() {}

    public Peoplevideogames(Peoplevideogames value) {
        this.peoplevideogamesid = value.peoplevideogamesid;
        this.videogameid = value.videogameid;
        this.personid = value.personid;
        this.role = value.role;
        this.roletype = value.roletype;
    }

    public Peoplevideogames(
        Integer peoplevideogamesid,
        Integer videogameid,
        Integer personid,
        String  role,
        Byte    roletype
    ) {
        this.peoplevideogamesid = peoplevideogamesid;
        this.videogameid = videogameid;
        this.personid = personid;
        this.role = role;
        this.roletype = roletype;
    }

    /**
     * Getter for <code>harmony.peopleVideogames.peopleVideogamesid</code>.
     */
    public Integer getPeoplevideogamesid() {
        return this.peoplevideogamesid;
    }

    /**
     * Setter for <code>harmony.peopleVideogames.peopleVideogamesid</code>.
     */
    public Peoplevideogames setPeoplevideogamesid(Integer peoplevideogamesid) {
        this.peoplevideogamesid = peoplevideogamesid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleVideogames.videogameid</code>.
     */
    public Integer getVideogameid() {
        return this.videogameid;
    }

    /**
     * Setter for <code>harmony.peopleVideogames.videogameid</code>.
     */
    public Peoplevideogames setVideogameid(Integer videogameid) {
        this.videogameid = videogameid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleVideogames.personid</code>.
     */
    public Integer getPersonid() {
        return this.personid;
    }

    /**
     * Setter for <code>harmony.peopleVideogames.personid</code>.
     */
    public Peoplevideogames setPersonid(Integer personid) {
        this.personid = personid;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleVideogames.role</code>.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Setter for <code>harmony.peopleVideogames.role</code>.
     */
    public Peoplevideogames setRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Getter for <code>harmony.peopleVideogames.roletype</code>.
     */
    public Byte getRoletype() {
        return this.roletype;
    }

    /**
     * Setter for <code>harmony.peopleVideogames.roletype</code>.
     */
    public Peoplevideogames setRoletype(Byte roletype) {
        this.roletype = roletype;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Peoplevideogames other = (Peoplevideogames) obj;
        if (peoplevideogamesid == null) {
            if (other.peoplevideogamesid != null)
                return false;
        }
        else if (!peoplevideogamesid.equals(other.peoplevideogamesid))
            return false;
        if (videogameid == null) {
            if (other.videogameid != null)
                return false;
        }
        else if (!videogameid.equals(other.videogameid))
            return false;
        if (personid == null) {
            if (other.personid != null)
                return false;
        }
        else if (!personid.equals(other.personid))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        }
        else if (!role.equals(other.role))
            return false;
        if (roletype == null) {
            if (other.roletype != null)
                return false;
        }
        else if (!roletype.equals(other.roletype))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.peoplevideogamesid == null) ? 0 : this.peoplevideogamesid.hashCode());
        result = prime * result + ((this.videogameid == null) ? 0 : this.videogameid.hashCode());
        result = prime * result + ((this.personid == null) ? 0 : this.personid.hashCode());
        result = prime * result + ((this.role == null) ? 0 : this.role.hashCode());
        result = prime * result + ((this.roletype == null) ? 0 : this.roletype.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Peoplevideogames (");

        sb.append(peoplevideogamesid);
        sb.append(", ").append(videogameid);
        sb.append(", ").append(personid);
        sb.append(", ").append(role);
        sb.append(", ").append(roletype);

        sb.append(")");
        return sb.toString();
    }
}
