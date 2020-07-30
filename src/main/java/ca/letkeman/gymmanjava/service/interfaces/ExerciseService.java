package ca.letkeman.gymmanjava.service.interfaces;

import ca.letkeman.gymmanjava.models.Exercise;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ExerciseService {

  boolean delete( String payload);

  Exercise update( String payload);

  Exercise create( String payload);

  Exercise get( String id);

  List<Exercise> list();
}
