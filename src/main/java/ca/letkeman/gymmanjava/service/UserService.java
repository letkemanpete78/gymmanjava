package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.dao.RoutineRepository;
import ca.letkeman.gymmanjava.dao.UserRepository;
import ca.letkeman.gymmanjava.models.Routine;
import ca.letkeman.gymmanjava.models.User;
import ca.letkeman.gymmanjava.service.interfaces.CrudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CrudService<User> {

  private final UserRepository userRepository;
  private final RoutineRepository routineRepository;

  public UserService(UserRepository userRepository,
      RoutineRepository routineRepository) {
    this.userRepository = userRepository;
    this.routineRepository = routineRepository;
  }

  @Override
  public boolean delete(String payload) {
    if (payload == null) {
      return false;
    }
    List<User> users = userRepository.findAllByuuidIn(
        Collections.singletonList(payload.replace("{", "").replace("}", "").replace("\"", "")));
    if (users == null) {
      return false;
    }
    userRepository.deleteAll(users);
    User user1 = getUserById(payload);
    return user1 == null;
  }

  @Override
  public User update(String payload) throws JsonProcessingException {
    return parseUser(payload);
  }

  @Override
  public User create(String payload) throws JsonProcessingException {
    return parseUser(payload);
  }

  @Override
  public User get(String id) {
    if (id == null || id.isEmpty()) {
      return null;
    }
    return getUserById(id);
  }

  @Override
  public List<User> list() {
    return (List<User>) userRepository.findAll();
  }

  private User parseUser(String payload) throws JsonProcessingException {
    User user = new ObjectMapper().readValue(payload, new TypeReference<User>() {
    });
    if (user != null) {
      if (user.getRoutines() != null) {
        List<Routine> routines = getRoutineFromDB(user.getRoutines());
        if (!routines.isEmpty()) {
          user.setRoutines(routines);
        }
        user = userRepository.save(updateUserIds(user));
      }
      return user;
    }
    return null;
  }

  private User updateUserIds(User user) {
    if (user.getUuid() == null && user.getId() != null) {
      Optional<User> user1 = userRepository.findById(user.getId());
      user1.ifPresent(value -> user.setUuid(value.getUuid()));
    } else if ((user.getUuid() != null) && (user.getId() == null)) {
      User user1 = userRepository.findByuuid(user.getUuid());
      if (user1 != null) {
        user.setId(user1.getId());
      }
    }
    return user;
  }

  private List<Routine> getRoutineFromDB(List<Routine> oldRoutine) {
    List<Routine> routines = new ArrayList<>();
    for (Routine e : oldRoutine) {
      if (e.getId() != null && e.getId() != 0) {
        Optional<Routine> e1 = routineRepository.findById(e.getId());
        if (e1.isPresent()) {
          e = e1.get();
        } else if (e.getUuid() != null) {
          e = routineRepository.findByuuid(e.getUuid());
        }
      } else if (e.getUuid() != null) {
        e = routineRepository.findByuuid(e.getUuid());
      }
      if (e != null) {
        routines.add(e);
      }
    }
    return routines;
  }

  private User getUserById(String id) {
    Optional<User> user = Optional.empty();
    if (StringUtils.isNumeric(id)) {
      user = userRepository.findById(Integer.valueOf(id));
    }
    if (!user.isPresent()) {
      user = Optional.ofNullable(userRepository.findByuuid(id));
    }
    return user.orElse(null);
  }
}
