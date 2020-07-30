package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.service.interfaces.ExerciseService;
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
@RequestMapping("/exercise/v1")
public class ExerciseController implements ExerciseService {

  @Autowired
  ExerciseService exerciseService;

  @Override
  @DeleteMapping
  public  boolean delete(@RequestBody String payload){
    return exerciseService.delete(payload);
  }

  @Override
  @PutMapping
  public Exercise update(@RequestBody String payload){
    return exerciseService.update(payload);
  }

  @Override
  @PostMapping
  public Exercise create(@RequestBody String payload){
    return exerciseService.create(payload);  }

  @Override
  @GetMapping("/{id}")
  public Exercise get(@PathVariable String id){
    return exerciseService.get(id);
  }

  @Override
  @GetMapping("/list")
  public List<Exercise> list(){
    return exerciseService.list();
  }
}
