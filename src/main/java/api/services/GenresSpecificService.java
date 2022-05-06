package api.services;

import api.helpers.response.GenreResponseHelper;
import api.helpers.request.GenreRequestHelper;
import api.GlobalValues;
import api.helpers.enums.RoleType;
import api.helpers.request.*;
import api.helpers.response.*;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.main.java.model.Tables.*;
import src.main.java.model.Routines;
import src.main.java.model.tables.pojos.Genres;

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
