package self.practice.interviewprep.atlassian.filecollections.model;

import java.util.ArrayList;
import java.util.List;

public class FileCollection {
  private final long id;
  private final String code;
  private final String name;
  private final String description;
  private Status status;

  private final List<String> fileCodes;

  public FileCollection(long id, String code, String name, String description, Status status) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.description = description;
    this.status = status;
    this.fileCodes = new ArrayList<>();
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public List<String> getFileCodes() {
    return fileCodes;
  }

  public void addFileToCollection(String fileCode) {
    fileCodes.add(fileCode);
  }
}
