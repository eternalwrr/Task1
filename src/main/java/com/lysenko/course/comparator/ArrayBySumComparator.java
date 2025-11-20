package com.lysenko.course.comparator;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.warehouse.Warehouse;
import java.util.Comparator;

public class ArrayBySumComparator implements Comparator<ProjectArray> {

  @Override
  public int compare(ProjectArray array1, ProjectArray array2) {
    var params1 = Warehouse.getInstance().getParameters(String.valueOf(array1.getArrayId()));
    var params2 = Warehouse.getInstance().getParameters(String.valueOf(array2.getArrayId()));

    if (params1 == null || params2 == null) {
      return 0;
    }
    return Integer.compare(params1.sum(), params2.sum());
  }
}