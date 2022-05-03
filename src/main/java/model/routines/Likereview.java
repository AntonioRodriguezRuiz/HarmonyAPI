/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

import src.main.java.model.Harmony;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Likereview extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.likeReview.user</code>.
     */
    public static final Parameter<Integer> USER = Internal.createParameter("user", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>harmony.likeReview.review</code>.
     */
    public static final Parameter<Integer> REVIEW = Internal.createParameter("review", SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public Likereview() {
        super("likeReview", Harmony.HARMONY);

        addInParameter(USER);
        addInParameter(REVIEW);
    }

    /**
     * Set the <code>user</code> parameter IN value to the routine
     */
    public void setUser(Integer value) {
        setValue(USER, value);
    }

    /**
     * Set the <code>review</code> parameter IN value to the routine
     */
    public void setReview(Integer value) {
        setValue(REVIEW, value);
    }
}
