package com.lysenko.course.service.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.factory.impl.ProjectArrayFactoryImpl;
import com.lysenko.course.service.ArraySortService;
import java.util.Arrays;

public class ArraySortServiceImpl implements ArraySortService {

  private final ProjectArrayFactoryImpl factory;
  public ArraySortServiceImpl() {
    factory = new ProjectArrayFactoryImpl();
  }

  @Override
  public void bubbleSort(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");

    int[] digits = array.getDigits();
    int n = digits.length;

    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (digits[j] > digits[j + 1]) {
          int temp = digits[j];
          digits[j] = digits[j + 1];
          digits[j + 1] = temp;
        }
      }
    }
    array.setDigits(digits);
  }

  @Override
  public void selectionSort(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");

    int[] digits = array.getDigits();
    int n = digits.length;

    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (digits[j] < digits[minIndex]) {
          minIndex = j;
        }
      }
      int temp = digits[minIndex];
      digits[minIndex] = digits[i];
      digits[i] = temp;
    }
    array.setDigits(digits);
  }

  @Override
  public void insertionSort(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");

    int[] digits = array.getDigits();
    int n = digits.length;

    for (int i = 1; i < n; i++) {
      int key = digits[i];
      int j = i - 1;

      while (j >= 0 && digits[j] > key) {
        digits[j + 1] = digits[j];
        j = j - 1;
      }
      digits[j + 1] = key;
    }
    array.setDigits(digits);
  }

  @Override
  public ProjectArray getSortedCopy(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");

    int[] sortedDigits = array.getDigits().clone();
    Arrays.sort(sortedDigits);
    return factory.createArray(array.getArrayId(), sortedDigits);
  }
}