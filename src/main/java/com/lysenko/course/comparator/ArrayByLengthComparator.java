package com.lysenko.course.comparator;

import com.lysenko.course.entity.ProjectArray;
import java.util.Comparator;

public class ArrayByLengthComparator implements Comparator<ProjectArray> {

  @Override
  public int compare(ProjectArray array1, ProjectArray array2) {
    return Integer.compare(array1.getLength(), array2.getLength());
  }
}