package com.lysenko.course.factory.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.factory.ProjectArrayFactory;

public class ProjectArrayFactoryImpl implements ProjectArrayFactory {

  @Override
  public ProjectArray createArray(long arrayId, int[] digits) throws ArrayException {
    return new ProjectArray(arrayId, digits);
  }
}