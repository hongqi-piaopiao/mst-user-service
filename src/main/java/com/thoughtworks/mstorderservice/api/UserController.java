package com.thoughtworks.mstorderservice.api;

import com.thoughtworks.mstorderservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{id}")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/address")
    @ResponseStatus(HttpStatus.OK)
    public String getAddress(@PathVariable("id") String userId) {
        return userRepository.findById(userId).getAddress();
    }
}
