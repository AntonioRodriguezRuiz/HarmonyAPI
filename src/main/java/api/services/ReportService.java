package api.services;

import api.GlobalValues;
import api.helpers.request.ReportRequestHelper;
import api.helpers.response.ReportResponseHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static src.main.java.model.Tables.REPORTS;

@Service
public class ReportService {
    public static List<Reports> getAllReports(Integer offset) throws SQLException {
        List<Reports> result = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
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


    public ReportResponseHelper postReport(ReportRequestHelper report) throws SQLException {
        Record record = null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

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


    public void deleteReport(Integer id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

            create.deleteFrom(REPORTS)
                    .where(REPORTS.REPORTID.eq(id)).execute();

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}
