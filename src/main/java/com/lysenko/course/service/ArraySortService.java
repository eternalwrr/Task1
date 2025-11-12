package com.lysenko.course.service;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;

public interface ArraySortService {

  void bubbleSort(ProjectArray array) throws ArrayException;
  void selectionSort(ProjectArray array) throws ArrayException;
  void insertionSort(ProjectArray array) throws ArrayException;

  ProjectArray getSortedCopy(ProjectArray array) throws ArrayException;
}