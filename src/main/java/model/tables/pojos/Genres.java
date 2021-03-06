/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Genres implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer genreid;
    private String  name;

    public Genres() {}

    public Genres(Genres value) {
        this.genreid = value.genreid;
        this.name = value.name;
    }

    public Genres(
        Integer genreid,
        String  name
    ) {
        this.genreid = genreid;
        this.name = name;
    }

    /**
     * Getter for <code>harmony.genres.genreid</code>.
     */
    public Integer getGenreid() {
        return this.genreid;
    }

    /**
     * Setter for <code>harmony.genres.genreid</code>.
     */
    public Genres setGenreid(Integer genreid) {
        this.genreid = genreid;
        return this;
    }

    /**
     * Getter for <code>harmony.genres.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>harmony.genres.name</code>.
     */
    public Genres setName(String name) {
        this.name = name;
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
        final Genres other = (Genres) obj;
        if (genreid == null) {
            if (other.genreid != null)
                return false;
        }
        else if (!genreid.equals(other.genreid))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.genreid == null) ? 0 : this.genreid.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Genres (");

        sb.append(genreid);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
