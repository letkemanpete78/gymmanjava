package ca.letkeman.gymmanjava.service;

import static org.mockito.Mockito.when;

import ca.letkeman.gymmanjava.service.interfaces.StorageService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

@ActiveProfiles("test")
@SpringBootTest
class FileSystemStorageServiceTest {

  public static final String TEST_UPLOAD_DIR = "test-upload-dir/";
  public static final String RESOURCE_TEST_FILE_TXT = "loadAsResoure-testFile.txt";
  private StorageProperties storageProperties;
  private FileSystemStorageService fileSystemStorageService;

  @Autowired
  StorageService storage;

  @BeforeEach
  public void initValues() {
    storageProperties = new StorageProperties();
    fileSystemStorageService = new FileSystemStorageService(storageProperties);
    fileSystemStorageService.init();
  }

  @Test
  void shouldThrowStoreException() {
    final MultipartFile multipartFile = Mockito.mock((MultipartFile.class));
    when(multipartFile.getOriginalFilename()).thenReturn("..test");
    Assertions.assertThrows(StorageException.class, () -> fileSystemStorageService.store(multipartFile));
    MockMultipartFile mockMultipartFile1 = new MockMultipartFile("test.txt", (byte[]) null);
    Assertions.assertThrows(StorageException.class, () -> fileSystemStorageService.store(mockMultipartFile1));
    final MultipartFile multipartFile2 = new MockMultipartFile("test.txt", "abc".getBytes());
    Assertions.assertThrows(StorageException.class, () -> fileSystemStorageService.store(multipartFile2));
  }

  @Test
  void shouldLoadFile() throws IOException {
    createSampleFile(TEST_UPLOAD_DIR + RESOURCE_TEST_FILE_TXT);
    Path path = fileSystemStorageService.load(RESOURCE_TEST_FILE_TXT);
    Assertions.assertEquals(TEST_UPLOAD_DIR + RESOURCE_TEST_FILE_TXT, path.toString());
  }

  @Test
  void shouldLoadResource() throws IOException {
    Assertions.assertThrows(StorageFileNotFoundException.class, () -> fileSystemStorageService.loadAsResource("test.txt"));
    String filePath = TEST_UPLOAD_DIR + RESOURCE_TEST_FILE_TXT;
    createSampleFile(filePath);
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(filePath);

    Resource loadAsResource = fileSystemStorageService.loadAsResource(file.getAbsolutePath());
    Assertions.assertEquals(loadAsResource.getFile().getAbsoluteFile(), file.getAbsoluteFile());
  }

  @Test
  void shouldDeleteFiles() throws IOException {
    Path path = fileSystemStorageService.load(RESOURCE_TEST_FILE_TXT).toAbsolutePath();
    createSampleFile(path.toString());
    if (Files.exists(path)) {
      List<Path> oldList = Files.list(path.getParent()).collect(Collectors.toList());
      if (!oldList.isEmpty()) {
        fileSystemStorageService.deleteAll();
        Assertions.assertFalse((Files.exists(path.toAbsolutePath())));
      }
    }
  }

  @Test
  void shouldDeleteFile() throws IOException {
    Path path = fileSystemStorageService.load(RESOURCE_TEST_FILE_TXT).toAbsolutePath();
    createSampleFile(path.toString());
    if (Files.exists(path)) {
      List<Path> oldList = Files.list(path.getParent()).collect(Collectors.toList());
      if (!oldList.isEmpty()) {
        fileSystemStorageService.delete(path.toString());
        Assertions.assertFalse((Files.exists(path.toAbsolutePath())));
      }
    }
  }

  private void createSampleFile(String path) throws IOException {
    String str = "sample content";
    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    writer.write(str);
    writer.close();
  }
}
