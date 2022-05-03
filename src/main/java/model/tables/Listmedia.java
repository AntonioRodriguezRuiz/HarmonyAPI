/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import src.main.java.model.Harmony;
import src.main.java.model.Indexes;
import src.main.java.model.Keys;
import src.main.java.model.tables.records.ListmediaRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Listmedia extends TableImpl<ListmediaRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.listmedia</code>
     */
    public static final Listmedia LISTMEDIA = new Listmedia();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ListmediaRecord> getRecordType() {
        return ListmediaRecord.class;
    }

    /**
     * The column <code>harmony.listmedia.elementid</code>.
     */
    public final TableField<ListmediaRecord, Integer> ELEMENTID = createField(DSL.name("elementid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.listmedia.listid</code>.
     */
    public final TableField<ListmediaRecord, Integer> LISTID = createField(DSL.name("listid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.listmedia.mediaid</code>.
     */
    public final TableField<ListmediaRecord, Integer> MEDIAID = createField(DSL.name("mediaid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.listmedia.addedDate</code>.
     */
    public final TableField<ListmediaRecord, LocalDateTime> ADDEDDATE = createField(DSL.name("addedDate"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    private Listmedia(Name alias, Table<ListmediaRecord> aliased) {
        this(alias, aliased, null);
    }

    private Listmedia(Name alias, Table<ListmediaRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.listmedia</code> table reference
     */
    public Listmedia(String alias) {
        this(DSL.name(alias), LISTMEDIA);
    }

    /**
     * Create an aliased <code>harmony.listmedia</code> table reference
     */
    public Listmedia(Name alias) {
        this(alias, LISTMEDIA);
    }

    /**
     * Create a <code>harmony.listmedia</code> table reference
     */
    public Listmedia() {
        this(DSL.name("listmedia"), null);
    }

    public <O extends Record> Listmedia(Table<O> child, ForeignKey<O, ListmediaRecord> key) {
        super(child, key, LISTMEDIA);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.LISTMEDIA_MEDIAID);
    }

    @Override
    public Identity<ListmediaRecord, Integer> getIdentity() {
        return (Identity<ListmediaRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ListmediaRecord> getPrimaryKey() {
        return Keys.KEY_LISTMEDIA_PRIMARY;
    }

    @Override
    public List<UniqueKey<ListmediaRecord>> getKeys() {
        return Arrays.<UniqueKey<ListmediaRecord>>asList(Keys.KEY_LISTMEDIA_PRIMARY, Keys.KEY_LISTMEDIA_ELEMENTID, Keys.KEY_LISTMEDIA_DUPLICATEDENTRY_LISTMEDIA);
    }

    @Override
    public List<ForeignKey<ListmediaRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ListmediaRecord, ?>>asList(Keys.LISTMEDIA_IBFK_1, Keys.LISTMEDIA_IBFK_2);
    }

    private transient Lists _lists;
    private transient Media _media;

    public Lists lists() {
        if (_lists == null)
            _lists = new Lists(this, Keys.LISTMEDIA_IBFK_1);

        return _lists;
    }

    public Media media() {
        if (_media == null)
            _media = new Media(this, Keys.LISTMEDIA_IBFK_2);

        return _media;
    }

    @Override
    public Listmedia as(String alias) {
        return new Listmedia(DSL.name(alias), this);
    }

    @Override
    public Listmedia as(Name alias) {
        return new Listmedia(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Listmedia rename(String name) {
        return new Listmedia(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Listmedia rename(Name name) {
        return new Listmedia(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
