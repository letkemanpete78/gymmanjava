package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.dao.ActivityRepository;
import ca.letkeman.gymmanjava.dao.ExerciseRepository;
import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.service.interfaces.CrudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService implements CrudService<Exercise> {

  private final ExerciseRepository exerciseRepository;
  private final ActivityRepository activityRepository;
  private final ResourceFileRepository resourceFileRepository;

  public ExerciseService(ExerciseRepository exerciseRepository,
      ActivityRepository activityRepository,
      ResourceFileRepository resourceFileRepository) {
    this.exerciseRepository = exerciseRepository;
    this.activityRepository = activityRepository;
    this.resourceFileRepository = resourceFileRepository;
  }

  @Override
  public boolean delete(String payload) {
    if (payload == null){
      return false;
    }
    Exercise exercise = getExerciseById(payload.replace("{", "").replace("}", "").replace("\"", ""));
    if (exercise == null) {
      return false;
    }
    exerciseRepository.delete(exercise);
    Exercise exercise1 = getExerciseById(payload);
    return exercise1 == null;
  }

  @Override
  public Exercise update(String payload) throws JsonProcessingException {
    return parseExercise(payload);
  }

  @Override
  public Exercise create(String payload) throws JsonProcessingException {
    return parseExercise(payload);
  }

  @Override
  public Exercise get(String id) {
    if (id == null || id.isEmpty()) {
      return null;
    }
    return getExerciseById(id);
  }

  @Override
  public List<Exercise> list() {
    return (List<Exercise>) exerciseRepository.findAll();
  }

  private Exercise parseExercise(String payload) throws JsonProcessingException {
    System.out.println(payload);
    Exercise exercise = new ObjectMapper().readValue(payload, new TypeReference<Exercise>() {
    });
    if (exercise != null) {
      if (exercise.getActivity() != null) {
        Activity activity = getActivityFromDB(exercise.getActivity());
        if (activity != null) {
          exercise.setActivity(activity);
        }
        if (exercise.getUuid() == null && exercise.getId() != null) {
          Optional<Exercise> exercise1 = exerciseRepository.findById(exercise.getId());
          if (exercise1.isPresent()) {
            exercise.setUuid(exercise1.get().getUuid());
          }
        } else if (exercise.getUuid() != null && exercise.getId() == null) {
          Exercise exercise1 = exerciseRepository.findByuuid(exercise.getUuid());
          if (exercise1 != null) {
            exercise.setId(exercise1.getId());
          }
        }
        exercise = exerciseRepository.save(exercise);
      }
      return exercise;
    }
    return null;
  }

  private Activity getActivityFromDB(Activity oldActivity) {
    Activity activity = null;
    if (oldActivity.getId() != null && oldActivity.getId() != 0) {
      Optional<Activity> activity1 = activityRepository
          .findById(oldActivity.getId());
      if (activity1.isPresent()) {
        activity = activity1.get();
      } else if (oldActivity.getUuid() != null) {
        activity = activityRepository.findByuuid(oldActivity.getUuid());
      }
    } else if (oldActivity.getUuid() != null) {
      activity = activityRepository.findByuuid(oldActivity.getUuid());
    }
    return activity;
  }

  private Exercise getExerciseById(String id) {
    Optional<Exercise> exercise = null;
    if (StringUtils.isNumeric(id)) {
      exercise = exerciseRepository.findById(Integer.valueOf(id));
    }
    if (exercise == null || !exercise.isPresent()) {
      exercise = Optional.ofNullable(exerciseRepository.findByuuid(id));
    }
    return exercise.orElse(null);
  }
}

