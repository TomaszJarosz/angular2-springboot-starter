package pl.jarosz.tomasz.service;

import pl.jarosz.tomasz.dao.entity.User;

import java.util.List;

/**
 * Created by Tomek on 10.02.2017.
 */

public interface UserService {
    User createUser(User user);

    User getUser(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<User> getUserList();
}
