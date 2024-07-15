package com.example.restApiPractice.user;

import com.example.restApiPractice.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public User retrieveUserById(@PathVariable Integer id){
        User user = userDaoService.findById(id);
        if(user==null) throw  new UserNotFoundException("User not found with id:"+id);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer id){
        userDaoService.deleteUserById(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User user1 = userDaoService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user1.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
