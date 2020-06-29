package com.vrush.controller;

import com.vrush.model.User;
import com.vrush.model.UserDto;
import com.vrush.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }


    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

}
