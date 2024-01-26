package self.practice.interviewprep.atlassian.filecollections.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.practice.interviewprep.atlassian.filecollections.model.File;
import self.practice.interviewprep.atlassian.filecollections.model.Status;
import self.practice.interviewprep.atlassian.filecollections.repo.FileCollectionStore;

@Service
public class FileProcessorImpl implements FileProcessor {

  private final FileCollectionStore fileCollectionStore;
  private static final String UN_NAMED_COLLECTION = "UnNamedCollection";

  @Autowired
  public FileProcessorImpl(FileCollectionStore fileCollectionStore) {
    this.fileCollectionStore = fileCollectionStore;
  }

  @Override
  public void addFileToCollection(File file, String collectionCode) {
    if(file == null || collectionCode == null) throw new IllegalArgumentException("File and collection code cannot be null");
    if(collectionCode.isEmpty()) throw new IllegalArgumentException("Collection code cannot be empty" );
    if(collectionCode.equalsIgnoreCase(UN_NAMED_COLLECTION)) throw new IllegalArgumentException("Collection code cannot be UnNamedCollection. UnNamedCollection is reserved for files that are not part of any collection");
    this.fileCollectionStore.addFileToCollection(file, collectionCode);
  }

  @Override
  public void addFile(File file) {
    if(file == null) throw new IllegalArgumentException("File cannot be null");
    this.fileCollectionStore.addFileToCollection(file, UN_NAMED_COLLECTION);
  }

  @Override
  public void deleteFile(String fileCode) {
    if(fileCode == null) throw new IllegalArgumentException("File code cannot be null");
    this.fileCollectionStore.deleteFile(fileCode);
  }

  @Override
  public void updateFileStatus(String fileCode, Status status) {
    if(fileCode == null || status == null) throw new IllegalArgumentException("File code and status cannot be null");
    this.fileCollectionStore.updateFileStatus(fileCode, status);
  }

  @Override
  public File getFile(String fileCode) {
    return this.fileCollectionStore.getFile(fileCode);
  }

  @Override
  public long getFileSize(String fileCode) {
    return this.fileCollectionStore.getFile(fileCode).getSize();
  }
}
