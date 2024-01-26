package self.practice.interviewprep.atlassian.filecollections.model;

public class File {
  private final long id;
  private final String code;
  private final String name;
  private final String description;
  private final byte[] content;
  private Status status;

  public File(long id, String code, String name, String description, byte[] content, Status status) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.description = description;
    this.content = content;
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public byte[] getContent() {
    return content;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public long getSize() {
    return content.length;
  }
}
