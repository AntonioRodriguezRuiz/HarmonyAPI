package populator.movies;

/**
 * FetchedMovie
 * Project HarmonyAPI
 * Created: 2022-05-05
 *
 * @author juagallop1
 **/
public record FetchedMovie(Integer id, Boolean adult, String original_title, Double popularity, Boolean video) {
}
