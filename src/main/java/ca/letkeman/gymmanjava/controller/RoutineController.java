package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.dao.RoutineRepository;
import ca.letkeman.gymmanjava.models.Routine;
import ca.letkeman.gymmanjava.service.RoutineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/routine/v1")
public class RoutineController {

  @Autowired
  RoutineService routineService;

  @DeleteMapping
  public boolean delete(@RequestBody String payload) {
    return routineService.delete(payload);
  }

  @PutMapping
  public Routine update(@RequestBody String payload) throws JsonProcessingException {
    return routineService.update(payload);
  }

  @PostMapping
  public Routine create(@RequestBody String payload) throws JsonProcessingException {
    return routineService.create(payload);
  }

  @GetMapping("/{id}")
  public Routine get(@PathVariable String id) {
    return routineService.get(id);
  }

  @GetMapping("/list")
  public List<Routine> list() {
    return routineService.list();
  }
}
