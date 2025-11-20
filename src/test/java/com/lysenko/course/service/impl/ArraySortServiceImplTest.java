package com.lysenko.course.service.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.factory.impl.ProjectArrayFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArraySortServiceImplTest {
  private ArraySortServiceImpl service;
  private ProjectArrayFactoryImpl factory;
  private ProjectArray testArray;

  @BeforeEach
  void setUp() throws ArrayException {
    service = new ArraySortServiceImpl();
    factory = new ProjectArrayFactoryImpl();
    testArray = factory.createArray(1L, new int[]{5, -2, 0, 8, -1, 3});
  }

  @Test
  void bubbleSort() throws ArrayException {
    service.bubbleSort(testArray);
    assertArrayEquals(new int[]{-2, -1, 0, 3, 5, 8}, testArray.getDigits());
  }

  @Test
  void selectionSort() throws ArrayException {
    service.selectionSort(testArray);
    assertArrayEquals(new int[]{-2, -1, 0, 3, 5, 8}, testArray.getDigits());
  }

  @Test
  void insertionSort() throws ArrayException {
    service.insertionSort(testArray);
    assertArrayEquals(new int[]{-2, -1, 0, 3, 5, 8}, testArray.getDigits());
  }

  @Test
  void getSortedCopy() throws ArrayException {
    ProjectArray sortedCopy = service.getSortedCopy(testArray);

    // Оригинальный массив не должен измениться
    assertArrayEquals(new int[]{5, -2, 0, 8, -1, 3}, testArray.getDigits());
    // Копия должна быть отсортирована
    assertArrayEquals(new int[]{-2, -1, 0, 3, 5, 8}, sortedCopy.getDigits());
    // ID должны совпадать
    assertEquals(testArray.getArrayId(), sortedCopy.getArrayId());
  }

  @Test
  void sortWithEmptyArray() throws ArrayException {
    ProjectArray emptyArray = factory.createArray(2L, new int[0]);

    // Эти методы должны работать с пустым массивом
    service.bubbleSort(emptyArray);
    service.selectionSort(emptyArray);
    service.insertionSort(emptyArray);

    assertArrayEquals(new int[0], emptyArray.getDigits());
  }

  @Test
  void getSortedCopyWithEmptyArray() throws ArrayException {
    ProjectArray emptyArray = factory.createArray(3L, new int[0]);
    ProjectArray sortedCopy = service.getSortedCopy(emptyArray);

    assertArrayEquals(new int[0], sortedCopy.getDigits());
  }

  @Test
  void sortWithNullArray() {
    assertThrows(ArrayException.class, () -> service.bubbleSort(null));
    assertThrows(ArrayException.class, () -> service.selectionSort(null));
    assertThrows(ArrayException.class, () -> service.insertionSort(null));
    assertThrows(ArrayException.class, () -> service.getSortedCopy(null));
  }

  @Test
  void sortWithSingleElement() throws ArrayException {
    ProjectArray singleElementArray = factory.createArray(4L, new int[]{42});

    service.bubbleSort(singleElementArray);
    assertArrayEquals(new int[]{42}, singleElementArray.getDigits());

    service.selectionSort(singleElementArray);
    assertArrayEquals(new int[]{42}, singleElementArray.getDigits());

    service.insertionSort(singleElementArray);
    assertArrayEquals(new int[]{42}, singleElementArray.getDigits());

    ProjectArray sortedCopy = service.getSortedCopy(singleElementArray);
    assertArrayEquals(new int[]{42}, sortedCopy.getDigits());
  }
}