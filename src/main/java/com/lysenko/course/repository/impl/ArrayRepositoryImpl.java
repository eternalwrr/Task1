package com.lysenko.course.repository.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.repository.ArrayRepository;
import com.lysenko.course.comparator.*;
import com.lysenko.course.repository.spec.ArraySpecifications;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public class ArrayRepositoryImpl implements ArrayRepository {
  private static ArrayRepositoryImpl instance;
  private final List<ProjectArray> arrays;

  private ArrayRepositoryImpl() {
    this.arrays = new CopyOnWriteArrayList<>();
  }

  public static synchronized ArrayRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new ArrayRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void addArray(ProjectArray array) {
    arrays.add(array);
  }

  @Override
  public boolean removeArray(long arrayId) {
    return arrays.removeIf(arr -> arr.getArrayId() == arrayId);
  }

  @Override
  public Optional<ProjectArray> findById(long arrayId) {
    return arrays.stream()
            .filter(arr -> arr.getArrayId() == arrayId)
            .findFirst();
  }

  @Override
  public List<ProjectArray> findAll() {
    return new ArrayList<>(arrays);
  }

  @Override
  public List<ProjectArray> findBy(Predicate<ProjectArray> specification) {
    return arrays.stream()
            .filter(specification)
            .toList();
  }

  @Override
  public void clear() {
    arrays.clear();
  }

  @Override
  public int size() {
    return arrays.size();
  }

  public List<ProjectArray> findAllSortedById() {
    List<ProjectArray> result = new ArrayList<>(arrays);
    result.sort(new ArrayByIdComparator());
    return result;
  }

  public List<ProjectArray> findAllSortedByFirstElement() {
    List<ProjectArray> result = new ArrayList<>(arrays);
    result.sort(new ArrayByFirstElementComparator());
    return result;
  }

  public List<ProjectArray> findAllSortedByLength() {
    List<ProjectArray> result = new ArrayList<>(arrays);
    result.sort(new ArrayByLengthComparator());
    return result;
  }

  public List<ProjectArray> findAllSortedBySum() {
    List<ProjectArray> result = new ArrayList<>(arrays);
    result.sort(new ArrayBySumComparator());
    return result;
  }
  public List<ProjectArray> findBySumGreaterThan(int value) {
    return findBy(ArraySpecifications.sumGreaterThan(value));
  }

  public List<ProjectArray> findBySumLessThan(int value) {
    return findBy(ArraySpecifications.sumLessThan(value));
  }

  public List<ProjectArray> findBySumEquals(int value) {
    return findBy(ArraySpecifications.sumEquals(value));
  }

  public List<ProjectArray> findByAverageGreaterThan(double value) {
    return findBy(ArraySpecifications.averageGreaterThan(value));
  }

  public List<ProjectArray> findByAverageLessThan(double value) {
    return findBy(ArraySpecifications.averageLessThan(value));
  }

  public List<ProjectArray> findByMinGreaterThan(int value) {
    return findBy(ArraySpecifications.minGreaterThan(value));
  }

  public List<ProjectArray> findByMaxLessThan(int value) {
    return findBy(ArraySpecifications.maxLessThan(value));
  }

  public List<ProjectArray> findByLength(int length) {
    return findBy(ArraySpecifications.lengthEquals(length));
  }

  public List<ProjectArray> findByLengthGreaterThan(int length) {
    return findBy(ArraySpecifications.lengthGreaterThan(length));
  }

  public List<ProjectArray> findByLengthLessThan(int length) {
    return findBy(ArraySpecifications.lengthLessThan(length));
  }

  public List<ProjectArray> findHasPositiveNumbers() {
    return findBy(ArraySpecifications.hasPositiveNumbers());
  }

  public List<ProjectArray> findHasNegativeNumbers() {
    return findBy(ArraySpecifications.hasNegativeNumbers());
  }

  public List<ProjectArray> findEmptyArrays() {
    return findBy(ArraySpecifications.isEmptyArray());
  }

  public List<ProjectArray> findBySumInRange(int minSum, int maxSum) {
    return findBy(ArraySpecifications.sumGreaterThan(minSum)
            .and(ArraySpecifications.sumLessThan(maxSum)));
  }

  public List<ProjectArray> findByLengthAndHasPositive(int length) {
    return findBy(ArraySpecifications.lengthEquals(length)
            .and(ArraySpecifications.hasPositiveNumbers()));
  }
}