package ca.letkeman.gymmanjava.controller;

import ca.letkeman.gymmanjava.models.User;
import ca.letkeman.gymmanjava.service.UserService;
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
@RequestMapping("/user/v1")
public class UserController {

  @Autowired
  UserService userService;

  @DeleteMapping
  public boolean delete(@RequestBody String payload) {
    return userService.delete(payload);
  }

  @PutMapping
  public User update(@RequestBody String payload) throws JsonProcessingException {
    return userService.update(payload);
  }

  @PostMapping
  public User create(@RequestBody String payload) throws JsonProcessingException {
    return userService.create(payload);
  }

  @GetMapping("/{id}")
  public User get(@PathVariable String id) {
    return userService.get(id);
  }

  @GetMapping("/list")
  public List<User> list() {
    return userService.list();
  }
}
