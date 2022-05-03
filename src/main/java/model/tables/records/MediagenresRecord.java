/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.model.tables.Mediagenres;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MediagenresRecord extends UpdatableRecordImpl<MediagenresRecord> implements Record3<Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.mediaGenres.mediagenresid</code>.
     */
    public MediagenresRecord setMediagenresid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.mediaGenres.mediagenresid</code>.
     */
    public Integer getMediagenresid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.mediaGenres.mediaid</code>.
     */
    public MediagenresRecord setMediaid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.mediaGenres.mediaid</code>.
     */
    public Integer getMediaid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>harmony.mediaGenres.genreid</code>.
     */
    public MediagenresRecord setGenreid(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>harmony.mediaGenres.genreid</code>.
     */
    public Integer getGenreid() {
        return (Integer) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Mediagenres.MEDIAGENRES.MEDIAGENRESID;
    }

    @Override
    public Field<Integer> field2() {
        return Mediagenres.MEDIAGENRES.MEDIAID;
    }

    @Override
    public Field<Integer> field3() {
        return Mediagenres.MEDIAGENRES.GENREID;
    }

    @Override
    public Integer component1() {
        return getMediagenresid();
    }

    @Override
    public Integer component2() {
        return getMediaid();
    }

    @Override
    public Integer component3() {
        return getGenreid();
    }

    @Override
    public Integer value1() {
        return getMediagenresid();
    }

    @Override
    public Integer value2() {
        return getMediaid();
    }

    @Override
    public Integer value3() {
        return getGenreid();
    }

    @Override
    public MediagenresRecord value1(Integer value) {
        setMediagenresid(value);
        return this;
    }

    @Override
    public MediagenresRecord value2(Integer value) {
        setMediaid(value);
        return this;
    }

    @Override
    public MediagenresRecord value3(Integer value) {
        setGenreid(value);
        return this;
    }

    @Override
    public MediagenresRecord values(Integer value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MediagenresRecord
     */
    public MediagenresRecord() {
        super(Mediagenres.MEDIAGENRES);
    }

    /**
     * Create a detached, initialised MediagenresRecord
     */
    public MediagenresRecord(Integer mediagenresid, Integer mediaid, Integer genreid) {
        super(Mediagenres.MEDIAGENRES);

        setMediagenresid(mediagenresid);
        setMediaid(mediaid);
        setGenreid(genreid);
    }
}
