package com.lysenko.course.service.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.entity.ProjectArrayFactory;
import com.lysenko.course.exception.ArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayManipulationServiceImplTest {
  private ArrayManipulationServiceImpl service;
  private ProjectArray testArray;

  @BeforeEach
  void setUp() throws ArrayException {
    service = new ArrayManipulationServiceImpl();
    testArray = ProjectArrayFactory.createArray(1L, new int[]{5, -2, 0, 8, -1, 3});
  }

  @Test
  void findMinDigit() throws ArrayException {
    assertEquals(-2, service.findMinDigit(testArray));
  }

  @Test
  void findMaxDigit() throws ArrayException {
    assertEquals(8, service.findMaxDigit(testArray));
  }

  @Test
  void calculateSum() throws ArrayException {
    assertEquals(13, service.calculateSum(testArray));
  }

  @Test
  void calculateAverageDigit() throws ArrayException {
    assertEquals(2.166, service.calculateAverageDigit(testArray), 0.001);
  }

  @Test
  void countPositiveDigits() throws ArrayException {
    assertEquals(3, service.countPositiveDigits(testArray));
  }

  @Test
  void countNegativeDigits() throws ArrayException {
    assertEquals(2, service.countNegativeDigits(testArray));
  }

  @Test
  void countZeroDigits() throws ArrayException {
    assertEquals(1, service.countZeroDigits(testArray));
  }

  @Test
  void replaceDigitsByCondition() throws ArrayException {
    service.replaceDigitsByCondition(testArray, value -> value < 0, 999);
    assertEquals(999, testArray.getDigit(1));
    assertEquals(999, testArray.getDigit(4));
  }

  @Test
  void findDigitsInRange() throws ArrayException {
    ProjectArray result = service.findDigitsInRange(testArray, 0, 5);
    assertEquals(3, result.getLength());
  }

  @Test
  void findMinDigitWithEmptyArray() throws ArrayException {
    ProjectArray emptyArray = ProjectArrayFactory.createArray(2L, new int[0]);
    assertThrows(ArrayException.class, () -> service.findMinDigit(emptyArray));
  }

  @Test
  void calculateSumWithNullArray() {
    assertThrows(ArrayException.class, () -> service.calculateSum(null));
  }
}