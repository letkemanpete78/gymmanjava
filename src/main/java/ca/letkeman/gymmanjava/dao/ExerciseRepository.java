package ca.letkeman.gymmanjava.dao;

import ca.letkeman.gymmanjava.models.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

}
