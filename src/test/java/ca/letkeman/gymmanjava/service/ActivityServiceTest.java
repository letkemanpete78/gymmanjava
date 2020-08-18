package ca.letkeman.gymmanjava.service;

import static ca.letkeman.gymmanjava.service.Util.setActivityValues;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.letkeman.gymmanjava.dao.ActivityRepository;
import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.service.interfaces.StorageService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class ActivityServiceTest {

  @Mock
  private ActivityRepository activityRepository;

  @Mock
  private ResourceFileRepository resourceFileRepository;

  @Mock
  private StorageService storage;

  @InjectMocks
  private ActivityService activityService;

  @Test
  void deleteShouldBeCalled() {
    List<Activity> activities = initList(2);
    when(activityRepository.findAllByuuidIn(Collections.singletonList("1 activity test uuid")))
        .thenReturn(activities);
    doNothing().when(activityRepository).delete(activities.get(0));
    boolean wasDeleted = activityService.delete("1 activity test uuid");
    Assertions.assertTrue(wasDeleted);
  }

  @Test
  void updateShouldBeCalled() {
    Activity activity = initList(1).get(0);
    activity.setResourceFile(null);
    MockMultipartFile mockMultipartFile = new MockMultipartFile("test.txt", "test".getBytes());
    when(activityRepository.save(activity)).thenReturn(activity);
    Activity activity1 = activityService.update(mockMultipartFile,
        "{\"id\":100,\"uuid\":\"1 activity test uuid\",\"name\":\"1 activity test name\",\"description\":\"1 activity test description\",\"resourceFile\":null}");
    Assertions.assertEquals(activity, activity1);
  }

  @Test
  void shouldThrowParseError() {
    Activity activity = initList(1).get(0);
    MockMultipartFile mockMultipartFile = new MockMultipartFile("test.txt", "test".getBytes());
    Assertions.assertThrows(JsonParseException.class, () -> {
      Activity activity1 = activityService.create(mockMultipartFile,
          "invalid content");
    });
  }

  @Test
  void saveShouldBeCalled() throws JsonProcessingException {
    Activity activity = initList(1).get(0);
    activity.setResourceFile(null);
    MockMultipartFile mockMultipartFile = new MockMultipartFile("test.txt", "test".getBytes());
    when(activityRepository.save(activity)).thenReturn(activity);
    Activity activity1 = activityService.create(mockMultipartFile,
        "{\"id\":100,\"uuid\":\"1 activity test uuid\",\"name\":\"1 activity test name\",\"description\":\"1 activity test description\",\"resourceFile\":null}");
    Assertions.assertEquals(activity, activity1);
  }

  @Test
  void shouldGetOneActivity() {
    Activity activity = initList(1).get(0);
    when(activityRepository.findByuuid("1 activity test-uuid")).thenReturn(activity);
    activityRepository.save(activity);
    Activity activity1 = activityService.get("1 activity test-uuid");
    assertEquals(activity1.getUuid(), activity.getUuid());
  }

  @Test
  void shouldCallList() {
    List<Activity> activities = initList(3);
    when((activityRepository.findAll())).thenReturn(activities);
    List<Activity> activityList = activityService.list();
    Assertions.assertEquals(activities, activityList);
  }

  private List<Activity> initList(int size) {
    List<Activity> activities = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      activities.add(setActivityValues(i));
    }
    return activities;
  }
}
