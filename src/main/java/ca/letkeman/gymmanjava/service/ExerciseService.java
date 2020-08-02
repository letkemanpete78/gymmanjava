package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.dao.ExerciseRepository;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.service.interfaces.CrudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService implements CrudService<Exercise> {

  private final ExerciseRepository exerciseRepository;

  public ExerciseService(ExerciseRepository exerciseRepository) {
    this.exerciseRepository = exerciseRepository;
  }

  @Override
  public boolean delete(String payload) {
    return false;
  }

  @Override
  public Exercise update(String payload) {
    return null;
  }

  @Override
  public Exercise create(String payload) throws JsonProcessingException {
    return null;
  }

  @Override
  public Exercise get(String id) {
    return null;
  }

  @Override
  public List<Exercise> list() {
    return null;
  }
}
