package populator;

import org.jooq.tools.json.ParseException;
import populator.movies.MoviePopulator;
import populator.series.SeriesPopulator;

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
    }
}
