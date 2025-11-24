package com.lysenko.course.validator.impl;

import com.lysenko.course.validator.ArrayValidator;

public class ArrayValidatorImpl implements ArrayValidator {
  private static final String VALIDATE_PATTERN = "^[\\s\\d;,-]+$";

  @Override
  public boolean isValid(String line) {
    if (line == null || line.isBlank()) {
      return false;
    }
    return line.matches("[0-9; ,\\-]+");
  }
}

