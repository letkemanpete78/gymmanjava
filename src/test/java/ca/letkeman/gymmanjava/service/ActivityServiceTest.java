package ca.letkeman.gymmanjava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import ca.letkeman.gymmanjava.dao.ActivityRepository;
import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.models.ResourceFile;
import ca.letkeman.gymmanjava.service.interfaces.StorageService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
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
    when(activityRepository.findAllByuuidIn(Collections.singletonList("1 test uuid"))).thenReturn(activities);
    doNothing().when(activityRepository).delete(activities.get(0));
    boolean wasDeleted = activityService.delete("1 test uuid");
    Assertions.assertTrue(wasDeleted);
  }

  @Test
  void updateShouldBeCalled() {
    Activity activity = initList(1).get(0);
    activity.setResourceFile(null);
    MockMultipartFile mockMultipartFile = new MockMultipartFile("test.txt", "test".getBytes());
    when(activityRepository.save(activity)).thenReturn(activity);
    Activity activity1 = activityService.update (mockMultipartFile,
        "{\"id\":100,\"uuid\":\"1 test uuid\",\"name\":\"1 test name\",\"description\":\"1 test description\",\"resourceFile\":null}");
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
        "{\"id\":100,\"uuid\":\"1 test uuid\",\"name\":\"1 test name\",\"description\":\"1 test description\",\"resourceFile\":null}");
    Assertions.assertEquals(activity,activity1);
  }

  @Test
  void shouldGetOneActivity() {
    Activity activity = initList(1).get(0);
    when(activityRepository.findByuuid("1 test-uuid")).thenReturn(activity);
    activityRepository.save(activity);
    Activity activity1 = activityService.get("1 test-uuid");
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
      ResourceFile resourceFile = new ResourceFile();
      Activity activity = new Activity();
      resourceFile.setDescription(i + " test description");
      resourceFile.setFileName(i + " test-file.jpg");
      resourceFile.setFileSize(123 * i);
      resourceFile.setDateTime(LocalDateTime.now());
      resourceFile.setFileId(i);
      activity.setDescription(i + " test description");
      activity.setName(i + " test name");
      activity.setResourceFile(resourceFile);
      activity.setUuid(i + " test-uuid");
      activity.setId(i);
      activities.add(activity);
    }
    return activities;
  }
}
