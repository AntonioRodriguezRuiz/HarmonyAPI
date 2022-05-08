package api.controllers;

import api.helpers.request.*;
import api.helpers.response.*;
import api.middlewares.*;
import api.services.MediaSpecificService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/media/{id}")
public class MediaSpecificController {

    @Autowired
    MediaSpecificService mediaService;

    @Operation(summary = "Get information about a certain piece of media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful"),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @GetMapping
    public MediaResponseHelper getMedia(@PathVariable Integer id) throws SQLException {
        MediaMiddlewares.mediaExists(id);
        return mediaService.getMedia(id);
    }

    @Operation(summary = "Deletes a piece of media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping
    public ResponseEntity deleteMedia(@PathVariable Integer id, @RequestBody UseridBodyHelper useridBody) throws SQLException {
        UserMiddlewares.isAdmin(useridBody.userid());
        MediaMiddlewares.mediaExists(id);
        mediaService.deleteMedia(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Post a new season of a series")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a series", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping("/seasons")
    public ResponseEntity<SeasonResponseHelper> postSeason(@PathVariable Integer id, @RequestBody SeasonRequestHelper season) throws SQLException {
        UserMiddlewares.isAdmin(season.getUserid());
        season.postValidate();
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonDoesNotExists(id, season.getSeasonNo());
        return new ResponseEntity<>(mediaService.postSeason(id, season), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a series season")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a series", content = @Content)})
    @PutMapping("/seasons")
    public ResponseEntity putSeason(@PathVariable Integer id, @RequestBody SeasonRequestHelper season) throws SQLException {
        UserMiddlewares.isAdmin(season.getUserid());
        season.validate();
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(season.getSeasonid());
        SeasonMiddlewares.isSeasonOf(id, season.getSeasonid());
        mediaService.putSeason(id, season);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Adds a new platform to a videogame")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a videogame", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping("/platforms")
    public ResponseEntity<PlatformResponseHelper> postPlatform(@PathVariable Integer id, @RequestBody PlatformRequestHelper platform) throws SQLException {
        UserMiddlewares.isAdmin(platform.getUserid());
        VideogamesMiddlewares.isVideogame(id);
        platform.validate();
        PlatformsMiddlewares.existsPlatform(null, platform.getPlatformid());
        VideogamesMiddlewares.doesNotHavePlatform(id, platform.getPlatformid());
        return new ResponseEntity<>(mediaService.postPlatform(id, platform), HttpStatus.CREATED);
    }

    @Operation(summary = "Removes a platform from a videogame's list of platforms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a videogame", content = @Content)})
    @DeleteMapping("/platforms/{platformid}")
    public ResponseEntity removePlatform(@PathVariable Integer id, @PathVariable Integer platformid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        VideogamesMiddlewares.isVideogame(id);
        PlatformsMiddlewares.existsPlatform(null, platformid);
        VideogamesMiddlewares.hasPlatform(id, platformid);
        mediaService.removePlatform(id, platformid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get information about a certain season, including episodes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful"),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a season of a series", content = @Content)})
    @GetMapping("/{seasonid}")
    public SeasonResponseHelper getSeason(@PathVariable Integer id, @PathVariable Integer seasonid) throws SQLException {
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        return mediaService.getSeason(id,seasonid);
    }

    @Operation(summary = "Adds a new episode to a season")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a season of a series", content = @Content),
            @ApiResponse(responseCode = "409", description = "Item already exists", content = @Content)})
    @PostMapping("/{seasonid}")
    public ResponseEntity<EpisodeResponseHelper> postEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @RequestBody EpisodeRequestHelper episode) throws SQLException {
        UserMiddlewares.isAdmin(episode.getUserid());
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        episode.postValidate();
        EpisodeMiddlewares.episodeDoesNotExists(seasonid, episode.getEpisodeNo());
        return new ResponseEntity<>(mediaService.postEpisode(id, seasonid, episode), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies an existing episode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item modified", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a season of a series", content = @Content)})
    @PutMapping("/{seasonid}")
    public ResponseEntity putEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @RequestBody EpisodeRequestHelper episode) throws SQLException {
        UserMiddlewares.isAdmin(episode.getUserid());
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        episode.validate();
        EpisodeMiddlewares.episodeExists(episode.getEpisodeid());
        EpisodeMiddlewares.isEpisodeOf(seasonid, episode.getEpisodeid());
        mediaService.putEpisode(id, seasonid, episode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Removes a season from a series list of seasons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a season of a series", content = @Content)})
    @DeleteMapping("/{seasonid}")
    public ResponseEntity deleteSeason(@PathVariable Integer id, @PathVariable Integer seasonid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        mediaService.deleteSeason(id, seasonid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get information about a certain episode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful"),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a episode of a series", content = @Content)})
    @GetMapping("/{seasonid}/{episodeid}")
    public EpisodeResponseHelper getEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid) throws SQLException {
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        EpisodeMiddlewares.episodeExists(episodeid);
        EpisodeMiddlewares.isEpisodeOf(seasonid, episodeid);
        return mediaService.getEpisode(episodeid);
    }

    @Operation(summary = "Removes an episode from a season")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is not a season of a series", content = @Content)})
    @DeleteMapping("/{seasonid}/{episodeid}")
    public ResponseEntity deleteEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        EpisodeMiddlewares.episodeExists(episodeid);
        EpisodeMiddlewares.isEpisodeOf(seasonid, episodeid);
        mediaService.deleteEpisode(episodeid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Adds a new genre to a media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "Media already has this genre", content = @Content)})
    @PostMapping("/genres")
    public ResponseEntity<GenreResponseHelper> addGenre(@PathVariable Integer id, @RequestBody GenreRequestHelper genre) throws SQLException {
        UserMiddlewares.isAdmin(genre.getUserid());
        genre.validate();
        MediaMiddlewares.mediaExists(id);
        GenresMiddlewares.existsGenre(genre.getGenreid());
        MediaMiddlewares.doesNotHaveGenre(id, genre.getGenreid());
        return new ResponseEntity<>(mediaService.addGenre(id, genre), HttpStatus.CREATED);
    }

    @Operation(summary = "Removes a genre from a media list of genres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping("/genres/{genreid}")
    public ResponseEntity removeGenre(@PathVariable Integer id, @PathVariable Integer genreid, @RequestBody UseridBodyHelper user) throws SQLException {
        UserMiddlewares.isAdmin(user.userid());
        MediaMiddlewares.mediaExists(id);
        GenresMiddlewares.existsGenre(genreid);
        MediaMiddlewares.hasGenre(id, genreid);
        mediaService.removeGenre(id, genreid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get information about all the people participating in the creation of the media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful"),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @GetMapping("/people")
    public List<PeopleMediaResponseHelper> getPeopleFromMedia(@PathVariable Integer id) throws SQLException {
        MediaMiddlewares.mediaExists(id);
        return mediaService.getPeopleFromMedia(id);
    }

    @Operation(summary = "Adds a new person to a media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is a series, series do not have people directly", content = @Content),
            @ApiResponse(responseCode = "409", description = "This person is already in this media with the same role", content = @Content)})
    @PostMapping("/people")
    public ResponseEntity<PeopleMediaResponseHelper> addPerson(@PathVariable Integer id, @RequestBody PeopleMediaRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getUserid());
        Table table = mediaService.getType(id);
        person.mediaValidate(table);
        PeopleMiddlewares.existsPerson(person.getPersonid());
        MediaMiddlewares.personNotInMedia(id, person, table);
        return new ResponseEntity<>(mediaService.addPerson(id, person, table), HttpStatus.CREATED);
    }

    @Operation(summary = "Removes a person with a role from a media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content),
            @ApiResponse(responseCode = "405", description = "The media item is a series, series do not have people directly", content = @Content)})
    @DeleteMapping("/people/{personid}")
    public ResponseEntity removePerson(@PathVariable Integer id, @PathVariable Integer personid, @RequestBody PeopleMediaRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getUserid());
        Table table = mediaService.getType(id);
        person.setPersonid(personid);
        person.mediaValidate(table);
        PeopleMiddlewares.existsPerson(person.getPersonid());
        MediaMiddlewares.persoInMedia(id, person, table);
        mediaService.removePerson(id, person, table);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Gets all the people working on an episode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "409", description = "This person is already in this media with the same role", content = @Content)})
    @GetMapping("/{seasonid}/{episodeid}/people")
    public List<PeopleMediaResponseHelper> getPeopleFromEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid) throws SQLException {
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        EpisodeMiddlewares.episodeExists(episodeid);
        EpisodeMiddlewares.isEpisodeOf(seasonid, episodeid);
        return mediaService.getPeopleFromEpisode(id, seasonid, episodeid);
    }

    @Operation(summary = "Adds a person with a role to an episode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PostMapping("/{seasonid}/{episodeid}/people")
    public ResponseEntity<PeopleMediaResponseHelper> addPersonEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid, @RequestBody PeopleMediaRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getUserid());
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        EpisodeMiddlewares.episodeExists(episodeid);
        EpisodeMiddlewares.isEpisodeOf(seasonid, episodeid);
        person.episodeValidate();
        PeopleMiddlewares.existsPerson(person.getPersonid());
        EpisodeMiddlewares.personNotInEpisode(episodeid, person);
        return new ResponseEntity<>(mediaService.addPersonEpisode(id, seasonid, episodeid, person), HttpStatus.CREATED);
    }

    @Operation(summary = "Removes a person with a role from an episode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping("/{seasonid}/{episodeid}/people/{personid}")
    public ResponseEntity removePersonEpisode(@PathVariable Integer id, @PathVariable Integer seasonid, @PathVariable Integer episodeid, @PathVariable Integer personid, @RequestBody PeopleMediaRequestHelper person) throws SQLException {
        UserMiddlewares.isAdmin(person.getUserid());
        MediaMiddlewares.mediaExists(id);
        SeriesMiddlewares.isSeries(id);
        SeasonMiddlewares.seasonExists(seasonid);
        SeasonMiddlewares.isSeasonOf(id, seasonid);
        EpisodeMiddlewares.episodeExists(episodeid);
        EpisodeMiddlewares.isEpisodeOf(seasonid, episodeid);
        person.setPersonid(personid);
        person.episodeValidate();
        PeopleMiddlewares.existsPerson(person.getPersonid());
        EpisodeMiddlewares.persoInEpisode(episodeid, person);
        mediaService.removePersonEpisode(episodeid, person);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get information about all the reviews of the media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful"),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @GetMapping("/reviews")
    public List<ReviewResponseHelper> getReviews(@PathVariable Integer id) throws SQLException {
        MediaMiddlewares.mediaExists(id);
        return mediaService.getReviews(id);
    }

    @Operation(summary = "Adds a new review to a media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not logged in", content = @Content),
            @ApiResponse(responseCode = "409", description = "This person already has a review in this media. You can only perform put operations over it", content = @Content)})
    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponseHelper> addReview(@PathVariable Integer id, @RequestBody ReviewRequestHelper review) throws SQLException {
        review.validateUserid();
        UserMiddlewares.userExists(review.userid());
        review.validateRating();
        MediaMiddlewares.doesNotHaveReview(id, review.userid());
        return new ResponseEntity<>(mediaService.addReview(id, review), HttpStatus.CREATED);
    }

    @Operation(summary = "Modifies a review from a media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed", content = @Content),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Only the owner of the review can modify it", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @PutMapping("/reviews")
    public ResponseEntity putReview(@PathVariable Integer id, @RequestBody ReviewRequestHelper review) throws SQLException {
        review.validateUserid();
        review.validateReviewid();
        UserMiddlewares.userExists(review.userid());
        review.validateRating();
        ReviewMiddlewares.existsReview(review.reviewid());
        MediaMiddlewares.hasReview(id, review.reviewid());
        ReviewMiddlewares.isOwnerOfReview(review.userid(), review.reviewid());

        mediaService.putReview(review);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
