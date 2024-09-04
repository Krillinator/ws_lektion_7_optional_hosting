package com.krillinator.ws_lektion_7_optional_hosting.controller;

import com.krillinator.ws_lektion_7_optional_hosting.model.User;
import com.krillinator.ws_lektion_7_optional_hosting.repository.UserRepository;
import com.krillinator.ws_lektion_7_optional_hosting.response.ErrorResponse;
import com.krillinator.ws_lektion_7_optional_hosting.response.UserResponse;
import com.krillinator.ws_lektion_7_optional_hosting.response.WsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @GetMapping("/{id}")
    public ResponseEntity<WsResponse> findUserById(
            @PathVariable("id") Long id
    ) {

        Optional<User> user = userRepository.findById(id);

        // Is user NULL?
        if (user.isPresent()) {

            // Convert Optional<User> --> User
            User tempUser = user.get();
            List<User> userList = new ArrayList<>();
            userList.add(tempUser);

            return ResponseEntity.ok(new UserResponse());
        }

        // return ResponseEntity.noContent().build() <-- WORKS
        return ResponseEntity
                .status(204)
                .body(
                        new ErrorResponse(
                                """
                                The user did not exist, therefore try again!
                                """)
                );
    }




}
