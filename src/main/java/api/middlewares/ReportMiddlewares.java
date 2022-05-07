package api.middlewares;

import api.GlobalValues;
import api.helpers.request.ReportRequestHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.REPORTS;

public class ReportMiddlewares {

    public static void existsReport(ReportRequestHelper report) throws SQLException {
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

    public static Result<Record> existsReport(Integer id) throws SQLException {
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
}