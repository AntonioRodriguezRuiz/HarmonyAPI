package api.middlewares;

import api.GlobalValues;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Genres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static src.main.java.model.Tables.GENRES;

public class GenresMiddlewares {

    public static void doesNotExistsGenre(String name) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

            if(!create.select()
                    .from(GENRES)
                    .where(GENRES.NAME.eq(name))
                    .fetch()
                    .isEmpty()){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Genre already exists");
            }

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void existsGenre(Integer id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);

            if(create.select()
                    .from(GENRES)
                    .where(GENRES.GENREID.eq(id))
                    .fetch()
                    .isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre does not exist");
            }

        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }

    public static void existsGenre(String genre) throws SQLException {
        try (Connection conn = DriverManager.getConnection(GlobalValues.URL, GlobalValues.USER, GlobalValues.PASSWORD)) {
            DSLContext create = DSL.using(conn, GlobalValues.DIALECT, GlobalValues.SETTINGS);
            if (create.select()
                .from(GENRES)
                .where(GENRES.NAME.eq(genre))
                .fetchInto(Genres.class)
                .isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre does not exist");
            }
        } catch (ResponseStatusException | SQLException e){
            if(e instanceof ResponseStatusException){
                throw e;
            }
            e.printStackTrace();
        }
    }
}
