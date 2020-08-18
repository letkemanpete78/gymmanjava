package ca.letkeman.gymmanjava.service;

import static ca.letkeman.gymmanjava.service.Util.setActivityValues;
import static ca.letkeman.gymmanjava.service.Util.setExerciseValues;
import static ca.letkeman.gymmanjava.service.Util.setRoutineValues;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.letkeman.gymmanjava.dao.UserRepository;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.models.Routine;
import ca.letkeman.gymmanjava.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  void deleteShouldBeCall() {
    List<User> userList = initList(2);
    doNothing().when(userRepository).delete(userList.get(0));
    boolean wasDeleted = userService.delete("1 exercise test-uuid");
    Assertions.assertTrue(wasDeleted);
  }

  @Test
  void updateShouldBeCalled() throws JsonProcessingException {
    User user = initList(1).get(0);
    user.setRoutines(null);
    when(userRepository.save(user)).thenReturn(user);
    String jsonStr = "{\"id\": 1, \"uuid\": \"1 user test-uuid\", \"fName\": \"1 user test-fname\", \"routines\": null}";
    User user1 = userService.update(jsonStr);
    Assertions.assertEquals(user, user1);
  }

  @Test
  void savedShouldBeCalled() throws JsonProcessingException {
    User user = initList(1).get(0);
    user.setRoutines(null);
    when(userRepository.save(user)).thenReturn(user);
    String jsonStr = "{\"id\": 1, \"uuid\": \"1 user test-uuid\", \"fName\": \"1 user test-fname\", \"routines\": null}";
    User user1 = userService.create(jsonStr);
    Assertions.assertEquals(user, user1);
  }

  @Test
  void shouldGetOneUser() {
    User user = initList(1).get(0);
    when(userRepository.findByuuid("1 user test-uuid")).thenReturn(user);
    User user1 = userService.get("1 user test-uuid");
    Assertions.assertEquals(user, user1);
  }

  @Test
  void shouldCallList() {
    List<User> users = initList(3);
    when(userRepository.findAll()).thenReturn(users);
    List<User> users1 = userService.list();
    Assertions.assertEquals(users, users1);
  }

  private List<User> initList(int size) {
    List<User> users = new ArrayList<>();
    for (int k = 1; k <= size; k++) {
      List<Routine> routines = new ArrayList<>();
      for (int j = 1; j <= size; j++) {
        List<Exercise> exercises = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
          exercises.add(setExerciseValues(i, setActivityValues(i)));
        }
        Routine routine = setRoutineValues(j, exercises);
        routines.add(routine);
      }
      User user= new User();
      user.setRoutines(routines);
      user.setfName(k + " user test-fname");
      user.setId(k);
      user.setUuid(k + " user test-uuid");
      users.add(user);
    }
    return users;
  }
}
