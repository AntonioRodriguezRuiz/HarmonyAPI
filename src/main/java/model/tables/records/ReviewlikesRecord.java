/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.model.tables.Reviewlikes;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ReviewlikesRecord extends UpdatableRecordImpl<ReviewlikesRecord> implements Record3<Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.reviewlikes.reviewlikeid</code>.
     */
    public ReviewlikesRecord setReviewlikeid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviewlikes.reviewlikeid</code>.
     */
    public Integer getReviewlikeid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.reviewlikes.userid</code>.
     */
    public ReviewlikesRecord setUserid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviewlikes.userid</code>.
     */
    public Integer getUserid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>harmony.reviewlikes.reviewid</code>.
     */
    public ReviewlikesRecord setReviewid(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviewlikes.reviewid</code>.
     */
    public Integer getReviewid() {
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
        return Reviewlikes.REVIEWLIKES.REVIEWLIKEID;
    }

    @Override
    public Field<Integer> field2() {
        return Reviewlikes.REVIEWLIKES.USERID;
    }

    @Override
    public Field<Integer> field3() {
        return Reviewlikes.REVIEWLIKES.REVIEWID;
    }

    @Override
    public Integer component1() {
        return getReviewlikeid();
    }

    @Override
    public Integer component2() {
        return getUserid();
    }

    @Override
    public Integer component3() {
        return getReviewid();
    }

    @Override
    public Integer value1() {
        return getReviewlikeid();
    }

    @Override
    public Integer value2() {
        return getUserid();
    }

    @Override
    public Integer value3() {
        return getReviewid();
    }

    @Override
    public ReviewlikesRecord value1(Integer value) {
        setReviewlikeid(value);
        return this;
    }

    @Override
    public ReviewlikesRecord value2(Integer value) {
        setUserid(value);
        return this;
    }

    @Override
    public ReviewlikesRecord value3(Integer value) {
        setReviewid(value);
        return this;
    }

    @Override
    public ReviewlikesRecord values(Integer value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ReviewlikesRecord
     */
    public ReviewlikesRecord() {
        super(Reviewlikes.REVIEWLIKES);
    }

    /**
     * Create a detached, initialised ReviewlikesRecord
     */
    public ReviewlikesRecord(Integer reviewlikeid, Integer userid, Integer reviewid) {
        super(Reviewlikes.REVIEWLIKES);

        setReviewlikeid(reviewlikeid);
        setUserid(userid);
        setReviewid(reviewid);
    }
}
