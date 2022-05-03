/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.harmony.tables.Normals;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NormalsRecord extends UpdatableRecordImpl<NormalsRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.normals.normalid</code>.
     */
    public NormalsRecord setNormalid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.normals.normalid</code>.
     */
    public Integer getNormalid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.normals.userid</code>.
     */
    public NormalsRecord setUserid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.normals.userid</code>.
     */
    public Integer getUserid() {
        return (Integer) get(1);
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
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Normals.NORMALS.NORMALID;
    }

    @Override
    public Field<Integer> field2() {
        return Normals.NORMALS.USERID;
    }

    @Override
    public Integer component1() {
        return getNormalid();
    }

    @Override
    public Integer component2() {
        return getUserid();
    }

    @Override
    public Integer value1() {
        return getNormalid();
    }

    @Override
    public Integer value2() {
        return getUserid();
    }

    @Override
    public NormalsRecord value1(Integer value) {
        setNormalid(value);
        return this;
    }

    @Override
    public NormalsRecord value2(Integer value) {
        setUserid(value);
        return this;
    }

    @Override
    public NormalsRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NormalsRecord
     */
    public NormalsRecord() {
        super(Normals.NORMALS);
    }

    /**
     * Create a detached, initialised NormalsRecord
     */
    public NormalsRecord(Integer normalid, Integer userid) {
        super(Normals.NORMALS);

        setNormalid(normalid);
        setUserid(userid);
    }
}
