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
public class Newepisodebyid extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.newEpisodeById.seasonid</code>.
     */
    public static final Parameter<Integer> SEASONID = Internal.createParameter("seasonid", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>harmony.newEpisodeById.episodeName</code>.
     */
    public static final Parameter<String> EPISODENAME = Internal.createParameter("episodeName", SQLDataType.VARCHAR(60), false, false);

    /**
     * The parameter <code>harmony.newEpisodeById.episodeNo</code>.
     */
    public static final Parameter<Integer> EPISODENO = Internal.createParameter("episodeNo", SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public Newepisodebyid() {
        super("newEpisodeById", Harmony.HARMONY);

        addInParameter(SEASONID);
        addInParameter(EPISODENAME);
        addInParameter(EPISODENO);
    }

    /**
     * Set the <code>seasonid</code> parameter IN value to the routine
     */
    public void setSeasonid(Integer value) {
        setValue(SEASONID, value);
    }

    /**
     * Set the <code>episodeName</code> parameter IN value to the routine
     */
    public void setEpisodename(String value) {
        setValue(EPISODENAME, value);
    }

    /**
     * Set the <code>episodeNo</code> parameter IN value to the routine
     */
    public void setEpisodeno(Integer value) {
        setValue(EPISODENO, value);
    }
}
