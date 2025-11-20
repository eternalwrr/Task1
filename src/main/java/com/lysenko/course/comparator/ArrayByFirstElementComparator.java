package com.lysenko.course.comparator;

import com.lysenko.course.entity.ProjectArray;
import java.util.Comparator;

public class ArrayByFirstElementComparator implements Comparator<ProjectArray> {

  @Override
  public int compare(ProjectArray array1, ProjectArray array2) {
    try {
      if (array1.isEmpty() || array2.isEmpty()) {
        return 0;
      }
      return Integer.compare(array1.getDigit(0), array2.getDigit(0));
    }
    catch (Exception e) {
      return 0;
    }
  }
}