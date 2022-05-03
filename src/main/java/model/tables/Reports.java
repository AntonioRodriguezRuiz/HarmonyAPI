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
import src.main.java.model.tables.records.ReportsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Reports extends TableImpl<ReportsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.reports</code>
     */
    public static final Reports REPORTS = new Reports();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ReportsRecord> getRecordType() {
        return ReportsRecord.class;
    }

    /**
     * The column <code>harmony.reports.reportid</code>.
     */
    public final TableField<ReportsRecord, Integer> REPORTID = createField(DSL.name("reportid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.reports.useridreporter</code>.
     */
    public final TableField<ReportsRecord, Integer> USERIDREPORTER = createField(DSL.name("useridreporter"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>harmony.reports.useridreported</code>.
     */
    public final TableField<ReportsRecord, Integer> USERIDREPORTED = createField(DSL.name("useridreported"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>harmony.reports.reviewid</code>.
     */
    public final TableField<ReportsRecord, Integer> REVIEWID = createField(DSL.name("reviewid"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>harmony.reports.reason</code>.
     */
    public final TableField<ReportsRecord, String> REASON = createField(DSL.name("reason"), SQLDataType.VARCHAR(120), this, "");

    private Reports(Name alias, Table<ReportsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Reports(Name alias, Table<ReportsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.reports</code> table reference
     */
    public Reports(String alias) {
        this(DSL.name(alias), REPORTS);
    }

    /**
     * Create an aliased <code>harmony.reports</code> table reference
     */
    public Reports(Name alias) {
        this(alias, REPORTS);
    }

    /**
     * Create a <code>harmony.reports</code> table reference
     */
    public Reports() {
        this(DSL.name("reports"), null);
    }

    public <O extends Record> Reports(Table<O> child, ForeignKey<O, ReportsRecord> key) {
        super(child, key, REPORTS);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.REPORTS_REVIEWID, Indexes.REPORTS_USERIDREPORTER);
    }

    @Override
    public Identity<ReportsRecord, Integer> getIdentity() {
        return (Identity<ReportsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ReportsRecord> getPrimaryKey() {
        return Keys.KEY_REPORTS_PRIMARY;
    }

    @Override
    public List<UniqueKey<ReportsRecord>> getKeys() {
        return Arrays.<UniqueKey<ReportsRecord>>asList(Keys.KEY_REPORTS_PRIMARY, Keys.KEY_REPORTS_REPORTID, Keys.KEY_REPORTS_ALREADYREPORTED);
    }

    @Override
    public List<ForeignKey<ReportsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ReportsRecord, ?>>asList(Keys.REPORTS_IBFK_1, Keys.REPORTS_IBFK_2, Keys.REPORTS_IBFK_3);
    }

    private transient Users _users;
    private transient Reviews _reportsIbfk_2;
    private transient Reviews _reportsIbfk_3;

    public Users users() {
        if (_users == null)
            _users = new Users(this, Keys.REPORTS_IBFK_1);

        return _users;
    }

    public Reviews reportsIbfk_2() {
        if (_reportsIbfk_2 == null)
            _reportsIbfk_2 = new Reviews(this, Keys.REPORTS_IBFK_2);

        return _reportsIbfk_2;
    }

    public Reviews reportsIbfk_3() {
        if (_reportsIbfk_3 == null)
            _reportsIbfk_3 = new Reviews(this, Keys.REPORTS_IBFK_3);

        return _reportsIbfk_3;
    }

    @Override
    public Reports as(String alias) {
        return new Reports(DSL.name(alias), this);
    }

    @Override
    public Reports as(Name alias) {
        return new Reports(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Reports rename(String name) {
        return new Reports(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Reports rename(Name name) {
        return new Reports(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, Integer, Integer, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}