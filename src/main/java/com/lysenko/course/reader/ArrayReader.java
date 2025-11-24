package com.lysenko.course.reader;
import com.lysenko.course.exception.ArrayException;
import java.util.List;

public interface ArrayReader {
  List<String> readFromFile(String filePath) throws ArrayException;
}