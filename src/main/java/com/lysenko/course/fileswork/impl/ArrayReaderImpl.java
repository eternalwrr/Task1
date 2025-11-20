package com.lysenko.course.fileswork.impl;

import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.fileswork.ArrayReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArrayReaderImpl implements ArrayReader {
  private static final Logger logger = LogManager.getLogger(ArrayReaderImpl.class);

  @Override
  public List<String> readFromFile(String filePath) throws ArrayException {  // Изменено на readFromFile
    logger.info("Reading file: {}", filePath);

    try {
      Path path = Paths.get(filePath);

      if (!Files.exists(path)) {
        throw new ArrayException("File does not exist: " + filePath);
      }

      if (!Files.isReadable(path)) {
        throw new ArrayException("File is not readable: " + filePath);
      }

      List<String> lines = Files.readAllLines(path);
      logger.info("Successfully read {} lines from file: {}", lines.size(), filePath);
      return lines;

    } catch (IOException e) {
      throw new ArrayException("Error reading file: " + filePath, e);
    }
  }
}