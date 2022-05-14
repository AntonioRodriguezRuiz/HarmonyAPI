package populator;

import org.jooq.tools.json.ParseException;
import populator.books.BookPopulator;
import populator.movies.MoviePopulator;
import populator.series.SeriesPopulator;
import populator.videogames.VideogamePopulator;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Populator
 * Project HarmonyAPI
 * Created: 2022-05-05
 *
 * @author juagallop1
 **/
public class Populator {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        MoviePopulator.populate(150);
        SeriesPopulator.populate(150);
        VideogamePopulator.populate(150);
        BookPopulator.populate(150);
    }
}
