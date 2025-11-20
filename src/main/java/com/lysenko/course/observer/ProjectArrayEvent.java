package com.lysenko.course.observer;
import com.lysenko.course.entity.ProjectArray;

import java.util.EventObject;

public class ProjectArrayEvent extends EventObject {
  public ProjectArrayEvent(ProjectArray source) {
    super(source);
  }

  public ProjectArray getProjectArray() {
    return (ProjectArray)getSource();
  }
}
