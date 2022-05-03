/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Videogameplatforms implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer videogameplatformid;
    private Integer videogameid;
    private Integer platformid;

    public Videogameplatforms() {}

    public Videogameplatforms(Videogameplatforms value) {
        this.videogameplatformid = value.videogameplatformid;
        this.videogameid = value.videogameid;
        this.platformid = value.platformid;
    }

    public Videogameplatforms(
        Integer videogameplatformid,
        Integer videogameid,
        Integer platformid
    ) {
        this.videogameplatformid = videogameplatformid;
        this.videogameid = videogameid;
        this.platformid = platformid;
    }

    /**
     * Getter for <code>harmony.videogameplatforms.videogamePlatformid</code>.
     */
    public Integer getVideogameplatformid() {
        return this.videogameplatformid;
    }

    /**
     * Setter for <code>harmony.videogameplatforms.videogamePlatformid</code>.
     */
    public Videogameplatforms setVideogameplatformid(Integer videogameplatformid) {
        this.videogameplatformid = videogameplatformid;
        return this;
    }

    /**
     * Getter for <code>harmony.videogameplatforms.videogameid</code>.
     */
    public Integer getVideogameid() {
        return this.videogameid;
    }

    /**
     * Setter for <code>harmony.videogameplatforms.videogameid</code>.
     */
    public Videogameplatforms setVideogameid(Integer videogameid) {
        this.videogameid = videogameid;
        return this;
    }

    /**
     * Getter for <code>harmony.videogameplatforms.platformid</code>.
     */
    public Integer getPlatformid() {
        return this.platformid;
    }

    /**
     * Setter for <code>harmony.videogameplatforms.platformid</code>.
     */
    public Videogameplatforms setPlatformid(Integer platformid) {
        this.platformid = platformid;
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
        final Videogameplatforms other = (Videogameplatforms) obj;
        if (videogameplatformid == null) {
            if (other.videogameplatformid != null)
                return false;
        }
        else if (!videogameplatformid.equals(other.videogameplatformid))
            return false;
        if (videogameid == null) {
            if (other.videogameid != null)
                return false;
        }
        else if (!videogameid.equals(other.videogameid))
            return false;
        if (platformid == null) {
            if (other.platformid != null)
                return false;
        }
        else if (!platformid.equals(other.platformid))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.videogameplatformid == null) ? 0 : this.videogameplatformid.hashCode());
        result = prime * result + ((this.videogameid == null) ? 0 : this.videogameid.hashCode());
        result = prime * result + ((this.platformid == null) ? 0 : this.platformid.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Videogameplatforms (");

        sb.append(videogameplatformid);
        sb.append(", ").append(videogameid);
        sb.append(", ").append(platformid);

        sb.append(")");
        return sb.toString();
    }
}
