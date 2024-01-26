package self.practice.interviewprep.atlassian.filecollections.service;

import java.util.List;
import self.practice.interviewprep.atlassian.filecollections.model.File;
import self.practice.interviewprep.atlassian.filecollections.model.Status;

public interface FileProcessor {
  void addFileToCollection(File file, String collectionCode);
  void addFile(File file);
  void deleteFile(String fileCode);
  void updateFileStatus(String fileCode, Status status);
  File getFile(String fileCode);
  long getFileSize(String fileCode);
}
