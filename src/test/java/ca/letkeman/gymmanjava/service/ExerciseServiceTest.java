package ca.letkeman.gymmanjava.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.letkeman.gymmanjava.dao.ActivityRepository;
import ca.letkeman.gymmanjava.dao.ExerciseRepository;
import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.models.ResourceFile;
import ca.letkeman.gymmanjava.models.Type;
import ca.letkeman.gymmanjava.service.interfaces.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

  @Mock
  private ActivityRepository activityRepository;

  @Mock
  private ResourceFileRepository resourceFileRepository;

  @Mock
  private StorageService storageService;

  @InjectMocks
  private ExerciseService exerciseService;

  @Test
  void deleteShouldBeCalled() {
    List<Exercise> exercises = initList(2);
    when(exerciseRepository.findAllByuuidIn(Collections.singletonList("1 exercise test-uuid"))).thenReturn(exercises);
    doNothing().when(exerciseRepository).delete(exercises.get(0));
    boolean wasDeleted = exerciseService.delete("1 exercise test-uuid");
    Assertions.assertTrue(wasDeleted);
  }

  @Test
  void update() throws JsonProcessingException {
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
    String jsonStr ="{ \"id\": 1,  \"type\": \"TIMED\",  \"value\": 10,  \"unit\": \"seconds\",  \"uuid\": \"1 exercise test uuid\", \"name\": \"1 exercise name\",  \"description\": \"1 exercise test description\",  \"activity\": null  }}";
    Exercise exercise1 = exerciseService.create(jsonStr);
    Assertions.assertEquals(exercise, exercise1);
  }

  @Test
  void shouldGetOneExercise() {
    Exercise exercise = initList(1).get(0);
    when(exerciseRepository.findByuuid("1 exercise test-uuid")).thenReturn(exercise);
    exerciseRepository.save(exercise);
    Exercise exercise1 = exerciseService.get("1 exercise test-uuid");
    Assertions.assertEquals(exercise,exercise1);
  }

  @Test
  void shouldCallList() {
    List<Exercise> exercises = initList(3);
    when(exerciseRepository.findAll()).thenReturn (exercises);
    List<Exercise> exerciseList = exerciseService.list();
    Assertions.assertEquals(exerciseList,exercises);
  }

  private List<Exercise> initList(int size) {
    List<Exercise> exercises = new ArrayList<>();
    for (int i = 1; i <= size; i++) {

      ResourceFile resourceFile = new ResourceFile();
      resourceFile.setDescription(i + " test description");
      resourceFile.setFileName(i + " test-file.jpg");
      resourceFile.setFileSize(123 * i);
      resourceFile.setDateTime(LocalDateTime.now());
      resourceFile.setFileId(i);

      Activity activity = new Activity();
      activity.setDescription(i + " activity test description");
      activity.setName(i + " activity test name");
      activity.setResourceFile(resourceFile);
      activity.setUuid(i + " activity test-uuid");
      activity.setId(i);

      Exercise exercise = new Exercise();
      exercise.setActivity(activity);
      exercise.setUuid(i + " exercise test-uuid");
      exercise.setDescription(i + " exercise test description");
      exercise.setId(i);
      exercise.setName(i + " exercise name");
      exercise.setType(Type.TIMED);
      exercise.setValue(10);
      exercise.setUnit("seconds");
      exercises.add(exercise);
    }
    return exercises;
  }
}
