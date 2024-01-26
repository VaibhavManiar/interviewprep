package self.practice.interviewprep.atlassian.filecollections.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import self.practice.interviewprep.atlassian.filecollections.model.File;
import self.practice.interviewprep.atlassian.filecollections.model.FileCollection;
import self.practice.interviewprep.atlassian.filecollections.model.Status;

/**
 * Summary
 * The FileCollectionInMemoryMapStore class is an implementation of the FileCollectionStore interface. It provides methods to create, update, and delete file collections and files in an in-memory map store.
 * Example Usage
 * FileCollectionInMemoryMapStore store = new FileCollectionInMemoryMapStore();
 *
 * // Create a new file collection
 * FileCollection collection = new FileCollection(1, "COL001", "Collection 1", "First collection", Status.ACTIVE);
 * store.createFileCollection(collection);
 *
 * // Add a file to the collection
 * File file = new File(1, "FILE001", "File 1", "First file", new byte[0], Status.ACTIVE);
 * store.addFileToCollection(file, "COL001");
 *
 * // Update the status of the file collection
 * store.updateFileCollectionStatus("COL001", Status.INACTIVE);
 *
 * <p>
 * // Get all file collections
 * List<FileCollection> collections = store.getAllFileCollections();
 * Code Analysis
 * Main functionalities
 * The main functionalities of the FileCollectionInMemoryMapStore class are:
 * Creating and managing file collections and files in an in-memory map store.
 * Adding files to a collection and updating their status.
 * Deleting file collections and files.
 * Retrieving files and collections based on their codes.
 * </p>
 *
 * <p>
 * Methods: <br>
 * createFileCollection(FileCollection fileCollection): Creates a new file collection and adds it to the in-memory map store. <br>
 * addFileToCollection(File file, String collectionCode): Adds a file to a file collection in the in-memory map store. <br>
 * deleteFileCollection(String collectionCode): Deletes a file collection from the in-memory map store. <br>
 * deleteFile(String fileCode): Deletes a file from all file collections in the in-memory map store. <br>
 * deleteFile(String fileCode, String collectionCode): Deletes a file from a specific file collection in the in-memory map store. <br>
 * updateFileCollectionStatus(String collectionCode, Status status): Updates the status of a file collection in the in-memory map store. <br>
 * updateFileStatus(String fileCode, Status status): Updates the status of a file in the in-memory map store. <br>
 * getFilesByCollection(String collectionCode): Retrieves all files in a file collection from the in-memory map store. <br>
 * getFileCollection(String collectionCode): Retrieves a file collection from the in-memory map store. <br>
 * getFile(String fileCode): Retrieves a file from the in-memory map store. <br>
 * getFileCollectionByFile(String fileCode): Retrieves all file collections that contain a specific file from the in-memory map store. <br>
 * getNumberOfFilesInCollection(String collectionCode): Retrieves the number of files in a file collection from the in-memory map store. <br>
 * getAllFileCollections(): Retrieves all file collections from the in-memory map store. <br>
 *</p>
 * <br>
 * <p>
 * Fields:
 * fileCollections: A map that stores file collections, with the collection code as the key and the FileCollection object as the value. <br>
 * files: A map that stores files, with the file code as the key and the File object as the value.
 * </p>
 */
@Component
public class FileCollectionInMemoryMapStore implements FileCollectionStore {

  private final Map<String, FileCollection> fileCollections;
  private final Map<String, File> files;

  @Autowired
  public FileCollectionInMemoryMapStore() {
    this.fileCollections = new HashMap<>();
    this.files = new HashMap<>();
  }

  @Override
  public void createFileCollection(FileCollection fileCollection) {
    if (fileCollection == null) {
      throw new IllegalArgumentException("File collection cannot be null");
    }
    this.fileCollections.put(fileCollection.getCode(), fileCollection);
  }

  @Override
  public void addFileToCollection(File file, String collectionCode) {
    FileCollection fileCollection = this.fileCollections.get(collectionCode);
    if (fileCollection == null) {
      throw new IllegalArgumentException("File collection does not exist");
    }
    files.put(file.getCode(), file);
    fileCollection.addFileToCollection(file.getCode());
  }

  @Override
  public void deleteFileCollection(String collectionCode) {
    FileCollection fileCollection = this.fileCollections.get(collectionCode);
    if (fileCollection == null) {
      throw new IllegalArgumentException("File collection does not exist");
    }
    fileCollection.setStatus(Status.DELETED);
  }

  @Override
  public void deleteFile(String fileCode) {
    this.fileCollections.forEach((fileCollectionCode, fileCollection) -> fileCollection.getFileCodes().removeIf(code -> code.equals(fileCode)));
  }

  @Override
  public void deleteFile(String fileCode, String collectionCode) {
    FileCollection fileCollection = this.fileCollections.get(collectionCode);
    if (fileCollection == null) {
      throw new IllegalArgumentException("File collection does not exist");
    }
    fileCollection.getFileCodes().removeIf(code -> code.equals(fileCode));
  }

  @Override
  public void updateFileCollectionStatus(String collectionCode, Status status) {
    FileCollection fileCollection = this.fileCollections.get(collectionCode);
    if (fileCollection == null) {
      throw new IllegalArgumentException("File collection does not exist");
    }
    fileCollection.setStatus(status);
  }

  @Override
  public void updateFileStatus(String fileCode, Status status) {
    File file = this.files.get(fileCode);
    if (file == null) {
      throw new IllegalArgumentException("File does not exist");
    }
    file.setStatus(status);
  }

  @Override
  public List<File> getFilesByCollection(String collectionCode) {
    FileCollection fileCollection = this.fileCollections.get(collectionCode);
    if (fileCollection == null) {
      throw new IllegalArgumentException("File collection does not exist");
    }
    return fileCollection.getFileCodes().stream().map(this.files::get).toList();
  }

  @Override
  public FileCollection getFileCollection(String collectionCode) {
    FileCollection fileCollection = this.fileCollections.get(collectionCode);
    if (fileCollection == null) {
      throw new IllegalArgumentException("File collection does not exist");
    }
    return fileCollection;
  }

  @Override
  public File getFile(String fileCode) {
    File file = this.files.get(fileCode);
    if (file == null) {
      throw new IllegalArgumentException("File does not exist");
    }
    return file;
  }

  @Override
  public List<FileCollection> getFileCollectionByFile(String fileCode) {
    List<FileCollection> collections = new ArrayList<>();
    this.fileCollections.forEach((fileCollectionCode, fileCollection) -> {
      if (fileCollection.getFileCodes().contains(fileCode)) {
        collections.add(fileCollection);
      }
    });
    return collections;
  }

  @Override
  public long getNumberOfFilesInCollection(String collectionCode) {
    FileCollection fileCollection = this.fileCollections.get(collectionCode);
    if (fileCollection == null) {
      throw new IllegalArgumentException("File collection does not exist");
    }
    return fileCollection.getFileCodes().size();
  }

  @Override
  public List<FileCollection> getAllFileCollections() {
    return new ArrayList<>(this.fileCollections.values());
  }
}
