package com.example.exspring.controller;

import com.example.exspring.entity.User;
import com.example.exspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        User newUser = userService.save(user);
        if (newUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }



    @GetMapping
    public  ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        User byId = userService.findById(id);
        if(byId == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(byId);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody User user){
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable("id") String id){
        if(userService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
