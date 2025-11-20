package com.lysenko.course.service.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.factory.impl.ProjectArrayFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayManipulationServiceImplTest {
  private ArrayManipulationServiceImpl service;
  private ProjectArrayFactoryImpl factory;
  private ProjectArray testArray;

  @BeforeEach
  void setUp() throws ArrayException {
    service = new ArrayManipulationServiceImpl();
    factory = new ProjectArrayFactoryImpl();
    testArray = factory.createArray(1L, new int[]{5, -2, 0, 8, -1, 3});
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
    assertArrayEquals(new int[]{5, 0, 3}, result.getDigits());
  }

  @Test
  void findMinDigitWithEmptyArray() throws ArrayException {
    ProjectArray emptyArray = factory.createArray(2L, new int[0]);
    assertThrows(ArrayException.class, () -> service.findMinDigit(emptyArray));
  }

  @Test
  void findMaxDigitWithEmptyArray() throws ArrayException {
    ProjectArray emptyArray = factory.createArray(3L, new int[0]);
    assertThrows(ArrayException.class, () -> service.findMaxDigit(emptyArray));
  }

  @Test
  void calculateSumWithNullArray() {
    assertThrows(ArrayException.class, () -> service.calculateSum(null));
  }

  @Test
  void replaceDigitsWithNullArray() {
    assertThrows(ArrayException.class,
            () -> service.replaceDigitsByCondition(null, value -> value > 0, 1));
  }

  @Test
  void replaceDigitsWithNullCondition() throws ArrayException {
    assertThrows(ArrayException.class,
            () -> service.replaceDigitsByCondition(testArray, null, 1));
  }
}