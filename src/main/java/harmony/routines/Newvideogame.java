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
public class Newvideogame extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.newVideogame.title</code>.
     */
    public static final Parameter<String> TITLE = Internal.createParameter("title", SQLDataType.VARCHAR(120), false, false);

    /**
     * The parameter <code>harmony.newVideogame.releaseDate</code>.
     */
    public static final Parameter<LocalDate> RELEASEDATE = Internal.createParameter("releaseDate", SQLDataType.LOCALDATE, false, false);

    /**
     * The parameter <code>harmony.newVideogame.coverImage</code>.
     */
    public static final Parameter<String> COVERIMAGE = Internal.createParameter("coverImage", SQLDataType.VARCHAR(120), false, false);

    /**
     * The parameter <code>harmony.newVideogame.backgroundImage</code>.
     */
    public static final Parameter<String> BACKGROUNDIMAGE = Internal.createParameter("backgroundImage", SQLDataType.VARCHAR(120), false, false);

    /**
     * The parameter <code>harmony.newVideogame.synopsis</code>.
     */
    public static final Parameter<String> SYNOPSIS = Internal.createParameter("synopsis", SQLDataType.VARCHAR(1500), false, false);

    /**
     * The parameter <code>harmony.newVideogame.company</code>.
     */
    public static final Parameter<String> COMPANY = Internal.createParameter("company", SQLDataType.VARCHAR(60), false, false);

    /**
     * Create a new routine call instance
     */
    public Newvideogame() {
        super("newVideogame", Harmony.HARMONY);

        addInParameter(TITLE);
        addInParameter(RELEASEDATE);
        addInParameter(COVERIMAGE);
        addInParameter(BACKGROUNDIMAGE);
        addInParameter(SYNOPSIS);
        addInParameter(COMPANY);
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
     * Set the <code>company</code> parameter IN value to the routine
     */
    public void setCompany(String value) {
        setValue(COMPANY, value);
    }
}
