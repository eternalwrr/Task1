package com.lysenko.course.entity;

import com.lysenko.course.exception.ArrayException;
import com.lysenko.course.observer.ProjectArrayObservable;
import com.lysenko.course.observer.ProjectArrayObserver;
import com.lysenko.course.observer.ProjectArrayEvent;
import com.lysenko.course.observer.impl.ProjectArrayObserverImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ProjectArray implements ProjectArrayObservable {
  private final long arrayId;
  private int[] digits;
  private final List<ProjectArrayObserver> observers = new ArrayList<>();

  public ProjectArray(long arrayId, int[] digits) throws ArrayException {
    if (digits == null) {
      throw new ArrayException("Digits array cannot be null");
    }
    this.arrayId = arrayId;
    this.digits = digits.clone();
    this.attach(new ProjectArrayObserverImpl());
  }

  public ProjectArray(long arrayId, int size) throws ArrayException {
    if (size < 0) {
      throw new ArrayException("Size cannot be negative: " + size);
    }
    this.arrayId = arrayId;
    this.digits = new int[size];
  }

  public long getArrayId() {
    return arrayId;
  }

  public int[] getDigits() {
    return digits.clone();
  }

  public int getLength() {
    return digits.length;
  }

  public int getDigit(int index) throws ArrayException {
    if (index < 0 || index >= digits.length) {
      throw new ArrayException("Index out of bounds: " + index);
    }
    return digits[index];
  }

  public void setDigit(int index, int value) throws ArrayException {
    if (index < 0 || index >= digits.length) {
      throw new ArrayException("Index out of bounds: " + index);
    }
    digits[index] = value;
    notifyObservers();
  }

  public void setDigits(int[] newDigits) throws ArrayException {
    if (newDigits == null) {
      throw new ArrayException("Digits array cannot be null");
    }
    this.digits = newDigits.clone();
    notifyObservers();
  }

  public boolean isEmpty() {
    return digits.length == 0;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;

    ProjectArray that = (ProjectArray) object;
    return arrayId == that.arrayId && Arrays.equals(digits, that.digits);
  }

  @Override
  public int hashCode() {
    int result = (int) (arrayId ^ (arrayId >>> 32));
    result = 31 * result + Arrays.hashCode(digits);
    return result;
  }

  @Override
  public String toString() {
    return "ProjectArray{arrayId=" + arrayId + ", digits=" + Arrays.toString(digits) + "}";
  }

  @Override
  public void attach(ProjectArrayObserver observer) {
    if (observer != null && !observers.contains(observer)) {
      observers.add(observer);
    }
  }

  @Override
  public void detach(ProjectArrayObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    ProjectArrayEvent event = new ProjectArrayEvent(this);
    for (ProjectArrayObserver observer : observers) {
      observer.projectArrayChanged(event);
    }
  }
}