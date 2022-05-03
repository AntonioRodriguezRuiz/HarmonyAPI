/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import src.main.java.harmony.Harmony;
import src.main.java.harmony.Keys;
import src.main.java.harmony.tables.records.ListsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Lists extends TableImpl<ListsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.lists</code>
     */
    public static final Lists LISTS = new Lists();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ListsRecord> getRecordType() {
        return ListsRecord.class;
    }

    /**
     * The column <code>harmony.lists.listid</code>.
     */
    public final TableField<ListsRecord, Integer> LISTID = createField(DSL.name("listid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.lists.userid</code>.
     */
    public final TableField<ListsRecord, Integer> USERID = createField(DSL.name("userid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.lists.listName</code>.
     */
    public final TableField<ListsRecord, String> LISTNAME = createField(DSL.name("listName"), SQLDataType.VARCHAR(60), this, "");

    /**
     * The column <code>harmony.lists.icon</code>.
     */
    public final TableField<ListsRecord, String> ICON = createField(DSL.name("icon"), SQLDataType.VARCHAR(1), this, "");

    /**
     * The column <code>harmony.lists.creationDate</code>.
     */
    public final TableField<ListsRecord, LocalDateTime> CREATIONDATE = createField(DSL.name("creationDate"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>harmony.lists.modificationDate</code>.
     */
    public final TableField<ListsRecord, LocalDateTime> MODIFICATIONDATE = createField(DSL.name("modificationDate"), SQLDataType.LOCALDATETIME(0), this, "");

    private Lists(Name alias, Table<ListsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Lists(Name alias, Table<ListsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.lists</code> table reference
     */
    public Lists(String alias) {
        this(DSL.name(alias), LISTS);
    }

    /**
     * Create an aliased <code>harmony.lists</code> table reference
     */
    public Lists(Name alias) {
        this(alias, LISTS);
    }

    /**
     * Create a <code>harmony.lists</code> table reference
     */
    public Lists() {
        this(DSL.name("lists"), null);
    }

    public <O extends Record> Lists(Table<O> child, ForeignKey<O, ListsRecord> key) {
        super(child, key, LISTS);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public Identity<ListsRecord, Integer> getIdentity() {
        return (Identity<ListsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ListsRecord> getPrimaryKey() {
        return Keys.KEY_LISTS_PRIMARY;
    }

    @Override
    public List<UniqueKey<ListsRecord>> getKeys() {
        return Arrays.<UniqueKey<ListsRecord>>asList(Keys.KEY_LISTS_PRIMARY, Keys.KEY_LISTS_LISTID, Keys.KEY_LISTS_INVALIDLISTNAME);
    }

    @Override
    public List<ForeignKey<ListsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ListsRecord, ?>>asList(Keys.LISTS_IBFK_1);
    }

    private transient Users _users;

    public Users users() {
        if (_users == null)
            _users = new Users(this, Keys.LISTS_IBFK_1);

        return _users;
    }

    @Override
    public Lists as(String alias) {
        return new Lists(DSL.name(alias), this);
    }

    @Override
    public Lists as(Name alias) {
        return new Lists(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Lists rename(String name) {
        return new Lists(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Lists rename(Name name) {
        return new Lists(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
