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
import src.main.java.model.tables.records.GenresRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Genres extends TableImpl<GenresRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.genres</code>
     */
    public static final Genres GENRES = new Genres();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GenresRecord> getRecordType() {
        return GenresRecord.class;
    }

    /**
     * The column <code>harmony.genres.genreid</code>.
     */
    public final TableField<GenresRecord, Integer> GENREID = createField(DSL.name("genreid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.genres.name</code>.
     */
    public final TableField<GenresRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    private Genres(Name alias, Table<GenresRecord> aliased) {
        this(alias, aliased, null);
    }

    private Genres(Name alias, Table<GenresRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.genres</code> table reference
     */
    public Genres(String alias) {
        this(DSL.name(alias), GENRES);
    }

    /**
     * Create an aliased <code>harmony.genres</code> table reference
     */
    public Genres(Name alias) {
        this(alias, GENRES);
    }

    /**
     * Create a <code>harmony.genres</code> table reference
     */
    public Genres() {
        this(DSL.name("genres"), null);
    }

    public <O extends Record> Genres(Table<O> child, ForeignKey<O, GenresRecord> key) {
        super(child, key, GENRES);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public Identity<GenresRecord, Integer> getIdentity() {
        return (Identity<GenresRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<GenresRecord> getPrimaryKey() {
        return Keys.KEY_GENRES_PRIMARY;
    }

    @Override
    public List<UniqueKey<GenresRecord>> getKeys() {
        return Arrays.<UniqueKey<GenresRecord>>asList(Keys.KEY_GENRES_PRIMARY, Keys.KEY_GENRES_GENREID, Keys.KEY_GENRES_NAME);
    }

    @Override
    public Genres as(String alias) {
        return new Genres(DSL.name(alias), this);
    }

    @Override
    public Genres as(Name alias) {
        return new Genres(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Genres rename(String name) {
        return new Genres(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Genres rename(Name name) {
        return new Genres(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}