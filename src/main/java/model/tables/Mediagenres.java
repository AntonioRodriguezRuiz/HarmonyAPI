/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
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
import src.main.java.model.tables.records.MediagenresRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Mediagenres extends TableImpl<MediagenresRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.mediaGenres</code>
     */
    public static final Mediagenres MEDIAGENRES = new Mediagenres();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MediagenresRecord> getRecordType() {
        return MediagenresRecord.class;
    }

    /**
     * The column <code>harmony.mediaGenres.mediagenresid</code>.
     */
    public final TableField<MediagenresRecord, Integer> MEDIAGENRESID = createField(DSL.name("mediagenresid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.mediaGenres.mediaid</code>.
     */
    public final TableField<MediagenresRecord, Integer> MEDIAID = createField(DSL.name("mediaid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.mediaGenres.genreid</code>.
     */
    public final TableField<MediagenresRecord, Integer> GENREID = createField(DSL.name("genreid"), SQLDataType.INTEGER.nullable(false), this, "");

    private Mediagenres(Name alias, Table<MediagenresRecord> aliased) {
        this(alias, aliased, null);
    }

    private Mediagenres(Name alias, Table<MediagenresRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.mediaGenres</code> table reference
     */
    public Mediagenres(String alias) {
        this(DSL.name(alias), MEDIAGENRES);
    }

    /**
     * Create an aliased <code>harmony.mediaGenres</code> table reference
     */
    public Mediagenres(Name alias) {
        this(alias, MEDIAGENRES);
    }

    /**
     * Create a <code>harmony.mediaGenres</code> table reference
     */
    public Mediagenres() {
        this(DSL.name("mediaGenres"), null);
    }

    public <O extends Record> Mediagenres(Table<O> child, ForeignKey<O, MediagenresRecord> key) {
        super(child, key, MEDIAGENRES);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.MEDIAGENRES_GENREID);
    }

    @Override
    public Identity<MediagenresRecord, Integer> getIdentity() {
        return (Identity<MediagenresRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<MediagenresRecord> getPrimaryKey() {
        return Keys.KEY_MEDIAGENRES_PRIMARY;
    }

    @Override
    public List<UniqueKey<MediagenresRecord>> getKeys() {
        return Arrays.<UniqueKey<MediagenresRecord>>asList(Keys.KEY_MEDIAGENRES_PRIMARY, Keys.KEY_MEDIAGENRES_MEDIAGENRESID, Keys.KEY_MEDIAGENRES_DUPLICATEDENTRY_MEDIAGENRES);
    }

    @Override
    public List<ForeignKey<MediagenresRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MediagenresRecord, ?>>asList(Keys.MEDIAGENRES_IBFK_1, Keys.MEDIAGENRES_IBFK_2);
    }

    private transient Media _media;
    private transient Genres _genres;

    public Media media() {
        if (_media == null)
            _media = new Media(this, Keys.MEDIAGENRES_IBFK_1);

        return _media;
    }

    public Genres genres() {
        if (_genres == null)
            _genres = new Genres(this, Keys.MEDIAGENRES_IBFK_2);

        return _genres;
    }

    @Override
    public Mediagenres as(String alias) {
        return new Mediagenres(DSL.name(alias), this);
    }

    @Override
    public Mediagenres as(Name alias) {
        return new Mediagenres(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Mediagenres rename(String name) {
        return new Mediagenres(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Mediagenres rename(Name name) {
        return new Mediagenres(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
