package ca.letkeman.gymmanjava.models;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "routine", indexes = {@Index(columnList = "uuid", unique = true)})
public class Routine {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Integer id;
  private String uuid;

  private String name;

  private String description;
  @ManyToMany
  private List<Exercise> exercises;

  public Routine() {
  }

  public Routine(Integer id, String name, String description,
      List<Exercise> exercises, String uuid) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.exercises = exercises;
    this.uuid = uuid;
  }


  public String getUuid() {
    if (uuid == null) {
      uuid = UUID.randomUUID().toString();
    }
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Exercise> getExercises() {
    return exercises;
  }

  public void setExercises(List<Exercise> exercises) {
    this.exercises = exercises;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Routine routine = (Routine) o;

    return new EqualsBuilder()
        .append(id, routine.id)
        .append(uuid, routine.uuid)
        .append(name, routine.name)
        .append(description, routine.description)
        .append(exercises, routine.exercises)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(uuid)
        .append(name)
        .append(description)
        .append(exercises)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("uuid", uuid)
        .append("name", name)
        .append("description", description)
        .append("exercises", exercises)
        .toString();
  }
}
