package com.redmath.database.application.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/User")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
