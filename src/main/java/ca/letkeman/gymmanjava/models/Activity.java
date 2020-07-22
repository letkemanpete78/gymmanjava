package ca.letkeman.gymmanjava.models;

public class Activity {

  private String uuid;
  private String name;
  private String description;
  private ResourceFile resourceFile;

  public Activity() {
  }

  public Activity(String uuid, String name, String description,
      ResourceFile resourceFile) {
    this.uuid = uuid;
    this.name = name;
    this.description = description;
    this.resourceFile = resourceFile;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
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

  public ResourceFile getResourceFile() {
    return resourceFile;
  }

  public void setResourceFile(ResourceFile resourceFile) {
    this.resourceFile = resourceFile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Activity activity = (Activity) o;

    return new org.apache.commons.lang3.builder.EqualsBuilder()
        .append(uuid, activity.uuid)
        .append(name, activity.name)
        .append(description, activity.description)
        .append(resourceFile, activity.resourceFile)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
        .append(uuid)
        .append(name)
        .append(description)
        .append(resourceFile)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new org.apache.commons.lang3.builder.ToStringBuilder(this)
        .append("uuid", uuid)
        .append("name", name)
        .append("description", description)
        .append("resourseFile", resourceFile)
        .toString();
  }
}
