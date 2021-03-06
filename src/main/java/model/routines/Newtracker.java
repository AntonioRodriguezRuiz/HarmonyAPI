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
public class Newtracker extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.newTracker.media</code>.
     */
    public static final Parameter<Integer> MEDIA = Internal.createParameter("media", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>harmony.newTracker.user</code>.
     */
    public static final Parameter<Integer> USER = Internal.createParameter("user", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>harmony.newTracker.state</code>.
     */
    public static final Parameter<Integer> STATE = Internal.createParameter("state", SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public Newtracker() {
        super("newTracker", Harmony.HARMONY);

        addInParameter(MEDIA);
        addInParameter(USER);
        addInParameter(STATE);
    }

    /**
     * Set the <code>media</code> parameter IN value to the routine
     */
    public void setMedia(Integer value) {
        setValue(MEDIA, value);
    }

    /**
     * Set the <code>user</code> parameter IN value to the routine
     */
    public void setUser(Integer value) {
        setValue(USER, value);
    }

    /**
     * Set the <code>state</code> parameter IN value to the routine
     */
    public void setState(Integer value) {
        setValue(STATE, value);
    }
}
