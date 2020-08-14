package ca.letkeman.gymmanjava.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "exercise", indexes = {@Index(columnList = "uuid", unique = true)})
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Integer id;
  private Type type;
  private int value;
  private String unit;
  @Column(unique = true)
  private String uuid;
  private String name;
  private String description;
  @OneToOne
  @JsonInclude(Include.NON_NULL)
  private Activity activity;

  public Exercise() {
  }

  public Exercise(Integer id, Type type, int value, String unit, String uuid, String name,
      String description,
      Activity activity) {
    this.id = id;
    this.type = type;
    this.value = value;
    this.unit = unit;
    this.uuid = uuid;
    this.name = name;
    this.description = description;
    this.activity = activity;
  }

  public String getUuid() {
    if (uuid == null) {
      uuid = UUID.randomUUID().toString();
    }
    return uuid;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
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

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
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

  public Activity getActivity() {
    return activity;
  }

  public void setActivity(Activity activity) {
    this.activity = activity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Exercise exercise = (Exercise) o;

    return new EqualsBuilder()
        .append(value, exercise.value)
        .append(type, exercise.type)
        .append(unit, exercise.unit)
        .append(name, exercise.name)
        .append(description, exercise.description)
        .append(activity, exercise.activity)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(type)
        .append(unit)
        .append(value)
        .append(name)
        .append(description)
        .append(activity)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("uuid", uuid)
        .append("type", type)
        .append("unit", unit)
        .append("value", value)
        .append("name", name)
        .append("description", description)
        .append("activity", activity)
        .toString();
  }
}
