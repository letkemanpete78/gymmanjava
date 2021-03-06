package ca.letkeman.gymmanjava.service;

import static ca.letkeman.gymmanjava.service.Util.setActivityValues;
import static ca.letkeman.gymmanjava.service.Util.setExerciseValues;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.letkeman.gymmanjava.dao.ExerciseRepository;
import ca.letkeman.gymmanjava.models.Exercise;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest
class ExerciseServiceTest {

  @Mock
  private ExerciseRepository exerciseRepository;

  @InjectMocks
  private ExerciseService exerciseService;

  @Test
  void deleteShouldBeCalled() {
    List<Exercise> exercises = initList(2);
    when(exerciseRepository.findAllByuuidIn(Collections.singletonList("1 exercise test-uuid")))
        .thenReturn(exercises);
    doNothing().when(exerciseRepository).delete(exercises.get(0));
    boolean wasDeleted = exerciseService.delete("1 exercise test-uuid");
    Assertions.assertTrue(wasDeleted);
  }

  @Test
  void updateShouldBeCalled() throws JsonProcessingException {
    Exercise exercise = initList(1).get(0);
    exercise.setActivity(null);
    when(exerciseRepository.save(exercise)).thenReturn(exercise);
    String jsonStr = "{ \"id\": 1,  \"type\": \"TIMED\",  \"value\": 10,  \"unit\": \"seconds\",  \"uuid\": \"1 exercise test uuid\", \"name\": \"1 exercise name\",  \"description\": \"1 exercise test description\",  \"activity\": null  }}";
    Exercise exercise1 = exerciseService.update(jsonStr);
    Assertions.assertEquals(exercise, exercise1);
  }

  @Test
  void saveShouldBeCalled() throws JsonProcessingException {
    Exercise exercise = initList(1).get(0);
    exercise.setActivity(null);
    when(exerciseRepository.save(exercise)).thenReturn(exercise);
    String jsonStr = "{ \"id\": 1,  \"type\": \"TIMED\",  \"value\": 10,  \"unit\": \"seconds\",  \"uuid\": \"1 exercise test uuid\", \"name\": \"1 exercise name\",  \"description\": \"1 exercise test description\",  \"activity\": null  }}";
    Exercise exercise1 = exerciseService.create(jsonStr);
    Assertions.assertEquals(exercise, exercise1);
  }

  @Test
  void shouldGetOneExercise() {
    Exercise exercise = initList(1).get(0);
    when(exerciseRepository.findByuuid("1 exercise test-uuid")).thenReturn(exercise);
    exerciseRepository.save(exercise);
    Exercise exercise1 = exerciseService.get("1 exercise test-uuid");
    Assertions.assertEquals(exercise, exercise1);
  }

  @Test
  void shouldCallList() {
    List<Exercise> exercises = initList(3);
    when(exerciseRepository.findAll()).thenReturn(exercises);
    List<Exercise> exerciseList = exerciseService.list();
    Assertions.assertEquals(exerciseList, exercises);
  }

  private List<Exercise> initList(int size) {
    List<Exercise> exercises = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      exercises.add(setExerciseValues(i, setActivityValues(i)));
    }
    return exercises;
  }
}
