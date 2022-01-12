package com.fruitleather.rechef.service;

import com.fruitleather.rechef.repo.UserRepo;
import com.fruitleather.rechef.repo.RecipeRepo;
import com.fruitleather.rechef.model.User;
import com.fruitleather.rechef.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RecipeRepo recipeRepo;

    @Autowired
    public UserService(UserRepo userRepo, RecipeRepo recipeRepo) { this.userRepo = userRepo; this.recipeRepo = recipeRepo; }

    public List<User> findAllUsers() { return userRepo.getAllUsers(); }

    public User validateUser(User user) {
        System.out.println(user.toString());
        User u = userRepo.validateUser(user.getUsername(), user.getPassword(), user.getEmail());
        System.out.println("New user gotten");
        if (Objects.isNull(u)) {
            System.out.println("Null User");
            return u;
        }
        System.out.println(u.toString());
        return u;
    }

    public User createNewUser(User user) {
        System.out.println(user.toString());
        User u = userRepo.checkExists(user.getUsername(), user.getEmail());
        if (Objects.isNull(u)) {
            userRepo.createNewUser(user.getUsername(), user.getPassword(), user.getEmail());
            System.out.println(user.toString());
            return user;
        }
        System.out.println("User already exists");
        return u;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> ret = recipeRepo.getAllRecipes();
        return ret;
    }
}
