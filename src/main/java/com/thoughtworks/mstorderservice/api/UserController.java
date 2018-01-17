package com.thoughtworks.mstorderservice.api;

import com.thoughtworks.mstorderservice.Repository.UserRepository;
import com.thoughtworks.mstorderservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private AuthService authService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> getUserNames() throws Exception {
        return Arrays.asList("zhangSan", "liSi");
    }
}
