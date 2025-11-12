package com.lysenko.course.service.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.service.ArraySortService;
import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.entity.ProjectArrayFactory;

public class ArraySortServiceImpl implements ArraySortService {

  @Override
  public void bubbleSort(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");

    int n = array.getLength();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (array.getDigit(j) > array.getDigit(j + 1)) {
          int temp = array.getDigit(j);
          array.setDigit(j, array.getDigit(j + 1));
          array.setDigit(j + 1, temp);
        }
      }
    }
  }

  @Override
  public void selectionSort(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");

    int n = array.getLength();
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (array.getDigit(j) < array.getDigit(minIndex)) {
          minIndex = j;
        }
      }
      int temp = array.getDigit(minIndex);
      array.setDigit(minIndex, array.getDigit(i));
      array.setDigit(i, temp);
    }
  }

  @Override
  public void insertionSort(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");

    int n = array.getLength();
    for (int i = 1; i < n; i++) {
      int key = array.getDigit(i);
      int j = i - 1;

      while (j >= 0 && array.getDigit(j) > key) {
        array.setDigit(j + 1, array.getDigit(j));
        j = j - 1;
      }
      array.setDigit(j + 1, key);
    }
  }

  @Override
  public ProjectArray getSortedCopy(ProjectArray array) throws ArrayException {
    if (array == null) throw new ArrayException("Array cannot be null");

    ProjectArray copy = ProjectArrayFactory.createArray(array.getArrayId() + 1000, array.getDigits());
    bubbleSort(copy);
    return copy;
  }
}