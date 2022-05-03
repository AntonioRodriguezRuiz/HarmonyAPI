/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.model.tables.Platforms;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PlatformsRecord extends UpdatableRecordImpl<PlatformsRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.platforms.platformid</code>.
     */
    public PlatformsRecord setPlatformid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.platforms.platformid</code>.
     */
    public Integer getPlatformid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.platforms.platformName</code>.
     */
    public PlatformsRecord setPlatformname(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.platforms.platformName</code>.
     */
    public String getPlatformname() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Platforms.PLATFORMS.PLATFORMID;
    }

    @Override
    public Field<String> field2() {
        return Platforms.PLATFORMS.PLATFORMNAME;
    }

    @Override
    public Integer component1() {
        return getPlatformid();
    }

    @Override
    public String component2() {
        return getPlatformname();
    }

    @Override
    public Integer value1() {
        return getPlatformid();
    }

    @Override
    public String value2() {
        return getPlatformname();
    }

    @Override
    public PlatformsRecord value1(Integer value) {
        setPlatformid(value);
        return this;
    }

    @Override
    public PlatformsRecord value2(String value) {
        setPlatformname(value);
        return this;
    }

    @Override
    public PlatformsRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PlatformsRecord
     */
    public PlatformsRecord() {
        super(Platforms.PLATFORMS);
    }

    /**
     * Create a detached, initialised PlatformsRecord
     */
    public PlatformsRecord(Integer platformid, String platformname) {
        super(Platforms.PLATFORMS);

        setPlatformid(platformid);
        setPlatformname(platformname);
    }
}