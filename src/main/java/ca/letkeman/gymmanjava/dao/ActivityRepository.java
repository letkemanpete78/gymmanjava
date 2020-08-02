package ca.letkeman.gymmanjava.dao;

import ca.letkeman.gymmanjava.models.Activity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {

  List<Activity> findAllByuuidIn(List<String> strings);

  Activity findByuuid(String string);
}
