package ca.letkeman.gymmanjava.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

public class ResourceFile {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(unique =  true, nullable = false)
  private Integer fileId;
  private String fileName;
  private LocalDateTime dateTime;
  private long fileSize;
  private String description;

  public ResourceFile() {
  }

  public ResourceFile(int fileId, String fileName, LocalDateTime dateTime, int fileSize,
      String description) {
    this.fileId = fileId;
    this.fileName = fileName;
    this.dateTime = dateTime;
    this.fileSize = fileSize;
    this.description = description;
  }

  public Integer getFileId() {
    return fileId;
  }

  public void setFileId(Integer fileId) {
    this.fileId = fileId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ResourceFile that = (ResourceFile) o;

    return new EqualsBuilder()
        .append(fileId, that.fileId)
        .append(fileSize, that.fileSize)
        .append(fileName, that.fileName)
        .append(dateTime, that.dateTime)
        .append(description, that.description)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(fileId)
        .append(fileName)
        .append(dateTime)
        .append(fileSize)
        .append(description)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("fileId", fileId)
        .append("fileName", fileName)
        .append("dateTime", dateTime)
        .append("fileSize", fileSize)
        .append("description", description)
        .toString();
  }


}
