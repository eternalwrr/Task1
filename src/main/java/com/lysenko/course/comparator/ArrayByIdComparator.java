package com.lysenko.course.comparator;

import com.lysenko.course.entity.ProjectArray;
import java.util.Comparator;

public class ArrayByIdComparator implements Comparator<ProjectArray> {

  @Override
  public int compare(ProjectArray array1, ProjectArray array2) {
    return Long.compare(array1.getArrayId(), array2.getArrayId());
  }
}