package self.practice.interviewprep.atlassian.filecollections.repo;

import java.util.List;
import self.practice.interviewprep.atlassian.filecollections.model.File;
import self.practice.interviewprep.atlassian.filecollections.model.FileCollection;
import self.practice.interviewprep.atlassian.filecollections.model.Status;

public interface FileCollectionStore {

  public void createFileCollection(FileCollection fileCollection);
  public void addFileToCollection(File file, String collectionCode);
  public void deleteFileCollection(String collectionCode);
  public void deleteFile(String fileCode);

  public void deleteFile(String fileCode, String collectionCode);
  public void updateFileCollectionStatus(String collectionCode, Status status);
  public void updateFileStatus(String fileCode, Status status);
  public List<File> getFilesByCollection(String collectionCode);
  public FileCollection getFileCollection(String collectionCode);
  public File getFile(String fileCode);
  public List<FileCollection> getFileCollectionByFile(String fileCode);
  public long getNumberOfFilesInCollection(String collectionCode);
  public List<FileCollection> getAllFileCollections();
}
