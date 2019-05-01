package com.dao.user;

import com.model.Role;
import com.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(String userName, String userLogin, String userPassword, Set<Role> role) {
        User user = new User(userName, userLogin, userPassword, role, true);
        entityManager.persist(user);

    }

    public void addUser(User user){
        entityManager.persist(user);
    }

    @Override
    public User getUserByLogin(String login) {
        User user;
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        query.setParameter("login", login);
        user = query.getSingleResult();
        return user;
    }

    @Override
    public User getUserById(long id) {
        User user;
        user = entityManager.find(User.class, id);

        return user;
    }

    @Override
    public void updateUser(String id, String userName, String userLogin, String userPassword, Set<Role> role) {
        long user_id = Long.parseLong(id);
        User user = new User(user_id, userName, userLogin, userPassword, role, true);
        entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public void removeUserById(long id) {
        User entity =  entityManager.find(User.class, id);
        entityManager.remove(entity);

    }
}
