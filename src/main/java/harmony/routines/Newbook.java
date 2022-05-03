/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.routines;


import java.time.LocalDate;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

import src.main.java.harmony.Harmony;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Newbook extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.newBook.title</code>.
     */
    public static final Parameter<String> TITLE = Internal.createParameter("title", SQLDataType.VARCHAR(120), false, false);

    /**
     * The parameter <code>harmony.newBook.releaseDate</code>.
     */
    public static final Parameter<LocalDate> RELEASEDATE = Internal.createParameter("releaseDate", SQLDataType.LOCALDATE, false, false);

    /**
     * The parameter <code>harmony.newBook.coverImage</code>.
     */
    public static final Parameter<String> COVERIMAGE = Internal.createParameter("coverImage", SQLDataType.VARCHAR(120), false, false);

    /**
     * The parameter <code>harmony.newBook.backgroundImage</code>.
     */
    public static final Parameter<String> BACKGROUNDIMAGE = Internal.createParameter("backgroundImage", SQLDataType.VARCHAR(120), false, false);

    /**
     * The parameter <code>harmony.newBook.synopsis</code>.
     */
    public static final Parameter<String> SYNOPSIS = Internal.createParameter("synopsis", SQLDataType.VARCHAR(1500), false, false);

    /**
     * The parameter <code>harmony.newBook.collection</code>.
     */
    public static final Parameter<String> COLLECTION = Internal.createParameter("collection", SQLDataType.VARCHAR(120), false, false);

    /**
     * Create a new routine call instance
     */
    public Newbook() {
        super("newBook", Harmony.HARMONY);

        addInParameter(TITLE);
        addInParameter(RELEASEDATE);
        addInParameter(COVERIMAGE);
        addInParameter(BACKGROUNDIMAGE);
        addInParameter(SYNOPSIS);
        addInParameter(COLLECTION);
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
     * Set the <code>coverImage</code> parameter IN value to the routine
     */
    public void setCoverimage(String value) {
        setValue(COVERIMAGE, value);
    }

    /**
     * Set the <code>backgroundImage</code> parameter IN value to the routine
     */
    public void setBackgroundimage(String value) {
        setValue(BACKGROUNDIMAGE, value);
    }

    /**
     * Set the <code>synopsis</code> parameter IN value to the routine
     */
    public void setSynopsis(String value) {
        setValue(SYNOPSIS, value);
    }

    /**
     * Set the <code>collection</code> parameter IN value to the routine
     */
    public void setCollection(String value) {
        setValue(COLLECTION, value);
    }
}
