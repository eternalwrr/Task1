package com.lysenko.course.reader.impl;

import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.reader.ArrayReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArrayReaderImpl implements ArrayReader {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public List<String> readFromFile(String filePath) throws ArrayException {
    logger.info("Reading file: {}", filePath);

    try {
      Path path = Paths.get(filePath);

      if (!Files.exists(path)) {
        logger.error("File does not exist: {}", filePath);
        throw new ArrayException("File does not exist: " + filePath);
      }

      if (!Files.isReadable(path)) {
        logger.error("File is not readable: {}", filePath);
        throw new ArrayException("File is not readable: " + filePath);
      }

      List<String> lines = Files.readAllLines(path);
      logger.info("Successfully read {} lines from file: {}", lines.size(), filePath);
      return lines;

    } catch (IOException e) {
      logger.error("Error reading file: {}", filePath, e);
      throw new ArrayException("Error reading file: " + filePath, e);
    }
  }
}