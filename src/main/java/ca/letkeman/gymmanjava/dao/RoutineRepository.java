package ca.letkeman.gymmanjava.dao;

import ca.letkeman.gymmanjava.models.Routine;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RoutineRepository extends CrudRepository<Routine, Integer> {

  Routine findByuuid(String string);

  List<Routine> findAllByuuidIn(List<String> strings);
}
