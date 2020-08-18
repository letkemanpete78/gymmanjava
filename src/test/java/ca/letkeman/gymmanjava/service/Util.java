package ca.letkeman.gymmanjava.service;

import ca.letkeman.gymmanjava.models.Activity;
import ca.letkeman.gymmanjava.models.Exercise;
import ca.letkeman.gymmanjava.models.ResourceFile;
import ca.letkeman.gymmanjava.models.Routine;
import ca.letkeman.gymmanjava.models.Type;
import java.time.LocalDateTime;
import java.util.List;

public class Util {

  public static Activity setActivityValues(int i) {
    ResourceFile resourceFile = new ResourceFile();
    resourceFile.setDescription(i + " test description");
    resourceFile.setFileName(i + " test-file.jpg");
    resourceFile.setFileSize(123 * i);
    resourceFile.setDateTime(LocalDateTime.now());
    resourceFile.setFileId(i);

    Activity activity = new Activity();
    activity.setDescription(i + " activity test description");
    activity.setName(i + " activity test name");
    activity.setResourceFile(resourceFile);
    activity.setUuid(i + " activity test-uuid");
    activity.setId(i);
    return activity;
  }

  public static Routine setRoutineValues(int j, List<Exercise> exercises) {
    Routine routine = new Routine();
    routine.setExercises(exercises);
    routine.setDescription(j + " routine test description");
    routine.setId(j);
    routine.setName(j + " routine test name");
    routine.setUuid(j + " routine test-uuid");
    return routine;
  }

  public static Exercise setExerciseValues(int i, Activity activity) {
    Exercise exercise = new Exercise();
    exercise.setActivity(activity);
    exercise.setUuid(i + " exercise test-uuid");
    exercise.setDescription(i + " exercise test description");
    exercise.setId(i);
    exercise.setName(i + " exercise name");
    exercise.setType(Type.TIMED);
    exercise.setValue(10);
    exercise.setUnit("seconds");
    return exercise;
  }
}
