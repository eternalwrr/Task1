package com.lysenko.course.service.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.entity.ProjectArrayFactory;
import com.lysenko.course.exception.ArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArraySortServiceImplTest {
  private ArraySortServiceImpl service;
  private ProjectArray unsortedArray;

  @BeforeEach
  void setUp() throws ArrayException {
    service = new ArraySortServiceImpl();
    unsortedArray = ProjectArrayFactory.createArray(1L, new int[]{5, 2, 8, 1, 9, 3});
  }

  @Test
  void bubbleSort() throws ArrayException {
    service.bubbleSort(unsortedArray);
    assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, unsortedArray.getDigits());
  }

  @Test
  void selectionSort() throws ArrayException {
    service.selectionSort(unsortedArray);
    assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, unsortedArray.getDigits());
  }

  @Test
  void insertionSort() throws ArrayException {
    service.insertionSort(unsortedArray);
    assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, unsortedArray.getDigits());
  }

  @Test
  void getSortedCopy() throws ArrayException {
    ProjectArray sortedCopy = service.getSortedCopy(unsortedArray);
    assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, sortedCopy.getDigits());
  }
}