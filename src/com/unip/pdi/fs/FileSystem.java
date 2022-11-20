package com.unip.pdi.fs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import com.unip.pdi.functional.Result;

public class FileSystem {
  /**
   * Reads a file from the file system.
   * 
   * @param filename The name of the file to be read.
   * @return A result containing the file contents as a byte array.
   */
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

  /**
   * Writes a file to the file system.
   * 
   * @param filename The name of the file to be written.
   * @param bytes    The contents of the file.
   * @return A result containing an error in case the operation fails.
   */
  public static Result<Boolean, IOException> write(String filename, byte[] bytes) {
    return Result.safeFunction(() -> {
      var path = Path.of(filename);
      Files.write(path, bytes);
      return true;
    });
  }

  /**
   * Writes a file to the file system.
   * 
   * @param filename The name of the file to be written.
   * @param image    The image to be written.
   * @return A result containing an error in case the operation fails.
   */
  public static Result<Boolean, IOException> writeImage(String filename, BufferedImage image) {
    return Result.safeFunction(() -> {
      var file = new File(filename);
      var extension = filename.substring(filename.lastIndexOf('.') + 1);

      return ImageIO.write(image, extension, file);
    });
  }

  /**
   * Reads an image from the file system.
   * 
   * @param filename The name of the file to be read.
   * @return A result containing the image.
   */
  public static Result<BufferedImage, IOException> readImage(String filename) {
    return Result.safeFunction(() -> {
      var path = Path.of(filename);
      return ImageIO.read(path.toFile());
    });
  }
}