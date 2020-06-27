package com.in28minutes.rest.webservice.restfulwebservices.service;

import com.in28minutes.rest.webservice.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservice.restfulwebservices.model.User;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    private static List<User> users = new LinkedList<>();


    static {
        users.add(User.builder().id(1).name("Teste1").birthday(LocalDate.now()).build());
        users.add(User.builder().id(2).name("Teste2").birthday(LocalDate.now()).build());
    }

    public List<User> findAll() {
        return this.users;
    }

    public User find(Integer id) throws UserNotFoundException {
        return users
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("id=" + id));
    }

    public Integer create(User user) {
        user.setId(users.size() + 1);
        users.add(user);
        return user.getId();
    }

    public Integer update(User user) throws UserNotFoundException {
        User userStored = find(user.getId());
        userStored.setName(user.getName());
        userStored.setBirthday(user.getBirthday());
        return userStored.getId();
    }

    public void delete(Integer id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User userStored = iterator.next();
            if(userStored.getId() == id) {
                iterator.remove();
            }
        }
    }
}
