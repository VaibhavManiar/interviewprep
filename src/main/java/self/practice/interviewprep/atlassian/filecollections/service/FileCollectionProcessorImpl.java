package self.practice.interviewprep.atlassian.filecollections.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.practice.interviewprep.atlassian.filecollections.model.File;
import self.practice.interviewprep.atlassian.filecollections.model.FileCollection;
import self.practice.interviewprep.atlassian.filecollections.model.Status;
import self.practice.interviewprep.atlassian.filecollections.repo.FileCollectionStore;

@Service
public class FileCollectionProcessorImpl implements FileCollectionProcessor {

  private final FileCollectionStore fileCollectionStore;

  @Autowired
  public FileCollectionProcessorImpl(FileCollectionStore fileCollectionStore) {
    this.fileCollectionStore = fileCollectionStore;
  }

  @Override
  public void createFileCollection(FileCollection fileCollection) {
    if (fileCollection == null) {
      throw new IllegalArgumentException("FileCollection cannot be null");
    }
    fileCollectionStore.createFileCollection(fileCollection);
  }

  @Override
  public FileCollection getFileCollection(String collectionCode) {
    return this.fileCollectionStore.getFileCollection(collectionCode);
  }

  @Override
  public void deleteFileCollection(String collectionCode) {
    this.fileCollectionStore.deleteFileCollection(collectionCode);
  }

  @Override
  public void updateFileCollectionStatus(String collectionCode, Status status) {
    this.fileCollectionStore.updateFileCollectionStatus(collectionCode, status);
  }

  @Override
  public long getNumberOfFilesInCollection(String collectionCode) {
    return this.fileCollectionStore.getNumberOfFilesInCollection(collectionCode);
  }

  @Override
  public long getFileCollectionSize(String collectionCode) {
    return this.fileCollectionStore.getFilesByCollection(collectionCode).stream()
        .mapToLong(File::getSize).sum();
  }

  @Override
  public List<FileCollection> getSortedFileCollectionsByFileSize(int limit) {
    if(limit <= 0) throw new IllegalArgumentException("Limit cannot be negative or zero");
    class FileCollectionWithSize {
      private final FileCollection fileCollection;
      private final long totalSize;

      public FileCollectionWithSize(FileCollection fileCollection, long totalSize) {
        this.fileCollection = fileCollection;
        this.totalSize = totalSize;
      }

      public FileCollection getFileCollection() {
        return this.fileCollection;
      }

      public long getTotalSize() {
        return this.totalSize;
      }
    }
    List<FileCollectionWithSize> fileCollectionWithSize = this.fileCollectionStore.getAllFileCollections()
        .stream().map(fileCollection ->
            new FileCollectionWithSize(fileCollection,
                this.getFileCollectionSize(fileCollection.getCode()))
        ).sorted((fileCollectionWithSize1, fileCollectionWithSize2) ->
            (int) (fileCollectionWithSize1.getTotalSize() - fileCollectionWithSize2.getTotalSize())
        ).toList();
    return fileCollectionWithSize.stream().map(FileCollectionWithSize::getFileCollection).limit(limit)
        .collect(Collectors.toList());
  }
}