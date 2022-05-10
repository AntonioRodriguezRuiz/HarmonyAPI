package api.services;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

import static src.main.java.model.Tables.GENRES;

@Service
public class GenresSpecificService {

    public void deleteGenre(Integer id) {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)){
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

            create.deleteFrom(GENRES).where(GENRES.GENREID.eq(id)).execute();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
