package ca.letkeman.gymmanjava.service;

import static org.mockito.Mockito.when;

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

  private StorageProperties storageProperties;
  private FileSystemStorageService fileSystemStorageService;

  @Autowired
  StorageService storage;

  @BeforeEach
  public void initValues() throws Exception {
    storageProperties = new StorageProperties();
    fileSystemStorageService = new FileSystemStorageService(storageProperties);
    fileSystemStorageService.init();
  }

  @Test
  void shouldThrowStoreException() {
    Assertions.assertThrows(StorageException.class, () -> {
      MultipartFile multipartFile = Mockito.mock((MultipartFile.class));
      when(multipartFile.getOriginalFilename()).thenReturn("..test");
      fileSystemStorageService.store(multipartFile);
    });
    Assertions.assertThrows(StorageException.class, () -> {
      MockMultipartFile mockMultipartFile = new MockMultipartFile("test.txt", (byte[]) null);
      fileSystemStorageService.store(mockMultipartFile);
    });
  }

  @Test
  void load() throws IOException {
    String expected = "loadAsResoure-testFile.txt";
    createSampleFile("test-upload-dir/" + expected);
    Path path = fileSystemStorageService.load(expected);
    Assertions.assertEquals("test-upload-dir/" + expected, path.toString());
  }

  @Test
  void loadAsResource() throws IOException {
    Assertions.assertThrows(StorageFileNotFoundException.class, () -> {
      fileSystemStorageService.loadAsResource("test.txt");
    });
    String expected = "test-upload-dir/loadAsResoure-testFile.txt";
    createSampleFile(expected);
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(expected);

    Resource loadAsResource = fileSystemStorageService.loadAsResource(file.getAbsolutePath());
    Assertions.assertEquals(loadAsResource.getFile().getAbsoluteFile(), file.getAbsoluteFile());
  }

  @Test
  void deleteAll() throws IOException {
    Path path = fileSystemStorageService.load("loadAsResoure-testFile.txt").toAbsolutePath();
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
  void delete() throws IOException {
    Path path = fileSystemStorageService.load("loadAsResoure-testFile.txt").toAbsolutePath();
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
