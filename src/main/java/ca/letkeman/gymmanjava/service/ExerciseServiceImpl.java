package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.dao.ExerciseRepository;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.service.interfaces.ExerciseService;
import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {

  private final ExerciseRepository exerciseRepository;

  public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
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
  public Exercise create(String payload) {
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
