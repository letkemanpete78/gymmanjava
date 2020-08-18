package ca.letkeman.gymmanjava.service;

import static ca.letkeman.gymmanjava.service.Util.setActivityValues;
import static ca.letkeman.gymmanjava.service.Util.setExerciseValues;
import static ca.letkeman.gymmanjava.service.Util.setRoutineValues;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.letkeman.gymmanjava.dao.RoutineRepository;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.models.Routine;
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
class RoutineServiceTest {

  @Mock
  private RoutineRepository routineRepository;

  @InjectMocks
  private RoutineService routineService;

  @Test
  void deleteShouldBeCalled() {
    List<Routine> routines = initList(2);
    when(routineRepository.findAllByuuidIn(Collections.singletonList("1 routine test-uuid")))
        .thenReturn(routines);
    doNothing().when(routineRepository).delete(routines.get(0));
    boolean wasDeleted = routineService.delete("1 routine test-uuid");
    Assertions.assertTrue(wasDeleted);
  }

  @Test
  void updateShouldBeCall() throws JsonProcessingException {
    Routine routine = initList(1).get(0);
    routine.setExercises(null);
    when(routineRepository.save(routine)).thenReturn(routine);
    String jsonStr = "{\"id\": 1,  \"uuid\": \"1 routine test-uuid\",  \"name\": \"1 routine test name\",  \"description\": \"1 routine test description\",  \"exercises\": null}";
    Routine routine1 = routineService.update(jsonStr);
    Assertions.assertTrue(routine.equals(routine1));
  }

  @Test
  void savedShouldBeCalled() throws JsonProcessingException {
    Routine routine = initList(1).get(0);
    routine.setExercises(null);
    when(routineRepository.save(routine)).thenReturn(routine);
    String jsonStr = "{\"id\": 1,  \"uuid\": \"1 routine test-uuid\",  \"name\": \"1 routine test name\",  \"description\": \"1 routine test description\",  \"exercises\": null}";
    Routine routine1 = routineService.create(jsonStr);
    Assertions.assertTrue(routine.equals(routine1));
  }

  @Test
  void shouldGetOneRoutine() {
    Routine routine = initList(1).get(0);
    when(routineRepository.findByuuid("1 routine test-uuid")).thenReturn(routine);
    Routine routine1 = routineService.get("1 routine test-uuid");
    Assertions.assertEquals(routine, routine1);
  }

  @Test
  void shouldCallList() {
    List<Routine> routines = initList(3);
    when(routineRepository.findAll()).thenReturn(routines);
    List<Routine> routineList = routineService.list();
    Assertions.assertEquals(routineList, routines);
  }


  private List<Routine> initList(int size) {
    List<Routine> routines = new ArrayList<>();
    for (int j = 1; j <= size; j++) {
      List<Exercise> exercises = new ArrayList<>();
      for (int i = 1; i <= size; i++) {
        exercises.add(setExerciseValues(i, setActivityValues(i)));
      }
      routines.add(setRoutineValues(j, exercises));
    }
    return routines;
  }
}
