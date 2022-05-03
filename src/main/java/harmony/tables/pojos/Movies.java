/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Movies implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer movieid;
    private Integer mediaid;

    public Movies() {}

    public Movies(Movies value) {
        this.movieid = value.movieid;
        this.mediaid = value.mediaid;
    }

    public Movies(
        Integer movieid,
        Integer mediaid
    ) {
        this.movieid = movieid;
        this.mediaid = mediaid;
    }

    /**
     * Getter for <code>harmony.movies.movieid</code>.
     */
    public Integer getMovieid() {
        return this.movieid;
    }

    /**
     * Setter for <code>harmony.movies.movieid</code>.
     */
    public Movies setMovieid(Integer movieid) {
        this.movieid = movieid;
        return this;
    }

    /**
     * Getter for <code>harmony.movies.mediaid</code>.
     */
    public Integer getMediaid() {
        return this.mediaid;
    }

    /**
     * Setter for <code>harmony.movies.mediaid</code>.
     */
    public Movies setMediaid(Integer mediaid) {
        this.mediaid = mediaid;
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
        final Movies other = (Movies) obj;
        if (movieid == null) {
            if (other.movieid != null)
                return false;
        }
        else if (!movieid.equals(other.movieid))
            return false;
        if (mediaid == null) {
            if (other.mediaid != null)
                return false;
        }
        else if (!mediaid.equals(other.mediaid))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.movieid == null) ? 0 : this.movieid.hashCode());
        result = prime * result + ((this.mediaid == null) ? 0 : this.mediaid.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Movies (");

        sb.append(movieid);
        sb.append(", ").append(mediaid);

        sb.append(")");
        return sb.toString();
    }
}
