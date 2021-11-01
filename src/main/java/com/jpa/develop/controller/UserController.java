package com.jpa.develop.controller;

import com.jpa.develop.common.ApiResponse;
import com.jpa.develop.domain.user.User;
import com.jpa.develop.domain.user.UserRepository;
import com.jpa.develop.domain.user.UserService;
import com.jpa.develop.dto.user.UserResponseDto;
import com.jpa.develop.dto.user.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<ApiResponse> insertUser(@Valid @RequestBody UserSignUpDto userSignUpDto) {

        User user = userService.insertUser(userSignUpDto.toEntity());

        ApiResponse response = ApiResponse.builder()
                .message("성공적으로 회원가입이 완료 되었습니다")
                .data(UserResponseDto.toUserResponse(user))
                .status(201)
                .build();

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/exist/userId/{userId}")
    public ResponseEntity<ApiResponse> existByUserId(@PathVariable String userId) {

        boolean isExistUserId = userRepository.existsByUserId(userId);

        ApiResponse response = ApiResponse.builder()
                .message(isExistUserId ? "이미 존재하는 아이디 입니다" : "사용 가능한 아이디 입니다")
                .data(isExistUserId)
                .status(201)
                .build();

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/exist/phone/{phoneNumber}")
    public ResponseEntity<ApiResponse> existUserPhoneNumber(@PathVariable String userPhoneNumber) {

        boolean isExistPhoneNumber = userRepository.existsByUserPhoneNumber(userPhoneNumber);

        ApiResponse response = ApiResponse.builder()
                .message(isExistPhoneNumber ? "이미 존재하는 핸드폰 번호 입니다" : "사용 가능한 핸드폰 번호 입니다")
                .data(isExistPhoneNumber)
                .status(201)
                .build();

        return ResponseEntity.status(201).body(response);
    }

}
