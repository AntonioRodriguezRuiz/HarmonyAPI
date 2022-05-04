/*
 * This file is generated by jOOQ.
 */
package src.main.java.model.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import src.main.java.model.tables.Reviews;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ReviewsRecord extends UpdatableRecordImpl<ReviewsRecord> implements Record7<Integer, Integer, Integer, LocalDateTime, Double, String, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>harmony.reviews.reviewid</code>.
     */
    public ReviewsRecord setReviewid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviews.reviewid</code>.
     */
    public Integer getReviewid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>harmony.reviews.userid</code>.
     */
    public ReviewsRecord setUserid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviews.userid</code>.
     */
    public Integer getUserid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>harmony.reviews.mediaid</code>.
     */
    public ReviewsRecord setMediaid(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviews.mediaid</code>.
     */
    public Integer getMediaid() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>harmony.reviews.creationDate</code>.
     */
    public ReviewsRecord setCreationdate(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviews.creationDate</code>.
     */
    public LocalDateTime getCreationdate() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>harmony.reviews.rating</code>.
     */
    public ReviewsRecord setRating(Double value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviews.rating</code>.
     */
    public Double getRating() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>harmony.reviews.review</code>.
     */
    public ReviewsRecord setReview(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviews.review</code>.
     */
    public String getReview() {
        return (String) get(5);
    }

    /**
     * Setter for <code>harmony.reviews.likes</code>.
     */
    public ReviewsRecord setLikes(Integer value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>harmony.reviews.likes</code>.
     */
    public Integer getLikes() {
        return (Integer) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, Integer, LocalDateTime, Double, String, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, Integer, Integer, LocalDateTime, Double, String, Integer> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Reviews.REVIEWS.REVIEWID;
    }

    @Override
    public Field<Integer> field2() {
        return Reviews.REVIEWS.USERID;
    }

    @Override
    public Field<Integer> field3() {
        return Reviews.REVIEWS.MEDIAID;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Reviews.REVIEWS.CREATIONDATE;
    }

    @Override
    public Field<Double> field5() {
        return Reviews.REVIEWS.RATING;
    }

    @Override
    public Field<String> field6() {
        return Reviews.REVIEWS.REVIEW;
    }

    @Override
    public Field<Integer> field7() {
        return Reviews.REVIEWS.LIKES;
    }

    @Override
    public Integer component1() {
        return getReviewid();
    }

    @Override
    public Integer component2() {
        return getUserid();
    }

    @Override
    public Integer component3() {
        return getMediaid();
    }

    @Override
    public LocalDateTime component4() {
        return getCreationdate();
    }

    @Override
    public Double component5() {
        return getRating();
    }

    @Override
    public String component6() {
        return getReview();
    }

    @Override
    public Integer component7() {
        return getLikes();
    }

    @Override
    public Integer value1() {
        return getReviewid();
    }

    @Override
    public Integer value2() {
        return getUserid();
    }

    @Override
    public Integer value3() {
        return getMediaid();
    }

    @Override
    public LocalDateTime value4() {
        return getCreationdate();
    }

    @Override
    public Double value5() {
        return getRating();
    }

    @Override
    public String value6() {
        return getReview();
    }

    @Override
    public Integer value7() {
        return getLikes();
    }

    @Override
    public ReviewsRecord value1(Integer value) {
        setReviewid(value);
        return this;
    }

    @Override
    public ReviewsRecord value2(Integer value) {
        setUserid(value);
        return this;
    }

    @Override
    public ReviewsRecord value3(Integer value) {
        setMediaid(value);
        return this;
    }

    @Override
    public ReviewsRecord value4(LocalDateTime value) {
        setCreationdate(value);
        return this;
    }

    @Override
    public ReviewsRecord value5(Double value) {
        setRating(value);
        return this;
    }

    @Override
    public ReviewsRecord value6(String value) {
        setReview(value);
        return this;
    }

    @Override
    public ReviewsRecord value7(Integer value) {
        setLikes(value);
        return this;
    }

    @Override
    public ReviewsRecord values(Integer value1, Integer value2, Integer value3, LocalDateTime value4, Double value5, String value6, Integer value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ReviewsRecord
     */
    public ReviewsRecord() {
        super(Reviews.REVIEWS);
    }

    /**
     * Create a detached, initialised ReviewsRecord
     */
    public ReviewsRecord(Integer reviewid, Integer userid, Integer mediaid, LocalDateTime creationdate, Double rating, String review, Integer likes) {
        super(Reviews.REVIEWS);

        setReviewid(reviewid);
        setUserid(userid);
        setMediaid(mediaid);
        setCreationdate(creationdate);
        setRating(rating);
        setReview(review);
        setLikes(likes);
    }
}
