package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.dao.ActivityRepository;
//import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.models.Activity;
//import ca.letkeman.gymmanjava.models.ResourceFile;
//import ca.letkeman.gymmanjava.service.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/activity")
public class ActivityController {

  private final ActivityRepository activityRepository;
//  private final ResourceFileRepository resourceFileRepository;
//  private final StorageService storage;

  public ActivityController(ActivityRepository activityRepository/*,
       ResourceFileRepository resourceFileRepository,StorageService storage*/) {
    this.activityRepository = activityRepository;
//    this.resourceFileRepository = resourceFileRepository;
//    this.storage = storage;
  }

  @DeleteMapping
  public boolean delete(@RequestBody String payload) {
    return deleteActivites(payload);
  }

  @PutMapping
  public Activity update(@RequestParam("file") MultipartFile file, @RequestBody String payload) {
    return getActivity(file, payload);
  }

  @PostMapping
  public Activity create(@RequestParam("file") MultipartFile file, @RequestBody String payload) {
    return getActivity(file, payload);
  }

  @GetMapping("/{id}")
  public Activity get(@PathVariable String id) {
    return activityRepository.findByuuid(id);
  }

  @GetMapping("/list")
  public List<Activity> list() {
    return (List<Activity>) activityRepository.findAll();
  }

  private Activity getActivity(
      MultipartFile file,
      String payload) {
    Activity activity = new Activity();
    try {
      activity = new ObjectMapper().readValue(payload, new TypeReference<Activity>() {});

//      if (activity != null) {
//        String deleteFile = activityRepository.findByuuid(activity.getUuid()).getResourceFile()
//            .getFileName();
//        if (deleteFile != null) {
//          storage.delete(deleteFile);
//        }
//        ResourceFile resourceFile = new ResourceFile();
//        if (file != null) {
//          storage.store(file);
//          resourceFile.setFileName(file.getName());
//          resourceFile.setFileSize(file.getSize());
//          resourceFile.setDescription("someting");
//          resourceFile.setDateTime(LocalDateTime.now());
//        }
//        activity.setResourceFile(resourceFile);
//      }
      activity = activityRepository.save(activity);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return activity;
  }

  private boolean deleteActivites(String payload) {
    if (!payload.isEmpty()) {
      List<Activity> activities = null;
      try {
        Iterable<String> list = new ObjectMapper().readValue(payload, List.class);
        activities = activityRepository.findAllByuuidIn(Collections.singletonList(list.toString()));
        if (activities.isEmpty()) {
          return false;
        }
        activityRepository.deleteAll(activities);
        if (activityRepository.findAllByuuidIn(Collections.singletonList(list.toString()))
            .isEmpty()) {
//          activities.stream().forEach(x -> storage.delete(x.getResourceFile().getFileName()));
          return true;
        }
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        return false;
      }
    }
    return false;
  }
}
