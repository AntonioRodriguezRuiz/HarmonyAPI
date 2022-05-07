package api.services;

import api.GlobalValues;
import api.helpers.response.GenreResponseHelper;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.GENRES;

@Service
public class GenresSpecificService {
    public GenreResponseHelper getGenre(Integer id) throws SQLException {
        GenreResponseHelper genreResult=null;
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            Record genre=create.select().from(GENRES).where(GENRES.GENREID.eq(id)).fetch().get(0);

            genreResult= new GenreResponseHelper(genre);

        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return genreResult;
    }

    public void deleteGenre(Integer id) {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, SQLDialect.MARIADB);

            create.deleteFrom(GENRES).where(GENRES.GENREID.eq(id)).execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
