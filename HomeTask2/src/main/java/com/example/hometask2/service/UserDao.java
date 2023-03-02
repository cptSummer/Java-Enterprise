package com.example.hometask2.service;

import com.example.hometask2.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    List<User> getByName(String name);

    void deleteById(Long id);

    void createUser(User user);

}
