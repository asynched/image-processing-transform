package com.unip.pdi.fs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import com.unip.pdi.functional.Result;

public class FileSystem {
  public static Result<Byte[], IOException> read(String filename) {
    return Result.safeFunction(() -> {
      var path = Path.of(filename);
      var bytes = Files.readAllBytes(path);

      var result = new Byte[bytes.length];

      for (int i = 0; i < bytes.length; i++) {
        result[i] = bytes[i];
      }

      return result;
    });
  }

  public static Result<Boolean, IOException> write(String filename, byte[] bytes) {
    return Result.safeFunction(() -> {
      var path = Path.of(filename);
      Files.write(path, bytes);
      return true;
    });
  }

  public static Result<Boolean, IOException> writeImage(String filename, BufferedImage image) {
    return Result.safeFunction(() -> {
      var path = Path.of(filename);
      var extension = filename.substring(filename.lastIndexOf('.') + 1);

      return ImageIO.write(image, extension, path.toFile());
    });
  }

  public static Result<BufferedImage, IOException> readImage(String filename) {
    return Result.safeFunction(() -> {
      var path = Path.of(filename);
      return ImageIO.read(path.toFile());
    });
  }
}