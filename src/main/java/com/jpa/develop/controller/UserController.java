package com.jpa.develop.controller;

import com.jpa.develop.domain.user.UserService;
import com.jpa.develop.dto.user.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final EntityManager entityManger;

    @PostMapping("")
    public void insertUser(@Valid @RequestBody UserSignUpDto userSignUpDto) {
        Long userNo = userService.insertUser(userSignUpDto.toEntity());
    }

}
