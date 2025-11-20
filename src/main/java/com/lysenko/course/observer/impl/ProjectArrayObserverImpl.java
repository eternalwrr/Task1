package com.lysenko.course.observer.impl;

import com.lysenko.course.entity.ProjectArray;
import com.lysenko.course.observer.ProjectArrayEvent;
import com.lysenko.course.observer.ProjectArrayObserver;
import com.lysenko.course.service.ArrayManipulationService;
import com.lysenko.course.service.impl.ArrayManipulationServiceImpl;
import com.lysenko.course.warehouse.ProjectArrayParameters;
import com.lysenko.course.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProjectArrayObserverImpl implements ProjectArrayObserver {
  private static final Logger logger = LogManager.getLogger(ProjectArrayObserverImpl.class);
  private final ArrayManipulationService service = new ArrayManipulationServiceImpl();

  @Override
  public void projectArrayChanged(ProjectArrayEvent event) {
    ProjectArray array = event.getProjectArray();
    logger.info("Observer received update for ProjectArray id={}", array.getArrayId());

    try {
      int min = service.findMinDigit(array);
      int max = service.findMaxDigit(array);
      int sum = service.calculateSum(array);
      double average = service.calculateAverageDigit(array);

      ProjectArrayParameters params = new ProjectArrayParameters(min, max, sum, average);

      Warehouse.getInstance().putParameters(String.valueOf(array.getArrayId()), params);
      logger.info(
              "Updated parameters for array id={} -> min={}, max={}, sum={}, average={}",
              array.getArrayId(), min, max, sum, average
      );
    } catch (Exception e) {
      logger.error("Error calculating parameters for array id={}", array.getArrayId(), e);
    }
  }
}