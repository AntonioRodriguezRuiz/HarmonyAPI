package api.helpers.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record UseridBodyHelper(Integer userid) {

    public void validate(){
        if(userid()==null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

}