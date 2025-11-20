package com.lysenko.course.factory;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.exception.ArrayException;

public interface ProjectArrayFactory {
  ProjectArray createArray(long arrayId, int[] digits) throws ArrayException;
}