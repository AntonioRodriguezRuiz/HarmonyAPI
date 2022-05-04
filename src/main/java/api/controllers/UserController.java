package api.controllers;

import api.helpers.request.UserRequestHelper;
import api.helpers.response.UserResponseHelper;
import api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

/**
 * UserController
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseHelper> postUser(@RequestBody UserRequestHelper user) throws SQLException {
        if (user.username() == null || user.password() == null || user.email() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.postUser(user), HttpStatus.CREATED);
    }
}
