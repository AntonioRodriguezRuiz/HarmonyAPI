/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables.pojos;


import java.io.Serializable;
import java.time.LocalDate;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer   personid;
    private String    name;
    private LocalDate birthdate;
    private String    picture;

    public People() {}

    public People(People value) {
        this.personid = value.personid;
        this.name = value.name;
        this.birthdate = value.birthdate;
        this.picture = value.picture;
    }

    public People(
        Integer   personid,
        String    name,
        LocalDate birthdate,
        String    picture
    ) {
        this.personid = personid;
        this.name = name;
        this.birthdate = birthdate;
        this.picture = picture;
    }

    /**
     * Getter for <code>harmony.people.personid</code>.
     */
    public Integer getPersonid() {
        return this.personid;
    }

    /**
     * Setter for <code>harmony.people.personid</code>.
     */
    public People setPersonid(Integer personid) {
        this.personid = personid;
        return this;
    }

    /**
     * Getter for <code>harmony.people.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>harmony.people.name</code>.
     */
    public People setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Getter for <code>harmony.people.birthdate</code>.
     */
    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    /**
     * Setter for <code>harmony.people.birthdate</code>.
     */
    public People setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    /**
     * Getter for <code>harmony.people.picture</code>.
     */
    public String getPicture() {
        return this.picture;
    }

    /**
     * Setter for <code>harmony.people.picture</code>.
     */
    public People setPicture(String picture) {
        this.picture = picture;
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
        final People other = (People) obj;
        if (personid == null) {
            if (other.personid != null)
                return false;
        }
        else if (!personid.equals(other.personid))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (birthdate == null) {
            if (other.birthdate != null)
                return false;
        }
        else if (!birthdate.equals(other.birthdate))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        }
        else if (!picture.equals(other.picture))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.personid == null) ? 0 : this.personid.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.birthdate == null) ? 0 : this.birthdate.hashCode());
        result = prime * result + ((this.picture == null) ? 0 : this.picture.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("People (");

        sb.append(personid);
        sb.append(", ").append(name);
        sb.append(", ").append(birthdate);
        sb.append(", ").append(picture);

        sb.append(")");
        return sb.toString();
    }
}
