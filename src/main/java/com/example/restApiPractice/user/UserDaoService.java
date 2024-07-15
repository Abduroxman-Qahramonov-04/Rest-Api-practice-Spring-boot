package com.example.restApiPractice.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDaoService {
    private static Integer userCount = 0;
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(++userCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount,"Jim", LocalDate.now().minusYears(10)));
    }
    public List<User> findAll(){
        return users;
    }
    public User findById(Integer id){
        for(User user : users){
            if(Objects.equals(user.getId(), id))
                return user;
        }
        return null;
    }
    public User addUser(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
    public User deleteUserById(Integer id){
        for (User user : users){
            if(Objects.equals(user.getId(),id)){
                users.remove(user);
                return user;
            }

        }
        return null;
    }
}
