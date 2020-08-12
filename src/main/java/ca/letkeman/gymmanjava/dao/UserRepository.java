package ca.letkeman.gymmanjava.dao;

import ca.letkeman.gymmanjava.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  User findByuuid(String string);
}
