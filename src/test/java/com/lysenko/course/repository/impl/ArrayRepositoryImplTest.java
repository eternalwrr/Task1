package com.lysenko.course.repository.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.factory.ProjectArrayFactory;
import com.lysenko.course.factory.impl.ProjectArrayFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryImplTest {
  private ArrayRepositoryImpl repository;
  private ProjectArrayFactory factory;

  @BeforeEach
  void setUp() throws ArrayException {
    repository = ArrayRepositoryImpl.getInstance();
    factory = new ProjectArrayFactoryImpl();
    repository.clear();

    repository.addArray(factory.createArray(1L, new int[]{5, 2, 8}));
    repository.addArray(factory.createArray(2L, new int[]{1, 9, 4, 7}));
    repository.addArray(factory.createArray(3L, new int[]{3, 6}));
    repository.addArray(factory.createArray(4L, new int[]{-1, 0, 5}));
    repository.addArray(factory.createArray(5L, new int[]{}));
  }

  @Test
  void findBySumGreaterThan() {
    List<ProjectArray> result = repository.findBySumGreaterThan(10);
    assertEquals(2, result.size());
    assertTrue(result.stream().anyMatch(arr -> arr.getArrayId() == 1L));
    assertTrue(result.stream().anyMatch(arr -> arr.getArrayId() == 2L));
  }

  @Test
  void findByAverageGreaterThan() {
    List<ProjectArray> result = repository.findByAverageGreaterThan(5.0);
    assertEquals(1, result.size());
    assertEquals(2L, result.get(0).getArrayId());
  }

  @Test
  void findByLength() {
    List<ProjectArray> result = repository.findByLength(3);
    assertEquals(2, result.size());
    assertTrue(result.stream().anyMatch(arr -> arr.getArrayId() == 1L));
    assertTrue(result.stream().anyMatch(arr -> arr.getArrayId() == 4L));
  }

  @Test
  void findHasPositiveNumbers() {
    List<ProjectArray> result = repository.findHasPositiveNumbers();
    assertEquals(4, result.size()); // Все кроме пустого массива
    assertFalse(result.stream().anyMatch(arr -> arr.getArrayId() == 5L));
  }

  @Test
  void findEmptyArrays() {
    List<ProjectArray> result = repository.findEmptyArrays();
    assertEquals(1, result.size());
    assertEquals(5L, result.get(0).getArrayId());
  }

  @Test
  void findBySumInRange() {
    List<ProjectArray> result = repository.findBySumInRange(5, 20);
    assertEquals(2, result.size());
    assertTrue(result.stream().anyMatch(arr -> arr.getArrayId() == 1L)); // sum=15
    assertTrue(result.stream().anyMatch(arr -> arr.getArrayId() == 3L)); // sum=9
  }

  @Test
  void findAllSortedById() throws ArrayException {
    repository.clear();
    repository.addArray(factory.createArray(5L, new int[]{1}));
    repository.addArray(factory.createArray(2L, new int[]{2}));
    repository.addArray(factory.createArray(8L, new int[]{3}));

    List<ProjectArray> sorted = repository.findAllSortedById();
    assertEquals(2L, sorted.get(0).getArrayId());
    assertEquals(5L, sorted.get(1).getArrayId());
    assertEquals(8L, sorted.get(2).getArrayId());
  }

  @Test
  void findAllSortedByLength() throws ArrayException {
    repository.clear();
    repository.addArray(factory.createArray(1L, new int[]{1, 2, 3}));
    repository.addArray(factory.createArray(2L, new int[]{1}));
    repository.addArray(factory.createArray(3L, new int[]{1, 2}));

    List<ProjectArray> sorted = repository.findAllSortedByLength();
    assertEquals(2L, sorted.get(0).getArrayId()); // length=1
    assertEquals(3L, sorted.get(1).getArrayId()); // length=2
    assertEquals(1L, sorted.get(2).getArrayId()); // length=3
  }
}