package pl.jarosz.tomasz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jarosz.tomasz.dao.UserRepository;
import pl.jarosz.tomasz.dao.entity.User;
import pl.jarosz.tomasz.service.UserService;

import java.util.List;

/**
 * Created by Tomek on 10.02.2017.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return repository.findOne(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = repository.findOne(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return repository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        repository.delete(id);

    }

    @Override
    public List<User> getUserList() {
        List<User> userList = (List<User>) repository.findAll();
        return userList;
    }


}
