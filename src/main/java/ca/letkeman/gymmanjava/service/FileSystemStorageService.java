package ca.letkeman.gymmanjava.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

  private Path rootLocation;
  private static String configPathalue = "";

  @Autowired
  public FileSystemStorageService(StorageProperties properties) throws Exception {
    if ((properties == null)) {
      throw new NullPointerException("cannot have null location value");
    }
    if (properties.getLocation() != null) {
      configPathalue = properties.getLocation();
    }
    this.rootLocation = Paths.get(configPathalue);
    init();
  }

  @Override
  public void store(MultipartFile file) {
    String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + filename);
      }
      if (filename.contains("..")) {
        // This is a security check
        throw new StorageException(
            "Cannot store file with relative path outside current directory "
                + filename);
      }
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, this.rootLocation.resolve(filename),
            StandardCopyOption.REPLACE_EXISTING);
      }
    } catch (IOException e) {
      throw new StorageException("Failed to store file " + filename, e);
    }
  }

  @Override
  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new StorageFileNotFoundException(
            "Could not read file: " + filename);

      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  @Override
  public void delete(String fileName) {
    Path file = load(fileName);
    FileSystemUtils.deleteRecursively(file.toFile());
  }

  @Override
  public void init() {
    try {
      if (!Files.exists(rootLocation)) {
        Files.createDirectories(rootLocation);
      }
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage", e);
    }
  }
}
