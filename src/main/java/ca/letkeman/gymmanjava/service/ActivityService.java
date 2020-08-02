package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.dao.ActivityRepository;
import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.models.ResourceFile;
import ca.letkeman.gymmanjava.service.interfaces.CrudWithFileService;
import ca.letkeman.gymmanjava.service.interfaces.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ActivityService implements CrudWithFileService<Activity> {

  private final ActivityRepository activityRepository;
  private final ResourceFileRepository resourceFileRepository;
  private final StorageService storage;


  public ActivityService(ActivityRepository activityRepository,
      ResourceFileRepository resourceFileRepository,
      StorageService storage) {
    this.activityRepository = activityRepository;
    this.resourceFileRepository = resourceFileRepository;
    this.storage = storage;
  }

  @Override
  public boolean delete(String payload) {
    return deleteActivities(payload);
  }

  @Override
  public Activity update(MultipartFile file,
      String payload) {
    try {
      return getActivity(file, payload);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Activity create(MultipartFile file,
      String payload) throws JsonProcessingException {
    return getActivity(file, payload);
  }

  @Override
  public Activity get(String id) {
    return activityRepository.findByuuid(id);
  }

  @Override
  public List<Activity> list() {
    return (List<Activity>) activityRepository.findAll();
  }

  private Activity getActivity(MultipartFile file, String payload) throws JsonProcessingException {

    Activity activity = new ObjectMapper().readValue(payload, new TypeReference<Activity>() {
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
      activity.setResourceFile(getResourceFile(file, activity.getResourceFile(), fileDescription));
      activity = activityRepository.save(activity);
    }
    return activity;
  }

  private ResourceFile getResourceFile(MultipartFile file, ResourceFile resourceFile,
      String fileDescription) {
    if (file == null) {
      return null;
    }

    ResourceFile returnedFile = new ResourceFile();
    storage.store(file);
    if ((resourceFile != null) && (resourceFile.getDescription() != null)) {
      fileDescription = resourceFile.getDescription();
    }

    returnedFile.setFileName(file.getOriginalFilename());
    returnedFile.setFileSize((int) file.getSize());
    returnedFile.setDescription(fileDescription);
    returnedFile.setDateTime(LocalDateTime.now());
    returnedFile = resourceFileRepository.save(returnedFile);
    return returnedFile;
  }

  private boolean deleteActivities(String payload) {
    if (!payload.isEmpty()) {
      List<Activity> activities;
      try {
        List<String> list = Arrays.asList(payload.replace("\"", "").split(","));
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
