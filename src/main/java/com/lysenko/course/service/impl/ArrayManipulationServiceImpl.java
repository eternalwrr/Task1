package com.lysenko.course.service.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.factory.impl.ProjectArrayFactoryImpl;
import com.lysenko.course.service.ArrayManipulationService;
import java.util.function.IntPredicate;

public class ArrayManipulationServiceImpl implements ArrayManipulationService {
  private final ProjectArrayFactoryImpl factory;

  public ArrayManipulationServiceImpl() {
    this.factory = new ProjectArrayFactoryImpl();
  }

  @Override
  public int findMinDigit(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");
    if (array.isEmpty()) throw new ArrayException("Cannot find min digit in empty array");

    int minDigit = array.getDigit(0);
    for (int i = 1; i < array.getLength(); i++) {
      int current = array.getDigit(i);
      if (current < minDigit) {
        minDigit = current;
      }
    }
    return minDigit;
  }

  @Override
  public int findMaxDigit(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");
    if (array.isEmpty()) throw new ArrayException("Cannot find max digit in empty array");

    int maxDigit = array.getDigit(0);
    for (int i = 1; i < array.getLength(); i++) {
      int current = array.getDigit(i);
      if (current > maxDigit) {
        maxDigit = current;
      }
    }
    return maxDigit;
  }

  @Override
  public void replaceDigitsByCondition(ProjectArray array, IntPredicate condition, int newValue)
          throws ArrayException {
    if (array == null || condition == null) {
      throw new ArrayException("Arguments cannot be null");
    }
    for (int i = 0; i < array.getLength(); i++) {
      if (condition.test(array.getDigit(i))) {
        array.setDigit(i, newValue);
      }
    }
  }
  @Override
  public double calculateAverageDigit(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");
    if (array.isEmpty()) return 0.0;

    return (double) calculateSum(array) / array.getLength();
  }
  @Override
  public int calculateSum(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");
    int total = 0;
    for (int i = 0; i < array.getLength(); i++) {
      total += array.getDigit(i);
    }
    return total;
  }
  @Override
  public int countPositiveDigits(ProjectArray array) throws ArrayException {
    return countDigitsByCondition(array, value -> value > 0);
  }

  @Override
  public int countNegativeDigits(ProjectArray array) throws ArrayException {
    return countDigitsByCondition(array, value -> value < 0);
  }

  @Override
  public int countZeroDigits(ProjectArray array) throws ArrayException {
    return countDigitsByCondition(array, value -> value == 0);
  }

  @Override
  public ProjectArray findDigitsInRange(ProjectArray array, int minValue, int maxValue)
          throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");
    if (minValue > maxValue) throw new ArrayException("Min value cannot be bigger than max value");

    int[] tempResults = new int[array.getLength()];
    int count = 0;

    for (int i = 0; i < array.getLength(); i++) {
      int value = array.getDigit(i);
      if (value >= minValue && value <= maxValue) {
        tempResults[count] = value;
        count++;
      }
    }
    int[] results = new int[count];
    System.arraycopy(tempResults, 0, results, 0, count);

    return factory.createArray(array.getArrayId(), results);
  }
  private int countDigitsByCondition(ProjectArray array, IntPredicate condition)
          throws ArrayException {
    if (array == null || condition == null) {
      throw new ArrayException("Arguments cannot be null");
    }
    int count = 0;
    for (int i = 0; i < array.getLength(); i++) {
      if (condition.test(array.getDigit(i))) {
        count++;
      }
    }
    return count;
  }
}