package com.lysenko.course.repository.spec;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.factory.ProjectArrayFactory;
import com.lysenko.course.factory.impl.ProjectArrayFactoryImpl;
import com.lysenko.course.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArraySpecificationsTest {
  private ProjectArrayFactory factory;
  private ProjectArray testArray;

  @BeforeEach
  void setUp() throws ArrayException {
    factory = new ProjectArrayFactoryImpl();
    Warehouse.getInstance().clear();

    testArray = factory.createArray(1L, new int[]{5, -2, 8, 0, 3});
    Warehouse.getInstance().putParameters("1",
            new com.lysenko.course.warehouse.ProjectArrayParameters(-2, 8, 14, 2.8));
  }

  @Test
  void sumGreaterThan_ShouldReturnTrue_WhenSumIsGreater() {
    assertTrue(ArraySpecifications.sumGreaterThan(10).test(testArray));
    assertFalse(ArraySpecifications.sumGreaterThan(20).test(testArray));
  }

  @Test
  void sumLessThan_ShouldReturnTrue_WhenSumIsLess() {
    assertTrue(ArraySpecifications.sumLessThan(20).test(testArray));
    assertFalse(ArraySpecifications.sumLessThan(10).test(testArray));
  }

  @Test
  void sumEquals_ShouldReturnTrue_WhenSumMatches() {
    assertTrue(ArraySpecifications.sumEquals(14).test(testArray));
    assertFalse(ArraySpecifications.sumEquals(10).test(testArray));
  }

  @Test
  void averageGreaterThan_ShouldReturnTrue_WhenAverageIsGreater() {
    assertTrue(ArraySpecifications.averageGreaterThan(2.0).test(testArray));
    assertFalse(ArraySpecifications.averageGreaterThan(5.0).test(testArray));
  }

  @Test
  void averageLessThan_ShouldReturnTrue_WhenAverageIsLess() {
    assertTrue(ArraySpecifications.averageLessThan(5.0).test(testArray));
    assertFalse(ArraySpecifications.averageLessThan(2.0).test(testArray));
  }

  @Test
  void minGreaterThan_ShouldReturnTrue_WhenMinIsGreater() {
    assertTrue(ArraySpecifications.minGreaterThan(-5).test(testArray));
    assertFalse(ArraySpecifications.minGreaterThan(0).test(testArray));
  }

  @Test
  void maxLessThan_ShouldReturnTrue_WhenMaxIsLess() {
    assertTrue(ArraySpecifications.maxLessThan(10).test(testArray));
    assertFalse(ArraySpecifications.maxLessThan(5).test(testArray));
  }

  @Test
  void lengthEquals_ShouldReturnTrue_WhenLengthMatches() throws ArrayException {
    ProjectArray array = factory.createArray(2L, new int[]{1, 2, 3});

    assertTrue(ArraySpecifications.lengthEquals(3).test(array));
    assertFalse(ArraySpecifications.lengthEquals(2).test(array));
  }

  @Test
  void lengthGreaterThan_ShouldReturnTrue_WhenLengthIsGreater() throws ArrayException {
    ProjectArray array = factory.createArray(2L, new int[]{1, 2, 3, 4});

    assertTrue(ArraySpecifications.lengthGreaterThan(3).test(array));
    assertFalse(ArraySpecifications.lengthGreaterThan(5).test(array));
  }

  @Test
  void lengthLessThan_ShouldReturnTrue_WhenLengthIsLess() throws ArrayException {
    ProjectArray array = factory.createArray(2L, new int[]{1, 2});

    assertTrue(ArraySpecifications.lengthLessThan(3).test(array));
    assertFalse(ArraySpecifications.lengthLessThan(2).test(array));
  }

  @Test
  void hasPositiveNumbers_ShouldReturnTrue_WhenArrayHasPositiveNumbers() throws ArrayException {
    ProjectArray withPositive = factory.createArray(2L, new int[]{-1, 5, -3});
    ProjectArray withoutPositive = factory.createArray(3L, new int[]{-1, -2, -3});

    assertTrue(ArraySpecifications.hasPositiveNumbers().test(withPositive));
    assertFalse(ArraySpecifications.hasPositiveNumbers().test(withoutPositive));
  }

  @Test
  void hasNegativeNumbers_ShouldReturnTrue_WhenArrayHasNegativeNumbers() throws ArrayException {
    ProjectArray withNegative = factory.createArray(2L, new int[]{1, -5, 3});
    ProjectArray withoutNegative = factory.createArray(3L, new int[]{1, 2, 3});

    assertTrue(ArraySpecifications.hasNegativeNumbers().test(withNegative));
    assertFalse(ArraySpecifications.hasNegativeNumbers().test(withoutNegative));
  }

  @Test
  void isEmptyArray_ShouldReturnTrue_WhenArrayIsEmpty() throws ArrayException {
    ProjectArray emptyArray = factory.createArray(2L, new int[]{});
    ProjectArray nonEmptyArray = factory.createArray(3L, new int[]{1, 2});

    assertTrue(ArraySpecifications.isEmptyArray().test(emptyArray));
    assertFalse(ArraySpecifications.isEmptyArray().test(nonEmptyArray));
  }

  @Test
  void specifications_ShouldHandleNullWarehouseData() throws ArrayException {
    // Массив без данных в Warehouse
    ProjectArray newArray = factory.createArray(999L, new int[]{1, 2, 3});

    assertFalse(ArraySpecifications.sumGreaterThan(10).test(newArray));
    assertFalse(ArraySpecifications.averageGreaterThan(1.0).test(newArray));
  }

  @Test
  void combinedSpecifications_ShouldWorkCorrectly() throws ArrayException {
    ProjectArray array = factory.createArray(2L, new int[]{1, 2, 3});
    Warehouse.getInstance().putParameters("2",
            new com.lysenko.course.warehouse.ProjectArrayParameters(1, 3, 6, 2.0));

    var combinedSpec = ArraySpecifications.lengthEquals(3)
            .and(ArraySpecifications.sumGreaterThan(5));

    assertTrue(combinedSpec.test(array));
  }

  @Test
  void edgeCase_EmptyArrayWithSpecifications() throws ArrayException {
    ProjectArray emptyArray = factory.createArray(2L, new int[]{});

    assertTrue(ArraySpecifications.isEmptyArray().test(emptyArray));
    assertFalse(ArraySpecifications.hasPositiveNumbers().test(emptyArray));
    assertFalse(ArraySpecifications.hasNegativeNumbers().test(emptyArray));
    assertTrue(ArraySpecifications.lengthEquals(0).test(emptyArray));
  }
}