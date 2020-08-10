package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.dao.ExerciseRepository;
import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.dao.RoutineRepository;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.models.Routine;
import ca.letkeman.gymmanjava.service.interfaces.CrudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class RoutineService implements CrudService<Routine> {

  private final RoutineRepository routineRepository;
  private final ExerciseRepository exerciseRepository;

  public RoutineService(RoutineRepository routineRepository,
      ExerciseRepository exerciseRepository,
      ResourceFileRepository resourceFileRepository) {
    this.routineRepository = routineRepository;
    this.exerciseRepository = exerciseRepository;
  }

  @Override
  public boolean delete(String payload) {
    if (payload == null) {
      return false;
    }
    Routine routine = getRoutineById(payload.replace("{", "").replace("}", "").replace("\"", ""));
    if (routine == null) {
      return false;
    }
    routineRepository.delete(routine);
    Routine routine1 = getRoutineById(payload);
    return routine1 == null;
  }

  @Override
  public Routine update(String payload) throws JsonProcessingException {
    return parseRoutine(payload);
  }

  @Override
  public Routine create(String payload) throws JsonProcessingException {
    return parseRoutine(payload);
  }

  @Override
  public Routine get(String id) {
    if (id == null || id.isEmpty()) {
      return null;
    }
    return getRoutineById(id);
  }

  @Override
  public List<Routine> list() {
    return (List<Routine>) routineRepository.findAll();
  }

  private Routine parseRoutine(String payload) throws JsonProcessingException {
    System.out.println(payload);
    Routine routine = new ObjectMapper().readValue(payload, new TypeReference<Routine>() {
    });
    if (routine != null) {
      if (routine.getExercises() != null) {
        List<Exercise> exercises = getExerciseFromDB(routine.getExercises());
        if (exercises != null) {
          routine.setExercises(exercises);
        }
        routine = routineRepository.save(updateRoutineIds(routine));
      }
      return routine;
    }
    return null;
  }

  private Routine updateRoutineIds(Routine routine) {
    if (routine.getUuid() == null && routine.getId() != null) {
      Optional<Routine> routine1 = routineRepository.findById(routine.getId());
      routine1.ifPresent(value -> routine.setUuid(value.getUuid()));
    } else if ((routine.getUuid() != null) && (routine.getId() == null)) {
      Routine routine1 = routineRepository.findByuuid(routine.getUuid());
      if (routine1 != null) {
        routine.setId(routine1.getId());
      }
    }
    return routine;
  }

  private List<Exercise> getExerciseFromDB(List<Exercise> oldExercise) {
    List<Exercise> exercise = null;
    for (Exercise e : oldExercise) {
      if (e.getId() != null && e.getId() != 0) {
        Optional<Exercise> e1 = exerciseRepository.findById(e.getId());
        if (e1.isPresent()) {
          e = e1.get();
        } else if (e.getUuid() != null) {
          e = exerciseRepository.findByuuid(e.getUuid());
        }
      } else if (e.getUuid() != null) {
        e = exerciseRepository.findByuuid(e.getUuid());
      }
      Objects.requireNonNull(exercise).add(e);
    }
    return exercise;
  }

  private Routine getRoutineById(String id) {
    Optional<Routine> routine = Optional.empty();
    if (StringUtils.isNumeric(id)) {
      routine = routineRepository.findById(Integer.valueOf(id));
    }
    if (!routine.isPresent()) {
      routine = Optional.ofNullable(routineRepository.findByuuid(id));
    }
    return routine.orElse(null);
  }
}
