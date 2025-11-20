package com.lysenko.course.repository;

import com.lysenko.course.entity.ProjectArray;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ArrayRepository {
  void addArray(ProjectArray array);
  boolean removeArray(long arrayId);
  Optional<ProjectArray> findById(long arrayId);
  List<ProjectArray> findAll();
  List<ProjectArray> findBy(Predicate<ProjectArray> specification);
  void clear();
  int size();
}