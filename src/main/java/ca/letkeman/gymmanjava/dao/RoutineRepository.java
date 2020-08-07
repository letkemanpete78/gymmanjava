package ca.letkeman.gymmanjava.dao;

import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.models.Routine;
import org.springframework.data.repository.CrudRepository;

public interface RoutineRepository extends CrudRepository<Routine, Integer> {

  Routine findByuuid(String string);
}
