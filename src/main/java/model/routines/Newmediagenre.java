/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.routines;


import java.time.LocalDate;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

import src.main.java.model.Harmony;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Newmediagenre extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.newMediaGenre.title</code>.
     */
    public static final Parameter<String> TITLE = Internal.createParameter("title", SQLDataType.VARCHAR(120), false, false);

    /**
     * The parameter <code>harmony.newMediaGenre.releaseDate</code>.
     */
    public static final Parameter<LocalDate> RELEASEDATE = Internal.createParameter("releaseDate", SQLDataType.LOCALDATE, false, false);

    /**
     * The parameter <code>harmony.newMediaGenre.genre</code>.
     */
    public static final Parameter<String> GENRE = Internal.createParameter("genre", SQLDataType.VARCHAR(50), false, false);

    /**
     * Create a new routine call instance
     */
    public Newmediagenre() {
        super("newMediaGenre", Harmony.HARMONY);

        addInParameter(TITLE);
        addInParameter(RELEASEDATE);
        addInParameter(GENRE);
    }

    /**
     * Set the <code>title</code> parameter IN value to the routine
     */
    public void setTitle(String value) {
        setValue(TITLE, value);
    }

    /**
     * Set the <code>releaseDate</code> parameter IN value to the routine
     */
    public void setReleasedate(LocalDate value) {
        setValue(RELEASEDATE, value);
    }

    /**
     * Set the <code>genre</code> parameter IN value to the routine
     */
    public void setGenre(String value) {
        setValue(GENRE, value);
    }
}