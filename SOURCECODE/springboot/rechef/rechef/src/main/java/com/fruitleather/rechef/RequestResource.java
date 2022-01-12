package com.fruitleather.rechef;

import java.util.List;

import com.fruitleather.rechef.model.IngredientList;
import com.fruitleather.rechef.model.User;
import com.fruitleather.rechef.model.Recipe;
import com.fruitleather.rechef.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RequestResource {
    private final UserService userService;

    public RequestResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<User> validateUser(@RequestBody User user) {
        User returnedUser = userService.validateUser(user);
        return new ResponseEntity<User>(returnedUser, HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<Void> createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> retVal = userService.getAllRecipes();
        return new ResponseEntity<List<Recipe>>(retVal, HttpStatus.OK);
    }
}
