package com.lysenko.course.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
  private static Warehouse instance;
  private Map<String, ProjectArrayParameters> parametersMap = new HashMap<>();

  private Warehouse() {}

  public static Warehouse getInstance() {
    if (instance == null) {
      instance = new Warehouse();
    }
    return instance;
  }

  public void putParameters(String id, ProjectArrayParameters params) {
    parametersMap.put(id, params);
  }

  public ProjectArrayParameters getParameters(String id) {
    return parametersMap.get(id);
  }

  public void removeParameters(String id) {
    parametersMap.remove(id);
  }

  public boolean containsParameters(String id) {
    return parametersMap.containsKey(id);
  }

  public void clear() {
    parametersMap.clear();
  }
}