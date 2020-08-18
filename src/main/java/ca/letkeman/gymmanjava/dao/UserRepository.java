package ca.letkeman.gymmanjava.dao;

import ca.letkeman.gymmanjava.models.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  User findByuuid(String string);

  List<User> findAllByuuidIn(List<String> strings);
}
