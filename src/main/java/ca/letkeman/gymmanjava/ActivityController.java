package ca.letkeman.gymmanjava;

import ca.letkeman.gymmanjava.dao.ActivityRepository;
import ca.letkeman.gymmanjava.dao.ResourceFileRepository;
import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.service.Storage;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Transactional
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/activity")
public class ActivityController {
  private final ActivityRepository activityRepository;
  private final ResourceFileRepository resourceFileRepository;
  private final Storage storage;

  public ActivityController(ActivityRepository activityRepository,
      ResourceFileRepository resourceFileRepository, Storage storage) {
    this.activityRepository = activityRepository;
    this.resourceFileRepository = resourceFileRepository;
    this.storage = storage;
  }

  @DeleteMapping
  public boolean deleteActivity(@RequestBody String payload){
    return true;
  }

  @PutMapping
  public Activity updateActivity(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {

    storage.store(file);

    return new Activity();
  }

  @PostMapping
  public Activity createActivity(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) {

    storage.store(file);

    return new Activity();
  }

  @GetMapping("/{id}")
  public Activity getActivety(@PathVariable String id){
    return new Activity();
  }

  @GetMapping("/list")
  public List<Activity> list (){
    return new ArrayList<Activity>();
  }
}
