package self.practice.interviewprep.atlassian.filecollections.service;

import java.util.List;
import self.practice.interviewprep.atlassian.filecollections.model.FileCollection;
import self.practice.interviewprep.atlassian.filecollections.model.Status;

public interface FileCollectionProcessor {
  void createFileCollection(FileCollection fileCollection);
  FileCollection getFileCollection(String collectionCode);
  void deleteFileCollection(String collectionCode);
  void updateFileCollectionStatus(String collectionCode, Status status);
  long getNumberOfFilesInCollection(String collectionCode);
  long getFileCollectionSize(String collectionCode);
  List<FileCollection> getSortedFileCollectionsByFileSize(int limit);
}
