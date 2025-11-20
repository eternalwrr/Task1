package com.lysenko.course.observer;

public interface ProjectArrayObservable {
  void attach(ProjectArrayObserver observer);
  void detach(ProjectArrayObserver observer);
  void notifyObservers();
}
