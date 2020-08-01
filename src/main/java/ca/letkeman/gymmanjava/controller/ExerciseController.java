package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.service.ExerciseService;
import ca.letkeman.gymmanjava.service.interfaces.CrudService;
import ca.letkeman.gymmanjava.service.interfaces.subInterfaces.DeleteGetList;
import ca.letkeman.gymmanjava.service.interfaces.subInterfaces.UpdateCreate;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class ExerciseController  {

  @Autowired
  ExerciseService exerciseService;

  @DeleteMapping
  public  boolean delete(@RequestBody String payload){
    return exerciseService.delete(payload);
  }

  @PutMapping
  public Exercise update(@RequestBody String payload){
    return (Exercise) exerciseService.update( payload);  }

  @PostMapping
  public Exercise create(@RequestBody String payload)
      throws JsonProcessingException {
    return (Exercise) exerciseService.create(payload);  }

  @GetMapping("/{id}")
  public Exercise get(@PathVariable String id){
    return (Exercise) exerciseService.get(id);
  }

  @GetMapping("/list")
  public List<Exercise> list(){
    return exerciseService.list();
  }
}
