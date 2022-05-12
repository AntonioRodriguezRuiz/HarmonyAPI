package populator.books;

import database.DatabaseConnection;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.main.java.model.Tables.BOOKS;
import static src.main.java.model.Tables.MEDIA;

/**
 * BooksPopulator
 * Project HarmonyAPI
 * Created: 2022-05-10
 *
 * @author juagallop1
 **/
public class BookPopulator {

    private static List<Media> getAll() throws SQLException {
        List<Media> result = new ArrayList<>();
        try {
            var create = DatabaseConnection.create();
            result = create.select()
                .from(MEDIA)
                .naturalJoin(BOOKS)
                .fetchInto(Media.class);
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return result;
    }

    private static List<JSONObject> fetchBooks() throws IOException, ParseException {
        var parser = new JSONParser();
        var array = (JSONArray) parser.parse(new FileReader("files/books.json"));
        return array.stream()
            .toList();
    }

    public static void populate() throws SQLException, IOException, ParseException {
        var ids = getAll().stream()
            .map(Media::getExternalid)
            .toList();
        var books = fetchBooks()
    }
}
