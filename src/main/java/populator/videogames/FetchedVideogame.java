package populator.videogames;

import api.helpers.request.PeopleRequestHelper;
import api.helpers.request.VideogameRequestHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.ParseException;

import java.util.List;

/**
 * FetchedVideogame
 * Project HarmonyAPI
 * Created: 2022-05-11
 *
 * @author juagallop1
 **/
public record FetchedVideogame(VideogameRequestHelper videogame, List<String> genres, List<String> platforms, List<Pair<PeopleRequestHelper, List<String>>> people) {
    public static FetchedVideogame of(JSONObject js) throws ParseException {
        return new FetchedVideogame(
            new VideogameRequestHelper(
                1,
                null,
                (String) js.get("name"),
                (String) js.get("release_date"),
                (String) js.get("cover_image"),
                (String) js.get("background_image"),
                (String) js.get("synopsis"),
                Math.toIntExact((Long) js.get("external_id")),
                (String) js.get("company")
            ),
            (List<String>) js.get("genres"),
            (List<String>) js.get("platforms"),
            ((JSONArray) js.get("developers")) == null ? null : ((JSONArray) js.get("developers")).stream()
                .map(developer -> {
                    JSONObject developerObject = (JSONObject) developer;
                    return Pair.of(
                        new PeopleRequestHelper(
                            1,
                            null,
                            (String) developerObject.get("name"),
                            null,
                            (String) developerObject.get("picture")
                        ),
                        (List<String>) developerObject.get("roles")
                    );
                })
                .toList()
        );
    }
}
