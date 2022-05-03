/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

import src.main.java.harmony.Harmony;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Newreview extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.newReview.user</code>.
     */
    public static final Parameter<Integer> USER = Internal.createParameter("user", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>harmony.newReview.media</code>.
     */
    public static final Parameter<Integer> MEDIA = Internal.createParameter("media", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>harmony.newReview.rating</code>.
     */
    public static final Parameter<Double> RATING = Internal.createParameter("rating", SQLDataType.FLOAT, false, false);

    /**
     * The parameter <code>harmony.newReview.review</code>.
     */
    public static final Parameter<String> REVIEW = Internal.createParameter("review", SQLDataType.VARCHAR(2800), false, false);

    /**
     * Create a new routine call instance
     */
    public Newreview() {
        super("newReview", Harmony.HARMONY);

        addInParameter(USER);
        addInParameter(MEDIA);
        addInParameter(RATING);
        addInParameter(REVIEW);
    }

    /**
     * Set the <code>user</code> parameter IN value to the routine
     */
    public void setUser(Integer value) {
        setValue(USER, value);
    }

    /**
     * Set the <code>media</code> parameter IN value to the routine
     */
    public void setMedia(Integer value) {
        setValue(MEDIA, value);
    }

    /**
     * Set the <code>rating</code> parameter IN value to the routine
     */
    public void setRating(Double value) {
        setValue(RATING, value);
    }

    /**
     * Set the <code>review</code> parameter IN value to the routine
     */
    public void setReview(String value) {
        setValue(REVIEW, value);
    }
}
