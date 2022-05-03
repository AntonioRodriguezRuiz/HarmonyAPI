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
import src.main.java.model.tables.records.SeriesRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Series extends TableImpl<SeriesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.series</code>
     */
    public static final Series SERIES = new Series();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SeriesRecord> getRecordType() {
        return SeriesRecord.class;
    }

    /**
     * The column <code>harmony.series.seriesid</code>.
     */
    public final TableField<SeriesRecord, Integer> SERIESID = createField(DSL.name("seriesid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.series.mediaid</code>.
     */
    public final TableField<SeriesRecord, Integer> MEDIAID = createField(DSL.name("mediaid"), SQLDataType.INTEGER.nullable(false), this, "");

    private Series(Name alias, Table<SeriesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Series(Name alias, Table<SeriesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.series</code> table reference
     */
    public Series(String alias) {
        this(DSL.name(alias), SERIES);
    }

    /**
     * Create an aliased <code>harmony.series</code> table reference
     */
    public Series(Name alias) {
        this(alias, SERIES);
    }

    /**
     * Create a <code>harmony.series</code> table reference
     */
    public Series() {
        this(DSL.name("series"), null);
    }

    public <O extends Record> Series(Table<O> child, ForeignKey<O, SeriesRecord> key) {
        super(child, key, SERIES);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public Identity<SeriesRecord, Integer> getIdentity() {
        return (Identity<SeriesRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<SeriesRecord> getPrimaryKey() {
        return Keys.KEY_SERIES_PRIMARY;
    }

    @Override
    public List<UniqueKey<SeriesRecord>> getKeys() {
        return Arrays.<UniqueKey<SeriesRecord>>asList(Keys.KEY_SERIES_PRIMARY, Keys.KEY_SERIES_SERIESID, Keys.KEY_SERIES_MEDIAID);
    }

    @Override
    public List<ForeignKey<SeriesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<SeriesRecord, ?>>asList(Keys.SERIES_IBFK_1);
    }

    private transient Media _media;

    public Media media() {
        if (_media == null)
            _media = new Media(this, Keys.SERIES_IBFK_1);

        return _media;
    }

    @Override
    public Series as(String alias) {
        return new Series(DSL.name(alias), this);
    }

    @Override
    public Series as(Name alias) {
        return new Series(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Series rename(String name) {
        return new Series(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Series rename(Name name) {
        return new Series(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
