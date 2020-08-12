package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.service.interfaces.CrudWithFileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping("/activity/v1")
public class ActivityController {

  @Autowired
  private CrudWithFileService<Activity> crudService;

  @DeleteMapping
  public boolean delete(@RequestBody String payload) {
    return crudService.delete(payload);
  }

  @PutMapping
  public Activity update(@RequestParam("file") MultipartFile file,
      @RequestParam("payload") String payload) {
    return crudService.update(file, payload);
  }

  @PostMapping
  public Activity create(@RequestParam("file") MultipartFile file,
      @RequestParam("payload") String payload) throws JsonProcessingException {
    return crudService.create(file, payload);
  }

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = MediaType.ALL_VALUE)
  public Activity get(@PathVariable String id) {
    return crudService.get(id);
  }

  @GetMapping(value = "/",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = MediaType.ALL_VALUE)
  public List<Activity> list() {
    return crudService.list();
  }
}
