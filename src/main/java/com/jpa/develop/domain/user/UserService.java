package com.jpa.develop.domain.user;

import com.jpa.develop.domain.user.exception.PhoneNumberDuplicateException;
import com.jpa.develop.domain.user.exception.UserIdDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User insertUser(User user) {
        validSignUpUser(user);
        user.encodingPassword(passwordEncoder.encode(user.getUserPw()));
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
        if (userRepository.existsByUserPhoneNumber(phoneNumber)) {
            throw new PhoneNumberDuplicateException("PhoneNumber Duplicate Error");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // map 메소드를 이용하면 원하는 형태로 Optional 객체 반환 가능
        return userRepository.findByUserId(userId)
                .map(this::generateUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("This User is not exist"));
    }

    private UserDetails generateUserDetails(User user) {

        return User.builder()
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .roles(user.getRoles())
                .build();
    }

}
