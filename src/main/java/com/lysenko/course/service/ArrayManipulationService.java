package com.lysenko.course.service;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;
import java.util.function.IntPredicate;

public interface ArrayManipulationService {

  int findMinDigit(ProjectArray array) throws ArrayException;
  int findMaxDigit(ProjectArray array) throws ArrayException;

  void replaceDigitsByCondition(ProjectArray array, IntPredicate condition, int newValue)
          throws ArrayException;

  double calculateAverageDigit(ProjectArray array) throws ArrayException;
  int calculateSum(ProjectArray array) throws ArrayException;

  int countPositiveDigits(ProjectArray array) throws ArrayException;
  int countNegativeDigits(ProjectArray array) throws ArrayException;
  int countZeroDigits(ProjectArray array) throws ArrayException;

  ProjectArray findDigitsInRange(ProjectArray array, int minValue, int maxValue)
          throws ArrayException;
}