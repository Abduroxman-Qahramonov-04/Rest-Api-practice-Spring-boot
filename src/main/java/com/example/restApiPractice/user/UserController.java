package com.example.restApiPractice.user;

import com.example.restApiPractice.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserDaoService userDaoService;
    public UserController(UserDaoService userDaoService){
        this.userDaoService = userDaoService;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return this.userDaoService.findAll();
    }
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable Integer id){
        User user = userDaoService.findById(id);
        if(user==null) throw  new UserNotFoundException("User not found with id:"+id);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer id){
        userDaoService.deleteUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User user1 = userDaoService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user1.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
