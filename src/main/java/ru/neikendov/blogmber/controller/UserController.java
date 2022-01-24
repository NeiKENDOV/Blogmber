package ru.neikendov.blogmber.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neikendov.blogmber.controller.forms.AddUserForm;
import ru.neikendov.blogmber.models.AppUser;
import ru.neikendov.blogmber.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(userService.loadAllAppUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUser(@PathVariable String username) {
        try {
            AppUser appUser = userService.loadAppUserByUsername(username);
            return ResponseEntity.ok().body(appUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody AddUserForm form) {
        try {
            userService.addUser(form.getUsername(), form.getPassword());
            return ResponseEntity.ok().body("User added");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error to add user");
        }

    }
}
