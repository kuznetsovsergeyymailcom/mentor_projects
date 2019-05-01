package com.service;


import com.model.Role;
import com.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(String userName, String userLogin, String userPassword, Set<Role> role);
    void addUser(User user);
    User getUserByLogin(String login);
    User getUserById(long id);
    void updateUser(String id, String userName, String userLogin, String userPassword, Set<Role> role);
    List<User> getAllUsers();
    void removeUserById(long id);
}
