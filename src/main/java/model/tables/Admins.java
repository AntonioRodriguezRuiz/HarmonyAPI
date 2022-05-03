/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import src.main.java.model.Harmony;
import src.main.java.model.Keys;
import src.main.java.model.tables.records.AdminsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Admins extends TableImpl<AdminsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.admins</code>
     */
    public static final Admins ADMINS = new Admins();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AdminsRecord> getRecordType() {
        return AdminsRecord.class;
    }

    /**
     * The column <code>harmony.admins.adminid</code>.
     */
    public final TableField<AdminsRecord, Integer> ADMINID = createField(DSL.name("adminid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.admins.userid</code>.
     */
    public final TableField<AdminsRecord, Integer> USERID = createField(DSL.name("userid"), SQLDataType.INTEGER.nullable(false), this, "");

    private Admins(Name alias, Table<AdminsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Admins(Name alias, Table<AdminsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.admins</code> table reference
     */
    public Admins(String alias) {
        this(DSL.name(alias), ADMINS);
    }

    /**
     * Create an aliased <code>harmony.admins</code> table reference
     */
    public Admins(Name alias) {
        this(alias, ADMINS);
    }

    /**
     * Create a <code>harmony.admins</code> table reference
     */
    public Admins() {
        this(DSL.name("admins"), null);
    }

    public <O extends Record> Admins(Table<O> child, ForeignKey<O, AdminsRecord> key) {
        super(child, key, ADMINS);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public Identity<AdminsRecord, Integer> getIdentity() {
        return (Identity<AdminsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<AdminsRecord> getPrimaryKey() {
        return Keys.KEY_ADMINS_PRIMARY;
    }

    @Override
    public List<UniqueKey<AdminsRecord>> getKeys() {
        return Arrays.<UniqueKey<AdminsRecord>>asList(Keys.KEY_ADMINS_PRIMARY, Keys.KEY_ADMINS_ADMINID, Keys.KEY_ADMINS_USERID);
    }

    @Override
    public List<ForeignKey<AdminsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AdminsRecord, ?>>asList(Keys.ADMINS_IBFK_1);
    }

    private transient Users _users;

    public Users users() {
        if (_users == null)
            _users = new Users(this, Keys.ADMINS_IBFK_1);

        return _users;
    }

    @Override
    public Admins as(String alias) {
        return new Admins(DSL.name(alias), this);
    }

    @Override
    public Admins as(Name alias) {
        return new Admins(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Admins rename(String name) {
        return new Admins(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Admins rename(Name name) {
        return new Admins(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}