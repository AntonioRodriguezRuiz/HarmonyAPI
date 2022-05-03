/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Check;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import src.main.java.model.Harmony;
import src.main.java.model.Indexes;
import src.main.java.model.Keys;
import src.main.java.model.tables.records.TrackersRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Trackers extends TableImpl<TrackersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.trackers</code>
     */
    public static final Trackers TRACKERS = new Trackers();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TrackersRecord> getRecordType() {
        return TrackersRecord.class;
    }

    /**
     * The column <code>harmony.trackers.trackerid</code>.
     */
    public final TableField<TrackersRecord, Integer> TRACKERID = createField(DSL.name("trackerid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.trackers.mediaid</code>.
     */
    public final TableField<TrackersRecord, Integer> MEDIAID = createField(DSL.name("mediaid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.trackers.userid</code>.
     */
    public final TableField<TrackersRecord, Integer> USERID = createField(DSL.name("userid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.trackers.state</code>.
     */
    public final TableField<TrackersRecord, Integer> STATE = createField(DSL.name("state"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.trackers.datetime</code>.
     */
    public final TableField<TrackersRecord, LocalDateTime> DATETIME = createField(DSL.name("datetime"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    private Trackers(Name alias, Table<TrackersRecord> aliased) {
        this(alias, aliased, null);
    }

    private Trackers(Name alias, Table<TrackersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.trackers</code> table reference
     */
    public Trackers(String alias) {
        this(DSL.name(alias), TRACKERS);
    }

    /**
     * Create an aliased <code>harmony.trackers</code> table reference
     */
    public Trackers(Name alias) {
        this(alias, TRACKERS);
    }

    /**
     * Create a <code>harmony.trackers</code> table reference
     */
    public Trackers() {
        this(DSL.name("trackers"), null);
    }

    public <O extends Record> Trackers(Table<O> child, ForeignKey<O, TrackersRecord> key) {
        super(child, key, TRACKERS);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.TRACKERS_MEDIAID, Indexes.TRACKERS_USERID);
    }

    @Override
    public Identity<TrackersRecord, Integer> getIdentity() {
        return (Identity<TrackersRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TrackersRecord> getPrimaryKey() {
        return Keys.KEY_TRACKERS_PRIMARY;
    }

    @Override
    public List<UniqueKey<TrackersRecord>> getKeys() {
        return Arrays.<UniqueKey<TrackersRecord>>asList(Keys.KEY_TRACKERS_PRIMARY, Keys.KEY_TRACKERS_TRACKERID);
    }

    @Override
    public List<ForeignKey<TrackersRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TrackersRecord, ?>>asList(Keys.TRACKERS_IBFK_1, Keys.TRACKERS_IBFK_2);
    }

    private transient Media _media;
    private transient Users _users;

    public Media media() {
        if (_media == null)
            _media = new Media(this, Keys.TRACKERS_IBFK_1);

        return _media;
    }

    public Users users() {
        if (_users == null)
            _users = new Users(this, Keys.TRACKERS_IBFK_2);

        return _users;
    }

    @Override
    public List<Check<TrackersRecord>> getChecks() {
        return Arrays.<Check<TrackersRecord>>asList(
              Internal.createCheck(this, DSL.name("invalidState"), "((`state` >= 1) and (`state` <= 4))", true)
        );
    }

    @Override
    public Trackers as(String alias) {
        return new Trackers(DSL.name(alias), this);
    }

    @Override
    public Trackers as(Name alias) {
        return new Trackers(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Trackers rename(String name) {
        return new Trackers(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Trackers rename(Name name) {
        return new Trackers(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, Integer, Integer, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
