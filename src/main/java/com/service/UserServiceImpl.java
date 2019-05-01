package com.service;

import com.dao.user.UserDao;
import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
   @Autowired
   private UserDao userDao;


   @Override
   public void addUser(String userName, String userLogin, String userPassword, Set<Role> role) {
      userDao.addUser(userName, userLogin, userPassword, role);
   }

   @Override
   public void addUser(User user){
      userDao.addUser(new User(user.getLogin(), user.getName(), user.getPassword(), user.getRoles(), user.isEnabled()));
   }

   @Override
   public User getUserByLogin(String login) {
      return userDao.getUserByLogin(login);
   }

   @Override
   public User getUserById(long id) {
      return userDao.getUserById(id);
   }

   @Override
   public void updateUser(String id, String userName, String userLogin, String userPassword, Set<Role> role) {
      userDao.updateUser(id, userName, userLogin, userPassword, role);
   }

   @Override
   public List<User> getAllUsers() {

      List<User> allUsers = userDao.getAllUsers();
      return allUsers;
   }

   @Override
   public void removeUserById(long id) {
      userDao.removeUserById(id);
   }

}
