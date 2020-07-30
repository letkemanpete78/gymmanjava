package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.models.Activity;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ActivityService {

  boolean delete(String payload);

  Activity update(MultipartFile file, String payload);

  Activity create(MultipartFile file, String payload) throws JsonProcessingException;

  Activity get(String id);

  List<Activity> list();
}
