package ca.letkeman.gymmanjava.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Integer id;
  private String uuid;
  private String name;
  private String description;

  @OneToOne
  @JsonInclude(Include.NON_NULL)  private ResourceFile resourceFile;

  public Activity() {
  }

  public Activity(Integer id, String uuid, String name, String description,
      ResourceFile resourceFile) {
    this.id = id;
    this.uuid = uuid;
    this.name = name;
    this.description = description;
    this.resourceFile = resourceFile;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUuid() {
    if (uuid == null){
      uuid = UUID.randomUUID().toString();
    }
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
        .append(name, activity.name)
        .append(description, activity.description)
        .append(resourceFile, activity.resourceFile)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
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
        .append("resourceFile", resourceFile)
        .toString();
  }
}
