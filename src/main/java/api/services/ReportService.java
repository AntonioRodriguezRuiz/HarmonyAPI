package api.services;

import api.GlobalValues;
import api.helpers.request.ReportRequestHelper;
import api.helpers.response.ReportResponseHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.REPORTS;
import static src.main.java.model.Tables.REVIEWS;

@Service
public class ReportService {
    public static List<Reports> getAllReports(Integer offset) throws SQLException {
        List<Reports> result = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);
            result = create.select(REPORTS.fields())
                    .from(REPORTS)
                    .offset(offset)
                    .limit(GlobalValues.PAGE_SIZE)
                    .fetchInto(Reports.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

    private void existsReport(ReportRequestHelper report) throws SQLException{
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

                Result<Record> reportList = create.select()
                        .from(REPORTS)
                        .where(REPORTS.USERIDREPORTER.eq(report.useridreporter()))
                        .and(REPORTS.USERIDREPORTED.eq(report.useridreported()))
                        .and(REPORTS.REVIEWID.eq(report.reviewid()))
                        .fetch();
                if(!reportList.isEmpty()){
                    throw new ResponseStatusException((HttpStatus.CONFLICT));
                }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }

    private Result<Record> existsReport(Integer id) throws SQLException {
        Result<Record> reportList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            reportList = create.select()
                    .from(REPORTS)
                    .where(REPORTS.REPORTID.eq(id))
                    .fetch();


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return reportList;
    }

    private Result<Record> existsReview(Integer id) throws SQLException {
        Result<Record> reviewList = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            reviewList = create.select()
                    .from(REVIEWS)
                    .where(REVIEWS.REVIEWID.eq(id))
                    .fetch();


        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return reviewList;
    }


    public ReportResponseHelper postReport(ReportRequestHelper report) throws SQLException {
        Record record = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            existsReport(report);

            Result<Record> review =  existsReview(report.reviewid());
            if(review.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            Routines.newreport(create.configuration(),
                    report.useridreporter(),
                    report.useridreported(),
                    report.reviewid(),
                    report.reason());

            record = create.select()
                    .from(REPORTS)
                    .orderBy(REPORTS.REPORTID.desc())
                    .fetch().get(0);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return ReportResponseHelper.of(record);
    }


    public void deleteReport(Integer id) {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Result<Record> reportList = existsReport(id);
            if(reportList.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            create.deleteFrom(REPORTS)
                    .where(REPORTS.REPORTID.eq(id)).execute();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
