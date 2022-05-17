package com.example.exspring.service;

import com.example.exspring.entity.User;
import com.example.exspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    public List<User> getAll() {
        return  userRepository.findAll();
    }

    public User findById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<User> byId = userRepository.findById(uuid);
        if(byId.isEmpty()){
            return null;
        }
        return byId.get();
    }

    public User update(String id, User user) {
        Optional<User> byId = userRepository.findById(UUID.fromString(id));
        if(byId.isEmpty()){
            return null;
        }
        User user1 = byId.get();
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        user1.setAge(user.getAge());
        return userRepository.save(user1);


    }

    public boolean delete(String id) {
        Optional<User> byId = userRepository.findById(UUID.fromString(id));
        if(byId.isEmpty()){
            return false;
        }
        userRepository.deleteById(UUID.fromString(id));
        return true;
    }
}
