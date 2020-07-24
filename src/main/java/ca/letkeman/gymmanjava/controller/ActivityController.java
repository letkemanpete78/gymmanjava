package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.dao.ActivityRepository;
import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.models.ResourceFile;
import ca.letkeman.gymmanjava.service.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/activity")
public class ActivityController {

  private final ActivityRepository activityRepository;
  private final ResourceFileRepository resourceFileRepository;
  private final StorageService storage;

  public ActivityController(ActivityRepository activityRepository,
      ResourceFileRepository resourceFileRepository, StorageService storage) {
    this.activityRepository = activityRepository;
    this.resourceFileRepository = resourceFileRepository;
    this.storage = storage;
  }

  @DeleteMapping
  public boolean delete(@RequestBody String payload) {
    return deleteActivites(payload);
  }

  @PutMapping
  public Activity update(@RequestParam("file") MultipartFile file,
      @RequestParam("payload") String payload) {
    return getActivity(file, payload);
  }

  @PostMapping("/")
  public Activity create(@RequestParam("file") MultipartFile file,
      @RequestParam("payload") String payload) {
    return getActivity(file, payload);
  }

  @RequestMapping(value = "/{id}",
      method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = MediaType.ALL_VALUE)
  public Activity get(@PathVariable String id) {
    return activityRepository.findByuuid(id);
  }

  @RequestMapping(value = "/",
      method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = MediaType.ALL_VALUE)
  public List<Activity> list() {
    return (List<Activity>) activityRepository.findAll();
  }

  private Activity getActivity(
      MultipartFile file,
      String payload) {
    Activity activity = new Activity();
    try {
      activity = new ObjectMapper().readValue(payload, new TypeReference<Activity>() {
      });
      String fileDescription = null;
      if ((activity != null) && (activity.getUuid() != null)) {
        Activity deleteFile = activityRepository.findByuuid(activity.getUuid());
        if ((deleteFile != null) && (deleteFile.getResourceFile() != null) && (
            deleteFile.getResourceFile().getFileName() != null)) {
          storage.delete(deleteFile.getResourceFile().getFileName());
          activity.setId(deleteFile.getId());
          if (deleteFile.getResourceFile().getDescription() != null) {
            fileDescription = deleteFile.getResourceFile().getDescription();
          }
        }
        ResourceFile resourceFile = new ResourceFile();
        if (file != null) {
          storage.store(file);
          if ((activity.getResourceFile() != null) && (activity.getResourceFile().getDescription()
              != null)) {
            fileDescription = activity.getResourceFile().getDescription();
          }
          resourceFile.setFileName(file.getOriginalFilename());
          resourceFile.setFileSize((int) file.getSize());
          resourceFile.setDescription(fileDescription);
          resourceFile.setDateTime(LocalDateTime.now());
          resourceFile = resourceFileRepository.save(resourceFile);
        }
        activity.setResourceFile(resourceFile);
        activity = activityRepository.save(activity);
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return activity;
  }

  private boolean deleteActivites(String payload) {
    if (!payload.isEmpty()) {
      List<Activity> activities;
      try {
        List<String> list = Arrays.asList(payload.replace("\"","").split(","));
        activities = activityRepository.findAllByuuidIn(list);
        if (activities.isEmpty()) {
          return false;
        }
        activityRepository.deleteAll(activities);
        activities.forEach(x -> {
          if ((x.getResourceFile() != null) && (x.getResourceFile().getFileName() != null)) {
            storage.delete(x.getResourceFile().getFileName());
         }
        });

        resourceFileRepository.deleteAll(
            activities.stream().map(Activity::getResourceFile).collect(Collectors.toList()));
        if (activityRepository.findAllByuuidIn(Collections.singletonList(list.toString()))
            .isEmpty()) {
          return true;
        }
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
    }
    return false;
  }
}
