package populator.books;

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
    Integer averageRating,
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
    Integer isbn,
    Integer isbn13,
    Integer asin,
    String settings,
    String characters,
    String awards,
    String amazonRedirectLink,
    String worldcatRedirectLink,
    List<Integer> recommendedBooks,
    List<Integer> booksInSeries,
    String description
) {
}
