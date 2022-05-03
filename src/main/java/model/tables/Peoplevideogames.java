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
import org.jooq.Row5;
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
import src.main.java.model.tables.records.PeoplevideogamesRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Peoplevideogames extends TableImpl<PeoplevideogamesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.peopleVideogames</code>
     */
    public static final Peoplevideogames PEOPLEVIDEOGAMES = new Peoplevideogames();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PeoplevideogamesRecord> getRecordType() {
        return PeoplevideogamesRecord.class;
    }

    /**
     * The column <code>harmony.peopleVideogames.peopleVideogamesid</code>.
     */
    public final TableField<PeoplevideogamesRecord, Integer> PEOPLEVIDEOGAMESID = createField(DSL.name("peopleVideogamesid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.peopleVideogames.videogameid</code>.
     */
    public final TableField<PeoplevideogamesRecord, Integer> VIDEOGAMEID = createField(DSL.name("videogameid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.peopleVideogames.personid</code>.
     */
    public final TableField<PeoplevideogamesRecord, Integer> PERSONID = createField(DSL.name("personid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.peopleVideogames.role</code>.
     */
    public final TableField<PeoplevideogamesRecord, String> ROLE = createField(DSL.name("role"), SQLDataType.VARCHAR(120).nullable(false), this, "");

    /**
     * The column <code>harmony.peopleVideogames.roletype</code>.
     */
    public final TableField<PeoplevideogamesRecord, Byte> ROLETYPE = createField(DSL.name("roletype"), SQLDataType.TINYINT, this, "");

    private Peoplevideogames(Name alias, Table<PeoplevideogamesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Peoplevideogames(Name alias, Table<PeoplevideogamesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.peopleVideogames</code> table reference
     */
    public Peoplevideogames(String alias) {
        this(DSL.name(alias), PEOPLEVIDEOGAMES);
    }

    /**
     * Create an aliased <code>harmony.peopleVideogames</code> table reference
     */
    public Peoplevideogames(Name alias) {
        this(alias, PEOPLEVIDEOGAMES);
    }

    /**
     * Create a <code>harmony.peopleVideogames</code> table reference
     */
    public Peoplevideogames() {
        this(DSL.name("peopleVideogames"), null);
    }

    public <O extends Record> Peoplevideogames(Table<O> child, ForeignKey<O, PeoplevideogamesRecord> key) {
        super(child, key, PEOPLEVIDEOGAMES);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PEOPLEVIDEOGAMES_PERSONID);
    }

    @Override
    public Identity<PeoplevideogamesRecord, Integer> getIdentity() {
        return (Identity<PeoplevideogamesRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<PeoplevideogamesRecord> getPrimaryKey() {
        return Keys.KEY_PEOPLEVIDEOGAMES_PRIMARY;
    }

    @Override
    public List<UniqueKey<PeoplevideogamesRecord>> getKeys() {
        return Arrays.<UniqueKey<PeoplevideogamesRecord>>asList(Keys.KEY_PEOPLEVIDEOGAMES_PRIMARY, Keys.KEY_PEOPLEVIDEOGAMES_PEOPLEVIDEOGAMESID, Keys.KEY_PEOPLEVIDEOGAMES_DUPLICATEDENTRY_PEOPLEVIDEOGAMES);
    }

    @Override
    public List<ForeignKey<PeoplevideogamesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PeoplevideogamesRecord, ?>>asList(Keys.PEOPLEVIDEOGAMES_IBFK_1, Keys.PEOPLEVIDEOGAMES_IBFK_2);
    }

    private transient Videogames _videogames;
    private transient People _people;

    public Videogames videogames() {
        if (_videogames == null)
            _videogames = new Videogames(this, Keys.PEOPLEVIDEOGAMES_IBFK_1);

        return _videogames;
    }

    public People people() {
        if (_people == null)
            _people = new People(this, Keys.PEOPLEVIDEOGAMES_IBFK_2);

        return _people;
    }

    @Override
    public Peoplevideogames as(String alias) {
        return new Peoplevideogames(DSL.name(alias), this);
    }

    @Override
    public Peoplevideogames as(Name alias) {
        return new Peoplevideogames(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Peoplevideogames rename(String name) {
        return new Peoplevideogames(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Peoplevideogames rename(Name name) {
        return new Peoplevideogames(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, Integer, String, Byte> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
