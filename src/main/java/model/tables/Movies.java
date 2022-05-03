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
import src.main.java.model.tables.records.MoviesRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Movies extends TableImpl<MoviesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.movies</code>
     */
    public static final Movies MOVIES = new Movies();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MoviesRecord> getRecordType() {
        return MoviesRecord.class;
    }

    /**
     * The column <code>harmony.movies.movieid</code>.
     */
    public final TableField<MoviesRecord, Integer> MOVIEID = createField(DSL.name("movieid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.movies.mediaid</code>.
     */
    public final TableField<MoviesRecord, Integer> MEDIAID = createField(DSL.name("mediaid"), SQLDataType.INTEGER.nullable(false), this, "");

    private Movies(Name alias, Table<MoviesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Movies(Name alias, Table<MoviesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.movies</code> table reference
     */
    public Movies(String alias) {
        this(DSL.name(alias), MOVIES);
    }

    /**
     * Create an aliased <code>harmony.movies</code> table reference
     */
    public Movies(Name alias) {
        this(alias, MOVIES);
    }

    /**
     * Create a <code>harmony.movies</code> table reference
     */
    public Movies() {
        this(DSL.name("movies"), null);
    }

    public <O extends Record> Movies(Table<O> child, ForeignKey<O, MoviesRecord> key) {
        super(child, key, MOVIES);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public Identity<MoviesRecord, Integer> getIdentity() {
        return (Identity<MoviesRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<MoviesRecord> getPrimaryKey() {
        return Keys.KEY_MOVIES_PRIMARY;
    }

    @Override
    public List<UniqueKey<MoviesRecord>> getKeys() {
        return Arrays.<UniqueKey<MoviesRecord>>asList(Keys.KEY_MOVIES_PRIMARY, Keys.KEY_MOVIES_MEDIAID);
    }

    @Override
    public List<ForeignKey<MoviesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MoviesRecord, ?>>asList(Keys.MOVIES_IBFK_1);
    }

    private transient Media _media;

    public Media media() {
        if (_media == null)
            _media = new Media(this, Keys.MOVIES_IBFK_1);

        return _media;
    }

    @Override
    public Movies as(String alias) {
        return new Movies(DSL.name(alias), this);
    }

    @Override
    public Movies as(Name alias) {
        return new Movies(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Movies rename(String name) {
        return new Movies(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Movies rename(Name name) {
        return new Movies(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
