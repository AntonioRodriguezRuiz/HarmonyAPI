package api.helpers.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record ReviewRequestHelper(Integer userid, Integer reviewid,
                                  Double rating, String review) {
    public void validateUserid() {
        if(this.userid()==null)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "UserId cannot be null");
    }

    public void validateReviewid() {
        if(this.reviewid()==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ReviewId cannot be null");
    }

    public void validateRating() {
        if(rating==null || 0.0 > rating || 5.0 < rating)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rating must be between 0 and 5");
    }
}
