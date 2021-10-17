package com.jpa.develop.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUserId(String userId);

    boolean existsByUserPhoneNumber(String phoneNumber);

}