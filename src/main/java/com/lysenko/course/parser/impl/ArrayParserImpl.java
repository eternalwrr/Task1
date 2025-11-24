package com.lysenko.course.parser.impl;
import com.lysenko.course.parser.ArrayParser;
import java.util.Arrays;

public class ArrayParserImpl implements ArrayParser {
  private static final String REGEX_DELIMITERS = "[;,\\-]";
  private static final String REPLACEMENT_SPACE = " ";
  private static final String REGEX_WHITESPACE = "\\s+";

  @Override
  public int[] parse(String line) {
    String normalized = line.replaceAll(REGEX_DELIMITERS, REPLACEMENT_SPACE);
    int[] result = Arrays.stream(normalized.split(REGEX_WHITESPACE))
            .filter(s -> !s.isEmpty())
            .mapToInt(Integer::parseInt)
            .toArray();
    return result;
  }
}

