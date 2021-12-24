package com.jpa.develop.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    @Transactional
    Optional<User> deleteUserByUserId(String userId);

    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByUserPhoneNumber(String phoneNumber);

}
