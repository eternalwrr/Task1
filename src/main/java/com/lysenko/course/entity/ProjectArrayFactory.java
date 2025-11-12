package com.lysenko.course.entity;

import com.lysenko.course.exception.ArrayException;

public class ProjectArrayFactory {

  private ProjectArrayFactory() {
  }

  public static ProjectArray createArray(long arrayId, int[] digits) throws ArrayException {
    return new ProjectArray(arrayId, digits);
  }

  public static ProjectArray createSampleArray(long arrayId) throws ArrayException {
    int[] sampleDigits = {5, 2, 8, 1, 9, -3, 0, 7, -1, 4};
    return new ProjectArray(arrayId, sampleDigits);
  }
}