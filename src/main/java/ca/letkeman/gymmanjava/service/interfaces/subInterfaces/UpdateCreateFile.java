package ca.letkeman.gymmanjava.service.interfaces.subInterfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateCreateFile<T> {

  T update(MultipartFile file, String payload);

  T create(MultipartFile file, String payload) throws JsonProcessingException;
}
