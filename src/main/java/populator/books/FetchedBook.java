package populator.books;

import org.jooq.tools.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * FetchedBook
 * Project HarmonyAPI
 * Created: 2022-05-12
 *
 * @author juagallop1
 **/
public record FetchedBook(
    Integer id,
    String title,
    String link,
    String series,
    String coverLink,
    List<String> authors,
    String authorLink,
    Integer ratingCount,
    Integer reviewCount,
    Double averageRating,
    Integer fiveStarRatings,
    Integer fourStarRatings,
    Integer threeStarRatings,
    Integer twoStarRatings,
    Integer oneStarRatings,
    Integer numberOfPages,
    String datePublished,
    String publisher,
    String originalTitle,
    List<String> genres,
    String isbn,
    String isbn13,
    String asin,
    String settings,
    String characters,
    String awards,
    String amazonRedirectLink,
    String worldcatRedirectLink,
    List<Integer> recommendedBooks,
    List<Integer> booksInSeries,
    String description
) {
    public static FetchedBook of(Object json) {
        JSONObject js = (JSONObject) json;
        return new FetchedBook(
            Integer.valueOf((String) js.get("id")),
            (String) js.get("title"),
            (String) js.get("link"),
            (String) js.get("series"),
            (String) js.get("coverLink"),
            js.get("author") == null ? null :Arrays.stream(((String) js.get("author")).split(", ")).toList(),
            (String) js.get("authorLink"),
            js.get("ratingCount") == null ? null :Integer.valueOf((String) js.get("ratingCount")),
            js.get("reviewCount") == null ? null :Integer.valueOf((String) js.get("reviewCount")),
            js.get("averageRating") == null ? null :Double.valueOf((String) js.get("averageRating")),
            js.get("fiveStarRatings") == null ? null :Integer.valueOf((String) js.get("fiveStarRatings")),
            js.get("fourStarRatings") == null ? null :Integer.valueOf((String) js.get("fourStarRatings")),
            js.get("threeStarRatings") == null ? null :Integer.valueOf((String) js.get("threeStarRatings")),
            js.get("twoStarRatings") == null ? null :Integer.valueOf((String) js.get("twoStarRatings")),
            js.get("oneStarRatings") == null ? null :Integer.valueOf((String) js.get("oneStarRatings")),
            js.get("numberOfPages") == null ? null :Integer.valueOf((String) js.get("numberOfPages")),
            (String) js.get("datePublished"),
            (String) js.get("publisher"),
            (String) js.get("originalTitle"),
            js.get("genre_and_votes") == null ? null : Arrays.stream(((String) js.get("genre_and_votes")).split(", "))
                .map(g -> g.split(" ")[0])
                .distinct()
                .filter(g -> !g.isEmpty())
                .toList(),
            (String) js.get("isbn"),
            (String) js.get("isbn13"),
            (String) js.get("asin"),
            (String) js.get("settings"),
            (String) js.get("characters"),
            (String) js.get("awards"),
            (String) js.get("amazonRedirectLink"),
            (String) js.get("worldcatRedirectLink"),
            js.get("recommendedBooks") == null ? null :Arrays.stream(((String) js.get("recommendedBooks")).split(", ")).map(Integer::parseInt).toList(),
            js.get("booksInSeries") == null ? null :Arrays.stream(((String) js.get("booksInSeries")).split(", ")).map(Integer::parseInt).toList(),
            (String) js.get("description")
        );
    }
}
