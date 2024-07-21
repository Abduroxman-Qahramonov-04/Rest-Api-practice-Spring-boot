package com.example.restApiPractice.user.jpa;

import com.example.restApiPractice.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
