package com.krillinator.ws_lektion_7_optional_hosting.controller;

import com.krillinator.ws_lektion_7_optional_hosting.model.User;
import com.krillinator.ws_lektion_7_optional_hosting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {

        return ResponseEntity.ok(userRepository.findAll());
    }

    // TODO - 10:06 - returnerar vi :)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findUserById(
            @PathVariable("id") Long id
    ) {

        Optional<User> user = userRepository.findById(id);

        // Is user NULL?
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        }

        // return ResponseEntity.noContent().build() <-- WORKS
        return ResponseEntity.status(204).body("");
    }




}
