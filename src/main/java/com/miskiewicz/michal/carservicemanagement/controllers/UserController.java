package com.miskiewicz.michal.carservicemanagement.controllers;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import com.miskiewicz.michal.carservicemanagement.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<User> getUserInfo(){
        return ResponseEntity.ok(userService.getUser());
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user){
        UserDTO userDTO = userService.addUser(user);
        System.out.println(userDTO.getCar().get(0).getBrand());
        return ResponseEntity.ok(userDTO);
    }
}
