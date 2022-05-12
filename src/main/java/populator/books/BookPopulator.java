package populator.books;

import api.helpers.request.BookRequestHelper;
import api.services.MediaService;
import database.DatabaseConnection;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;
import org.jooq.tools.json.JSONArray;
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

    private static MediaService mediaService = new MediaService();

    public static final ProgressBarBuilder pbb = new ProgressBarBuilder()
        .setTaskName("Populating books...")
        .setStyle(ProgressBarStyle.COLORFUL_UNICODE_BLOCK)
        .setUpdateIntervalMillis(100)
        .setMaxRenderedLength(100)
        .setUnit(" books", 1);

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

    private static List<FetchedBook> fetchBooks() throws IOException, ParseException {
        var parser = new JSONParser();
        var array = (JSONArray) parser.parse(new FileReader("files/books.json"));
        return array.stream()
            .map(FetchedBook::of)
            .toList();
    }

    private static void add(List<FetchedBook> books) throws SQLException {
        for (var book: ProgressBar.wrap(books, pbb)) {
            var brh = new BookRequestHelper(
                1,
                null,
                book.title(),
                book.datePublished() == null ? "1900-01-01" : book.datePublished(),
                book.coverLink(),
                null,
                book.description(),
                book.id(),
                book.series()
            );
            var dbBook = mediaService.postBook(brh);
        }
    }

    public static void populate(Integer limit) throws SQLException, IOException, ParseException {
        var ids = getAll().stream()
            .map(Media::getExternalid)
            .toList();
        var books = fetchBooks().stream()
            .filter(book -> !ids.contains(book.id()))
            .limit(limit)
            .toList();
        add(books);
    }
}
