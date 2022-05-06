package api.controllers;

import api.helpers.request.ReviewLikesRequestHelper;
import api.helpers.request.ReviewRequestHelper;
import api.helpers.request.UseridBodyHelper;
import api.helpers.response.ReviewResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.ReviewSpecificService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jooq.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/reviews/{id}")
public class ReviewSpecificController {
    @Autowired
    ReviewSpecificService reviewSpecificService;

    @Operation(summary = "Get information about a certain review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful"),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @GetMapping
    public ReviewResponseHelper getReview(@PathVariable Integer id) throws SQLException{
        return reviewSpecificService.getReview(id);
    }

    @Operation(summary = "Deletes a review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping
    public ResponseEntity deleteReview(@PathVariable Integer id, @RequestBody ReviewRequestHelper review) throws SQLException{
        UserMiddlewares.isOwnerOfReview(review.userid(), review.reviewid());
        reviewSpecificService.deleteReview(id);
        return new ResponseEntity((HttpStatus.NO_CONTENT));
    }

    @Operation(summary = "Like a review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "409", description = "You have already liked this", content = @Content)})
    @PostMapping("/likes")
    public ResponseEntity<ReviewResponseHelper> postReview(@PathVariable Integer id, @RequestBody ReviewLikesRequestHelper reviewlikes) throws SQLException {
        if(reviewlikes.userid() == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if(reviewlikes.reviewid()== null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ReviewResponseHelper>(reviewSpecificService.postLike(id, reviewlikes), HttpStatus.CREATED);
    }
    @Operation(summary = "Unlikes a review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)})
    @DeleteMapping("/likes/{likeid}")
    public ResponseEntity deleteReview(@PathVariable Integer id, @PathVariable Integer likeid, @RequestBody ReviewRequestHelper review) throws SQLException {
        UserMiddlewares.isOwnerOfLike(review.userid(), review.reviewid());
        return new ResponseEntity<>((HttpStatus.NO_CONTENT));
    }
}
