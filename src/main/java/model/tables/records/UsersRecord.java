/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.records;


import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.model.tables.Users;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersRecord extends UpdatableRecordImpl<UsersRecord> implements Record5<Integer, String, String, String, LocalDate> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.users.userid</code>.
     */
    public UsersRecord setUserid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.users.userid</code>.
     */
    public Integer getUserid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.users.username</code>.
     */
    public UsersRecord setUsername(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.users.username</code>.
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>harmony.users.email</code>.
     */
    public UsersRecord setEmail(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>harmony.users.email</code>.
     */
    public String getEmail() {
        return (String) get(2);
    }

    /**
     * Setter for <code>harmony.users.password</code>.
     */
    public UsersRecord setPassword(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>harmony.users.password</code>.
     */
    public String getPassword() {
        return (String) get(3);
    }

    /**
     * Setter for <code>harmony.users.creationDate</code>.
     */
    public UsersRecord setCreationdate(LocalDate value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>harmony.users.creationDate</code>.
     */
    public LocalDate getCreationdate() {
        return (LocalDate) get(4);
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
    public Row5<Integer, String, String, String, LocalDate> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, String, String, String, LocalDate> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Users.USERS.USERID;
    }

    @Override
    public Field<String> field2() {
        return Users.USERS.USERNAME;
    }

    @Override
    public Field<String> field3() {
        return Users.USERS.EMAIL;
    }

    @Override
    public Field<String> field4() {
        return Users.USERS.PASSWORD;
    }

    @Override
    public Field<LocalDate> field5() {
        return Users.USERS.CREATIONDATE;
    }

    @Override
    public Integer component1() {
        return getUserid();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public String component3() {
        return getEmail();
    }

    @Override
    public String component4() {
        return getPassword();
    }

    @Override
    public LocalDate component5() {
        return getCreationdate();
    }

    @Override
    public Integer value1() {
        return getUserid();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public String value3() {
        return getEmail();
    }

    @Override
    public String value4() {
        return getPassword();
    }

    @Override
    public LocalDate value5() {
        return getCreationdate();
    }

    @Override
    public UsersRecord value1(Integer value) {
        setUserid(value);
        return this;
    }

    @Override
    public UsersRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public UsersRecord value3(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UsersRecord value4(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UsersRecord value5(LocalDate value) {
        setCreationdate(value);
        return this;
    }

    @Override
    public UsersRecord values(Integer value1, String value2, String value3, String value4, LocalDate value5) {
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
     * Create a detached UsersRecord
     */
    public UsersRecord() {
        super(Users.USERS);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(Integer userid, String username, String email, String password, LocalDate creationdate) {
        super(Users.USERS);

        setUserid(userid);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCreationdate(creationdate);
    }
}
