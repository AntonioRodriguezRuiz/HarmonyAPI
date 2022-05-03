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
public class Newlistmedia extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>harmony.newListMedia.list</code>.
     */
    public static final Parameter<Integer> LIST = Internal.createParameter("list", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>harmony.newListMedia.media</code>.
     */
    public static final Parameter<Integer> MEDIA = Internal.createParameter("media", SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public Newlistmedia() {
        super("newListMedia", Harmony.HARMONY);

        addInParameter(LIST);
        addInParameter(MEDIA);
    }

    /**
     * Set the <code>list</code> parameter IN value to the routine
     */
    public void setList(Integer value) {
        setValue(LIST, value);
    }

    /**
     * Set the <code>media</code> parameter IN value to the routine
     */
    public void setMedia(Integer value) {
        setValue(MEDIA, value);
    }
}
