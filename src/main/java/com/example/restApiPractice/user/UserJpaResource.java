package com.example.restApiPractice.user;

import com.example.restApiPractice.exceptions.UserNotFoundException;
import com.example.restApiPractice.user.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {
    private UserRepository repository;

    public UserJpaResource(UserRepository repository){
        this.repository = repository;
    }
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return this.repository.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) throw  new UserNotFoundException("User not found with id:"+id);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
    @DeleteMapping("/jpa/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer id){
        repository.deleteById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) throw  new UserNotFoundException("User not found with id:"+id);
        return user.get().getPosts();
    }
    @PostMapping("/jpa/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User user1 = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user1.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
