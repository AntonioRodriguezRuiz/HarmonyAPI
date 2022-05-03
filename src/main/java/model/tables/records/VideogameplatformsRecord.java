/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.model.tables.Videogameplatforms;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VideogameplatformsRecord extends UpdatableRecordImpl<VideogameplatformsRecord> implements Record3<Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.videogameplatforms.videogamePlatformid</code>.
     */
    public VideogameplatformsRecord setVideogameplatformid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.videogameplatforms.videogamePlatformid</code>.
     */
    public Integer getVideogameplatformid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.videogameplatforms.videogameid</code>.
     */
    public VideogameplatformsRecord setVideogameid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.videogameplatforms.videogameid</code>.
     */
    public Integer getVideogameid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>harmony.videogameplatforms.platformid</code>.
     */
    public VideogameplatformsRecord setPlatformid(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>harmony.videogameplatforms.platformid</code>.
     */
    public Integer getPlatformid() {
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
        return Videogameplatforms.VIDEOGAMEPLATFORMS.VIDEOGAMEPLATFORMID;
    }

    @Override
    public Field<Integer> field2() {
        return Videogameplatforms.VIDEOGAMEPLATFORMS.VIDEOGAMEID;
    }

    @Override
    public Field<Integer> field3() {
        return Videogameplatforms.VIDEOGAMEPLATFORMS.PLATFORMID;
    }

    @Override
    public Integer component1() {
        return getVideogameplatformid();
    }

    @Override
    public Integer component2() {
        return getVideogameid();
    }

    @Override
    public Integer component3() {
        return getPlatformid();
    }

    @Override
    public Integer value1() {
        return getVideogameplatformid();
    }

    @Override
    public Integer value2() {
        return getVideogameid();
    }

    @Override
    public Integer value3() {
        return getPlatformid();
    }

    @Override
    public VideogameplatformsRecord value1(Integer value) {
        setVideogameplatformid(value);
        return this;
    }

    @Override
    public VideogameplatformsRecord value2(Integer value) {
        setVideogameid(value);
        return this;
    }

    @Override
    public VideogameplatformsRecord value3(Integer value) {
        setPlatformid(value);
        return this;
    }

    @Override
    public VideogameplatformsRecord values(Integer value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VideogameplatformsRecord
     */
    public VideogameplatformsRecord() {
        super(Videogameplatforms.VIDEOGAMEPLATFORMS);
    }

    /**
     * Create a detached, initialised VideogameplatformsRecord
     */
    public VideogameplatformsRecord(Integer videogameplatformid, Integer videogameid, Integer platformid) {
        super(Videogameplatforms.VIDEOGAMEPLATFORMS);

        setVideogameplatformid(videogameplatformid);
        setVideogameid(videogameid);
        setPlatformid(platformid);
    }
}
