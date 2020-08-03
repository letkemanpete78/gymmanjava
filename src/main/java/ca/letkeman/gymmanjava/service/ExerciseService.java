package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.dao.ExerciseRepository;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.service.interfaces.CrudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService implements CrudService<Exercise> {

  private final ExerciseRepository exerciseRepository;

  public ExerciseService(ExerciseRepository exerciseRepository) {
    this.exerciseRepository = exerciseRepository;
  }

  @Override
  public boolean delete(String payload) {
    Exercise exercise = getExerciseById(payload);
    if (exercise == null){
      return false;
    }
    exerciseRepository.delete(exercise);
    Exercise exercise1 = getExerciseById(payload);
    return exercise1 == null;
  }

  @Override
  public Exercise update(String payload) throws JsonProcessingException {
    return saveExercise(payload);
  }

  @Override
  public Exercise create(String payload) throws JsonProcessingException {
    return saveExercise(payload);
  }

  @Override
  public Exercise get(String id) {
    if (id == null || id.isEmpty() ){
      return null;
    }
    return getExerciseById(id);
  }

  @Override
  public List<Exercise> list() {
    return (List<Exercise>) exerciseRepository.findAll();
  }

  private Exercise saveExercise(String payload) throws JsonProcessingException {
    Exercise exercise = new ObjectMapper().readValue(payload, new TypeReference<Exercise>() {});
    if (exercise != null) {
      exercise = exerciseRepository.save(exercise);
      return exercise;
    }
    return null;
  }

  private Exercise getExerciseById(String id) {
    Optional<Exercise> exercise = exerciseRepository.findById(Integer.valueOf(id));
    return exercise.orElse(null);
  }
}
