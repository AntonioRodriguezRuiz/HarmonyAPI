/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.harmony.tables.Peopleepisodes;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PeopleepisodesRecord extends UpdatableRecordImpl<PeopleepisodesRecord> implements Record5<Integer, Integer, Integer, String, Byte> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.peopleEpisodes.peopleEpisodesid</code>.
     */
    public PeopleepisodesRecord setPeopleepisodesid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.peopleEpisodesid</code>.
     */
    public Integer getPeopleepisodesid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.episodeid</code>.
     */
    public PeopleepisodesRecord setEpisodeid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.episodeid</code>.
     */
    public Integer getEpisodeid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.personid</code>.
     */
    public PeopleepisodesRecord setPersonid(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.personid</code>.
     */
    public Integer getPersonid() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.role</code>.
     */
    public PeopleepisodesRecord setRole(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.role</code>.
     */
    public String getRole() {
        return (String) get(3);
    }

    /**
     * Setter for <code>harmony.peopleEpisodes.roletype</code>.
     */
    public PeopleepisodesRecord setRoletype(Byte value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>harmony.peopleEpisodes.roletype</code>.
     */
    public Byte getRoletype() {
        return (Byte) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, Integer, String, Byte> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, Integer, Integer, String, Byte> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Peopleepisodes.PEOPLEEPISODES.PEOPLEEPISODESID;
    }

    @Override
    public Field<Integer> field2() {
        return Peopleepisodes.PEOPLEEPISODES.EPISODEID;
    }

    @Override
    public Field<Integer> field3() {
        return Peopleepisodes.PEOPLEEPISODES.PERSONID;
    }

    @Override
    public Field<String> field4() {
        return Peopleepisodes.PEOPLEEPISODES.ROLE;
    }

    @Override
    public Field<Byte> field5() {
        return Peopleepisodes.PEOPLEEPISODES.ROLETYPE;
    }

    @Override
    public Integer component1() {
        return getPeopleepisodesid();
    }

    @Override
    public Integer component2() {
        return getEpisodeid();
    }

    @Override
    public Integer component3() {
        return getPersonid();
    }

    @Override
    public String component4() {
        return getRole();
    }

    @Override
    public Byte component5() {
        return getRoletype();
    }

    @Override
    public Integer value1() {
        return getPeopleepisodesid();
    }

    @Override
    public Integer value2() {
        return getEpisodeid();
    }

    @Override
    public Integer value3() {
        return getPersonid();
    }

    @Override
    public String value4() {
        return getRole();
    }

    @Override
    public Byte value5() {
        return getRoletype();
    }

    @Override
    public PeopleepisodesRecord value1(Integer value) {
        setPeopleepisodesid(value);
        return this;
    }

    @Override
    public PeopleepisodesRecord value2(Integer value) {
        setEpisodeid(value);
        return this;
    }

    @Override
    public PeopleepisodesRecord value3(Integer value) {
        setPersonid(value);
        return this;
    }

    @Override
    public PeopleepisodesRecord value4(String value) {
        setRole(value);
        return this;
    }

    @Override
    public PeopleepisodesRecord value5(Byte value) {
        setRoletype(value);
        return this;
    }

    @Override
    public PeopleepisodesRecord values(Integer value1, Integer value2, Integer value3, String value4, Byte value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PeopleepisodesRecord
     */
    public PeopleepisodesRecord() {
        super(Peopleepisodes.PEOPLEEPISODES);
    }

    /**
     * Create a detached, initialised PeopleepisodesRecord
     */
    public PeopleepisodesRecord(Integer peopleepisodesid, Integer episodeid, Integer personid, String role, Byte roletype) {
        super(Peopleepisodes.PEOPLEEPISODES);

        setPeopleepisodesid(peopleepisodesid);
        setEpisodeid(episodeid);
        setPersonid(personid);
        setRole(role);
        setRoletype(roletype);
    }
}
