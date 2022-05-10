package api.middlewares;

import api.helpers.request.ReportRequestHelper;
import database.DatabaseConnection;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

import static src.main.java.model.Tables.REPORTS;

public class ReportMiddlewares {

    public static void doesNotExistsReport(ReportRequestHelper report) throws SQLException {
        try {
            var create = DatabaseConnection.create();

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

    public static void existsReport(Integer id) throws SQLException {
        try {
            var create = DatabaseConnection.create();

            if(create.select()
                    .from(REPORTS)
                    .where(REPORTS.REPORTID.eq(id))
                    .fetch().isEmpty()){
                throw new ResponseStatusException((HttpStatus.NOT_FOUND));
            }

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}