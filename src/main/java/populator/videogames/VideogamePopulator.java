package populator.videogames;

import database.DatabaseConnection;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Media;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.main.java.model.Tables.MEDIA;
import static src.main.java.model.Tables.VIDEOGAMES;

/**
 * VideogamePopulator
 * Project HarmonyAPI
 * Created: 2022-05-11
 *
 * @author juagallop1
 **/
public class VideogamePopulator {
    private static List<Media> getAll() throws SQLException {
        List<Media> result = new ArrayList<>();
        try {
            var create = DatabaseConnection.create();
            result = create.select()
                .from(MEDIA)
                .naturalJoin(VIDEOGAMES)
                .fetchInto(Media.class);
        } catch (ResponseStatusException | SQLException e) {
            if (e instanceof ResponseStatusException) {
                throw e;
            }
            e.printStackTrace();
        }
        return result;
    }

    private static List<FetchedVideogame> fetchVideogames() {
        List<FetchedVideogame> videogames = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader("/home/kinami/Code/rawg/games.json"))) {
            String str;
            JSONParser parser = new JSONParser();
            while ((str = br.readLine()) != null) {
                var js = (JSONObject) parser.parse(str);
                videogames.add(
                    FetchedVideogame.of(js)
                );
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return videogames;
    }

    public static void populate(Integer limit) throws SQLException {
        var ids = getAll().stream()
            .map(Media::getExternalid)
            .toList();
        var videogames = fetchVideogames().stream()
            .filter(v -> !ids.contains(v.videogame().getExternalId()))
            .limit(limit)
            .toList();
//        add(videogames);
        System.out.println(videogames.size());
    }
}
