package ca.letkeman.gymmanjava.dao;

import ca.letkeman.gymmanjava.models.Exercise;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

  Exercise findByuuid(String string);

  List<Exercise> findAllByuuidIn(List<String> strings);


}
