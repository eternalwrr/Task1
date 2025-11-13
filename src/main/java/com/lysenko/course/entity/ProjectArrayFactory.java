package com.lysenko.course.entity;

import com.lysenko.course.exception.ArrayException;

public class ProjectArrayFactory {

  private ProjectArrayFactory() {
  }

  public static ProjectArray createArray(long arrayId, int[] digits) throws ArrayException {
    return new ProjectArray(arrayId, digits);
  }
}