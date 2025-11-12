package com.lysenko.course.entity;

import com.lysenko.course.exception.ArrayException;

public class ProjectArray {
  private final long arrayId;
  private final int[] digits;


  ProjectArray(long arrayId, int[] digits) throws ArrayException {
    if (digits == null) {
      throw new ArrayException("Digits array cannot be null");
    }
    this.arrayId = arrayId;
    this.digits = digits.clone();
  }

  ProjectArray(long arrayId, int size) throws ArrayException {
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
  }

  public boolean isEmpty() {
    return digits.length == 0;
  }

  public boolean contains(int value) {
    for (int digit : digits) {
      if (digit == value) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;

    ProjectArray that = (ProjectArray) object;
    return arrayId == that.arrayId && java.util.Arrays.equals(digits, that.digits);
  }

  @Override
  public int hashCode() {
    int result = (int) (arrayId ^ (arrayId >>> 32));
    result = 31 * result + java.util.Arrays.hashCode(digits);
    return result;
  }

  @Override
  public String toString() {
    return "ProjectArray{arrayId=" + arrayId + ", digits=" + java.util.Arrays.toString(digits) + "}";
  }
}