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
import org.jooq.Row7;
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
import src.main.java.model.tables.records.ReviewsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Reviews extends TableImpl<ReviewsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>harmony.reviews</code>
     */
    public static final Reviews REVIEWS = new Reviews();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ReviewsRecord> getRecordType() {
        return ReviewsRecord.class;
    }

    /**
     * The column <code>harmony.reviews.reviewid</code>.
     */
    public final TableField<ReviewsRecord, Integer> REVIEWID = createField(DSL.name("reviewid"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>harmony.reviews.userid</code>.
     */
    public final TableField<ReviewsRecord, Integer> USERID = createField(DSL.name("userid"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>harmony.reviews.mediaid</code>.
     */
    public final TableField<ReviewsRecord, Integer> MEDIAID = createField(DSL.name("mediaid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>harmony.reviews.datetime</code>.
     */
    public final TableField<ReviewsRecord, LocalDateTime> DATETIME = createField(DSL.name("datetime"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>harmony.reviews.rating</code>.
     */
    public final TableField<ReviewsRecord, Double> RATING = createField(DSL.name("rating"), SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>harmony.reviews.review</code>.
     */
    public final TableField<ReviewsRecord, String> REVIEW = createField(DSL.name("review"), SQLDataType.VARCHAR(2800), this, "");

    /**
     * The column <code>harmony.reviews.likes</code>.
     */
    public final TableField<ReviewsRecord, Integer> LIKES = createField(DSL.name("likes"), SQLDataType.INTEGER.nullable(false), this, "");

    private Reviews(Name alias, Table<ReviewsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Reviews(Name alias, Table<ReviewsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>harmony.reviews</code> table reference
     */
    public Reviews(String alias) {
        this(DSL.name(alias), REVIEWS);
    }

    /**
     * Create an aliased <code>harmony.reviews</code> table reference
     */
    public Reviews(Name alias) {
        this(alias, REVIEWS);
    }

    /**
     * Create a <code>harmony.reviews</code> table reference
     */
    public Reviews() {
        this(DSL.name("reviews"), null);
    }

    public <O extends Record> Reviews(Table<O> child, ForeignKey<O, ReviewsRecord> key) {
        super(child, key, REVIEWS);
    }

    @Override
    public Schema getSchema() {
        return Harmony.HARMONY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.REVIEWS_MEDIAID);
    }

    @Override
    public Identity<ReviewsRecord, Integer> getIdentity() {
        return (Identity<ReviewsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ReviewsRecord> getPrimaryKey() {
        return Keys.KEY_REVIEWS_PRIMARY;
    }

    @Override
    public List<UniqueKey<ReviewsRecord>> getKeys() {
        return Arrays.<UniqueKey<ReviewsRecord>>asList(Keys.KEY_REVIEWS_PRIMARY, Keys.KEY_REVIEWS_REVIEWID, Keys.KEY_REVIEWS_REVIEWALREADYEXIST);
    }

    @Override
    public List<ForeignKey<ReviewsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ReviewsRecord, ?>>asList(Keys.REVIEWS_IBFK_1, Keys.REVIEWS_IBFK_2);
    }

    private transient Users _users;
    private transient Media _media;

    public Users users() {
        if (_users == null)
            _users = new Users(this, Keys.REVIEWS_IBFK_1);

        return _users;
    }

    public Media media() {
        if (_media == null)
            _media = new Media(this, Keys.REVIEWS_IBFK_2);

        return _media;
    }

    @Override
    public List<Check<ReviewsRecord>> getChecks() {
        return Arrays.<Check<ReviewsRecord>>asList(
              Internal.createCheck(this, DSL.name("invalidRatingTooHigh"), "(`rating` <= 5)", true)
            , Internal.createCheck(this, DSL.name("invalidRatingTooLow"), "(`rating` >= 0)", true)
        );
    }

    @Override
    public Reviews as(String alias) {
        return new Reviews(DSL.name(alias), this);
    }

    @Override
    public Reviews as(Name alias) {
        return new Reviews(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Reviews rename(String name) {
        return new Reviews(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Reviews rename(Name name) {
        return new Reviews(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, Integer, LocalDateTime, Double, String, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}