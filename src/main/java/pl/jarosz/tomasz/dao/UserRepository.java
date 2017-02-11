package pl.jarosz.tomasz.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jarosz.tomasz.dao.entity.User;

/**
 * Created by Tomek on 10.02.2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
