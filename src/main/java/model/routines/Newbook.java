/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import src.main.java.model.Harmony;

import java.time.LocalDate;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Newbook extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.newBook.title</code>.
     */
    public static final Parameter<String> TITLE = Internal.createParameter("title", SQLDataType.VARCHAR(500), false, false);

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
    public static final Parameter<String> SYNOPSIS = Internal.createParameter("synopsis", SQLDataType.CLOB, false, false);

    /**
     * The parameter <code>harmony.newBook.externalId</code>.
     */
    public static final Parameter<Integer> EXTERNALID = Internal.createParameter("externalId", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>harmony.newBook.collection</code>.
     */
    public static final Parameter<String> COLLECTION = Internal.createParameter("collection", SQLDataType.VARCHAR(120), false, false);

    /**
     * The parameter <code>harmony.newBook.number</code>.
     */
    public static final Parameter<Integer> NUMBER = Internal.createParameter("number", SQLDataType.INTEGER, false, false);

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
        addInParameter(EXTERNALID);
        addInParameter(COLLECTION);
        addInParameter(NUMBER);
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
     * Set the <code>externalId</code> parameter IN value to the routine
     */
    public void setExternalid(Integer value) {
        setValue(EXTERNALID, value);
    }

    /**
     * Set the <code>collection</code> parameter IN value to the routine
     */
    public void setCollection(String value) {
        setValue(COLLECTION, value);
    }

    /**
     * Set the <code>number</code> parameter IN value to the routine
     */
    public void setNumber(Integer value) {
        setValue(NUMBER, value);
    }
}
