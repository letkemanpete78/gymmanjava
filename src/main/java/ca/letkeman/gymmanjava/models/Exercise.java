package ca.letkeman.gymmanjava.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Exercise {
  private Integer id;
  private Type type;
  private int value;
  private String name;
  private String description;
  private Activity activity;

  public Exercise() {
  }

  public Exercise(Integer id, Type type, int value, String name, String description,
      Activity activity) {
    this.id = id;
    this.type = type;
    this.value = value;
    this.name = name;
    this.description = description;
    this.activity = activity;
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
        .append(name, exercise.name)
        .append(description, exercise.description)
        .append(activity, exercise.activity)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(type)
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
        .append("type", type)
        .append("value", value)
        .append("name", name)
        .append("description", description)
        .append("activity", activity)
        .toString();
  }
}
