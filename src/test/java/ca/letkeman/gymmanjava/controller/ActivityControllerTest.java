//package ca.letkeman.gymmanjava.controller;
//
//import ca.letkeman.gymmanjava.dao.ActivityRepository;
//import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
//import ca.letkeman.gymmanjava.models.Activity;
//import ca.letkeman.gymmanjava.models.ResourceFile;
//import ca.letkeman.gymmanjava.service.ActivityService;
//import ca.letkeman.gymmanjava.service.ActivityServiceImpl;
//import ca.letkeman.gymmanjava.service.StorageService;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//
//@ActiveProfiles("test")
//@SpringBootTest
//class ActivityControllerTest {
//
//  @Mock
//  private ActivityRepository activityRepository;
//
//  @Mock
//  private ResourceFileRepository resourceFileRepository;
//
//  @Mock
//  private StorageService storage;
//
//  private ActivityService activityService;
//
//  @BeforeEach
//  void setUp() {
//    activityService = new ActivityServiceImpl(activityRepository,resourceFileRepository,storage);
//  }
//
//  @Test
//  void delete() {
////    List<Activity> activities = initList();
////    when(activityRepository.findAll()).thenReturn(activities);
////    activityService.delete("1 test uuid");
////    System.out.printf(String.valueOf(activityService.list()));
//  }
//
//  private List<Activity> initList() {
//    List<Activity> activities = new ArrayList<>();
//    for (int i = 1;i<=5;i++) {
//      ResourceFile resourceFile = new ResourceFile();
//      Activity activity = new Activity();
//      resourceFile.setDescription(i + " test description");
//      resourceFile.setFileName(i + " test-file.jpg");
//      resourceFile.setFileSize(123 * i);
//      resourceFile.setDateTime(LocalDateTime.now());
//      resourceFile.setFileId(i);
//      activity.setDescription(i + " test description");
//      activity.setName(i + " test name");
//      activity.setResourceFile(resourceFile);
//      activity.setUuid(i + " test-uuid");
//      activity.setId(i);
//      activities.add(activity);
//    }
//    return activities;
//  }
//
//  @Test
//  void update() {
//  }
//
//  @Test
//  void create() {
//  }
//
//  @Test
//  void get() {
//  }
//
//  @Test
//  void list() {
//  }
//}
