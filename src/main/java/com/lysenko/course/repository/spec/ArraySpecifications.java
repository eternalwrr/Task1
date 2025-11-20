package com.lysenko.course.repository.spec;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.warehouse.Warehouse;
import java.util.function.Predicate;

public final class ArraySpecifications {

  private ArraySpecifications() {
  }

  public static Predicate<ProjectArray> sumGreaterThan(int value) {
    return array -> {
      var params = Warehouse.getInstance().getParameters(String.valueOf(array.getArrayId()));
      return params != null && params.sum() > value;
    };
  }

  public static Predicate<ProjectArray> sumLessThan(int value) {
    return array -> {
      var params = Warehouse.getInstance().getParameters(String.valueOf(array.getArrayId()));
      return params != null && params.sum() < value;
    };
  }

  public static Predicate<ProjectArray> sumEquals(int value) {
    return array -> {
      var params = Warehouse.getInstance().getParameters(String.valueOf(array.getArrayId()));
      return params != null && params.sum() == value;
    };
  }

  public static Predicate<ProjectArray> averageGreaterThan(double value) {
    return array -> {
      var params = Warehouse.getInstance().getParameters(String.valueOf(array.getArrayId()));
      return params != null && params.average() > value;
    };
  }

  public static Predicate<ProjectArray> averageLessThan(double value) {
    return array -> {
      var params = Warehouse.getInstance().getParameters(String.valueOf(array.getArrayId()));
      return params != null && params.average() < value;
    };
  }

  public static Predicate<ProjectArray> minGreaterThan(int value) {
    return array -> {
      var params = Warehouse.getInstance().getParameters(String.valueOf(array.getArrayId()));
      return params != null && params.min() > value;
    };
  }

  public static Predicate<ProjectArray> maxLessThan(int value) {
    return array -> {
      var params = Warehouse.getInstance().getParameters(String.valueOf(array.getArrayId()));
      return params != null && params.max() < value;
    };
  }

  public static Predicate<ProjectArray> lengthEquals(int length) {
    return array -> array.getLength() == length;
  }

  public static Predicate<ProjectArray> lengthGreaterThan(int length) {
    return array -> array.getLength() > length;
  }

  public static Predicate<ProjectArray> lengthLessThan(int length) {
    return array -> array.getLength() < length;
  }

  public static Predicate<ProjectArray> hasPositiveNumbers() {
    return array -> {
      try {
        for (int i = 0; i < array.getLength(); i++) {
          if (array.getDigit(i) > 0) {
            return true;
          }
        }
        return false;
      }
      catch (Exception e) {
        return false;
      }
    };
  }

  public static Predicate<ProjectArray> hasNegativeNumbers() {
    return array -> {
      try {
        for (int i = 0; i < array.getLength(); i++) {
          if (array.getDigit(i) < 0) {
            return true;
          }
        }
        return false;
      } catch (Exception e) {
        return false;
      }
    };
  }

  public static Predicate<ProjectArray> isEmptyArray() {
    return ProjectArray::isEmpty;
  }
}