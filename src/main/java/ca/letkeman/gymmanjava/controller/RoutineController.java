package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.dao.RountineRepository;
import ca.letkeman.gymmanjava.models.Routine;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

@Transactional
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/routine")
public class RoutineController {


  public RoutineController(RountineRepository routineRepository) {
  }

  @DeleteMapping
  public boolean delete(@RequestBody String payload) {
    return true;
  }

  @PutMapping
  public Routine update(@RequestBody String payload) {
    return new Routine();
  }

  @PostMapping
  public Routine create(@RequestBody String payload) {
    return new Routine();
  }

  @GetMapping("/{id}")
  public Routine get(@PathVariable String id) {
    return new Routine();
  }

  @GetMapping("/list")
  public List<Routine> list() {
    return new ArrayList<>();
  }
}
