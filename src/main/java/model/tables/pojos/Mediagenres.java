/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Mediagenres implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer mediagenresid;
    private Integer mediaid;
    private Integer genreid;

    public Mediagenres() {}

    public Mediagenres(Mediagenres value) {
        this.mediagenresid = value.mediagenresid;
        this.mediaid = value.mediaid;
        this.genreid = value.genreid;
    }

    public Mediagenres(
        Integer mediagenresid,
        Integer mediaid,
        Integer genreid
    ) {
        this.mediagenresid = mediagenresid;
        this.mediaid = mediaid;
        this.genreid = genreid;
    }

    /**
     * Getter for <code>harmony.mediaGenres.mediagenresid</code>.
     */
    public Integer getMediagenresid() {
        return this.mediagenresid;
    }

    /**
     * Setter for <code>harmony.mediaGenres.mediagenresid</code>.
     */
    public Mediagenres setMediagenresid(Integer mediagenresid) {
        this.mediagenresid = mediagenresid;
        return this;
    }

    /**
     * Getter for <code>harmony.mediaGenres.mediaid</code>.
     */
    public Integer getMediaid() {
        return this.mediaid;
    }

    /**
     * Setter for <code>harmony.mediaGenres.mediaid</code>.
     */
    public Mediagenres setMediaid(Integer mediaid) {
        this.mediaid = mediaid;
        return this;
    }

    /**
     * Getter for <code>harmony.mediaGenres.genreid</code>.
     */
    public Integer getGenreid() {
        return this.genreid;
    }

    /**
     * Setter for <code>harmony.mediaGenres.genreid</code>.
     */
    public Mediagenres setGenreid(Integer genreid) {
        this.genreid = genreid;
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
        final Mediagenres other = (Mediagenres) obj;
        if (mediagenresid == null) {
            if (other.mediagenresid != null)
                return false;
        }
        else if (!mediagenresid.equals(other.mediagenresid))
            return false;
        if (mediaid == null) {
            if (other.mediaid != null)
                return false;
        }
        else if (!mediaid.equals(other.mediaid))
            return false;
        if (genreid == null) {
            if (other.genreid != null)
                return false;
        }
        else if (!genreid.equals(other.genreid))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.mediagenresid == null) ? 0 : this.mediagenresid.hashCode());
        result = prime * result + ((this.mediaid == null) ? 0 : this.mediaid.hashCode());
        result = prime * result + ((this.genreid == null) ? 0 : this.genreid.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Mediagenres (");

        sb.append(mediagenresid);
        sb.append(", ").append(mediaid);
        sb.append(", ").append(genreid);

        sb.append(")");
        return sb.toString();
    }
}
