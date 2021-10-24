package com.jpa.develop.domain.user;

import com.jpa.develop.domain.user.exception.PhoneNumberDuplicateException;
import com.jpa.develop.domain.user.exception.UserIdDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User insertUser(User user) {
        System.out.println("user = " + user.getUserId());
        System.out.println("user = " + user.getUserBirthDate());
        validSignUpUser(user);
        return userRepository.save(user);
    }

    private void validSignUpUser(User user) {
        existByUserId(user.getUserId());
        existByPhoneNumber(user.getUserPhoneNumber());
    }

    public void existByUserId(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new UserIdDuplicateException("Id Duplicate Error");
        }
    }

    public void existByPhoneNumber(String phoneNumber) {
        if(userRepository.existsByUserPhoneNumber(phoneNumber)) {
            throw new PhoneNumberDuplicateException("PhoneNumber Duplicate Error");
        }
    }

}
