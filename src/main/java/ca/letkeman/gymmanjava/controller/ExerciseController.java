package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.dao.ExerciseRepository;
import ca.letkeman.gymmanjava.models.Exercise;
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
@RequestMapping("/exercise")
public class ExerciseController {


  public ExerciseController(ExerciseRepository exerciseRepository) {
  }

  @DeleteMapping
  public  boolean delete(@RequestBody String payload){
    return true;
  }

  @PutMapping
  public Exercise update(@RequestBody String payload){
    return new Exercise();
  }

  @PostMapping
  public Exercise create(@RequestBody String payload){
    return new Exercise();
  }

  @GetMapping("/{id}")
  public Exercise get(@PathVariable String id){
    return new Exercise();
  }

  @GetMapping("/list")
  public List<Exercise> list (){
    return new ArrayList<>();
  }
}
