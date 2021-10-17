package com.jpa.develop.domain.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Long insertUser(User user) {
        userRepository.save(user);
        return user.getSeq();
    }

    public void validSignUpUser(User user) {

    }

    private void existByUserId(String userId) {

    }

    private void existByPhoneNumber(String phoneNumber) {

    }
}
