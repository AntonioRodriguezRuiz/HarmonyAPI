package api.controllers;

import api.helpers.response.UserResponseHelper;
import api.services.UserSpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

/**
 * UserSpecificCOntroller
 * Project HarmonyAPI
 * Created: 2022-05-04
 *
 * @author juagallop1
 **/

@RestController
@RequestMapping("/api/v1/user/{id}")
public class UserSpecificController {

    @Autowired
    UserSpecificService userService;

    @GetMapping
    public UserResponseHelper getUser(@PathVariable Integer id) throws SQLException {
        return userService.getUser(id);
    }
}
