package com.example.restApiPractice.user.jpa;

import com.example.restApiPractice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {

}
