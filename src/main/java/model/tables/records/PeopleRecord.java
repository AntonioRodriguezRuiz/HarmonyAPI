/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.records;


import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.model.tables.People;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PeopleRecord extends UpdatableRecordImpl<PeopleRecord> implements Record4<Integer, String, LocalDate, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.people.personid</code>.
     */
    public PeopleRecord setPersonid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.people.personid</code>.
     */
    public Integer getPersonid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.people.name</code>.
     */
    public PeopleRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.people.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>harmony.people.birthdate</code>.
     */
    public PeopleRecord setBirthdate(LocalDate value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>harmony.people.birthdate</code>.
     */
    public LocalDate getBirthdate() {
        return (LocalDate) get(2);
    }

    /**
     * Setter for <code>harmony.people.picture</code>.
     */
    public PeopleRecord setPicture(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>harmony.people.picture</code>.
     */
    public String getPicture() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, LocalDate, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, LocalDate, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return People.PEOPLE.PERSONID;
    }

    @Override
    public Field<String> field2() {
        return People.PEOPLE.NAME;
    }

    @Override
    public Field<LocalDate> field3() {
        return People.PEOPLE.BIRTHDATE;
    }

    @Override
    public Field<String> field4() {
        return People.PEOPLE.PICTURE;
    }

    @Override
    public Integer component1() {
        return getPersonid();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public LocalDate component3() {
        return getBirthdate();
    }

    @Override
    public String component4() {
        return getPicture();
    }

    @Override
    public Integer value1() {
        return getPersonid();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public LocalDate value3() {
        return getBirthdate();
    }

    @Override
    public String value4() {
        return getPicture();
    }

    @Override
    public PeopleRecord value1(Integer value) {
        setPersonid(value);
        return this;
    }

    @Override
    public PeopleRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public PeopleRecord value3(LocalDate value) {
        setBirthdate(value);
        return this;
    }

    @Override
    public PeopleRecord value4(String value) {
        setPicture(value);
        return this;
    }

    @Override
    public PeopleRecord values(Integer value1, String value2, LocalDate value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PeopleRecord
     */
    public PeopleRecord() {
        super(People.PEOPLE);
    }

    /**
     * Create a detached, initialised PeopleRecord
     */
    public PeopleRecord(Integer personid, String name, LocalDate birthdate, String picture) {
        super(People.PEOPLE);

        setPersonid(personid);
        setName(name);
        setBirthdate(birthdate);
        setPicture(picture);
    }
}
