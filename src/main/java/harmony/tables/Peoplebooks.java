/*
 * This file is generated by jOOQ.
 */
package src.main.java.harmony.tables;


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

import src.main.java.harmony.Harmony;
import src.main.java.harmony.Indexes;
import src.main.java.harmony.Keys;
import src.main.java.harmony.tables.records.PeoplebooksRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Peoplebooks extends TableImpl<PeoplebooksRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.peopleBooks</code>
     */
    public static final Peoplebooks PEOPLEBOOKS = new Peoplebooks();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PeoplebooksRecord> getRecordType() {
        return PeoplebooksRecord.class;
    }

    /**
     * The column <code>harmony.peopleBooks.peopleBooksid</code>.
     */
    public final TableField<PeoplebooksRecord, Integer> PEOPLEBOOKSID = createField(DSL.name("peopleBooksid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.peopleBooks.bookid</code>.
     */
    public final TableField<PeoplebooksRecord, Integer> BOOKID = createField(DSL.name("bookid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.peopleBooks.personid</code>.
     */
    public final TableField<PeoplebooksRecord, Integer> PERSONID = createField(DSL.name("personid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.peopleBooks.role</code>.
     */
    public final TableField<PeoplebooksRecord, String> ROLE = createField(DSL.name("role"), SQLDataType.VARCHAR(120).nullable(false), this, "");

    private Peoplebooks(Name alias, Table<PeoplebooksRecord> aliased) {
        this(alias, aliased, null);
    }

    private Peoplebooks(Name alias, Table<PeoplebooksRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.peopleBooks</code> table reference
     */
    public Peoplebooks(String alias) {
        this(DSL.name(alias), PEOPLEBOOKS);
    }

    /**
     * Create an aliased <code>harmony.peopleBooks</code> table reference
     */
    public Peoplebooks(Name alias) {
        this(alias, PEOPLEBOOKS);
    }

    /**
     * Create a <code>harmony.peopleBooks</code> table reference
     */
    public Peoplebooks() {
        this(DSL.name("peopleBooks"), null);
    }

    public <O extends Record> Peoplebooks(Table<O> child, ForeignKey<O, PeoplebooksRecord> key) {
        super(child, key, PEOPLEBOOKS);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PEOPLEBOOKS_PERSONID);
    }

    @Override
    public Identity<PeoplebooksRecord, Integer> getIdentity() {
        return (Identity<PeoplebooksRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<PeoplebooksRecord> getPrimaryKey() {
        return Keys.KEY_PEOPLEBOOKS_PRIMARY;
    }

    @Override
    public List<UniqueKey<PeoplebooksRecord>> getKeys() {
        return Arrays.<UniqueKey<PeoplebooksRecord>>asList(Keys.KEY_PEOPLEBOOKS_PRIMARY, Keys.KEY_PEOPLEBOOKS_PEOPLEBOOKSID, Keys.KEY_PEOPLEBOOKS_DUPLICATEDENTRY_PEOPLEMOVIES);
    }

    @Override
    public List<ForeignKey<PeoplebooksRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PeoplebooksRecord, ?>>asList(Keys.PEOPLEBOOKS_IBFK_1, Keys.PEOPLEBOOKS_IBFK_2);
    }

    private transient Books _books;
    private transient People _people;

    public Books books() {
        if (_books == null)
            _books = new Books(this, Keys.PEOPLEBOOKS_IBFK_1);

        return _books;
    }

    public People people() {
        if (_people == null)
            _people = new People(this, Keys.PEOPLEBOOKS_IBFK_2);

        return _people;
    }

    @Override
    public Peoplebooks as(String alias) {
        return new Peoplebooks(DSL.name(alias), this);
    }

    @Override
    public Peoplebooks as(Name alias) {
        return new Peoplebooks(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Peoplebooks rename(String name) {
        return new Peoplebooks(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Peoplebooks rename(Name name) {
        return new Peoplebooks(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
