package com.exist.ecc.matrix.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class AsciiMatrixCell implements Comparable<AsciiMatrixCell>, Iterable<String> {

  private List<String> data;

  public AsciiMatrixCell() {
    data = new ArrayList();
  }

  public int size() {
    return data.size();
  }

  public String get(int index) throws IndexOutOfBoundsException {
    return data.get(index);
  }

  public void set(int index, String newVal) 
      throws IndexOutOfBoundsException, IllegalArgumentException {
    for (char c : newVal.toCharArray()) {
      if (!isValid(c)) {
        String msg = "Input string contains an illegal character ('" + c + "').";
        throw new IllegalArgumentException(msg);
      }
    }

    data.set(index, newVal);
  }

  public void add(String element) {
    data.add(element);
  }

  public void clear() {
    data.clear();
  }

  public Iterator<String> iterator() {
    return data.iterator();
  }

  public void autoFill(int targetSize) throws IllegalArgumentException {
    if (targetSize < 0) {
      throw new IllegalArgumentException("Negative number of columns is not allowed.");
    }

    data.clear();
    ((ArrayList)data).ensureCapacity(targetSize);

    for (int index = 0; index < targetSize; index++) {
      data.add(index, generateRandomCell(AsciiMatrixConventions.DEFAULT_ELEMENT_LENGTH));
    }
  }

  public void autoFill() {
    autoFill(AsciiMatrixConventions.getCellSize());
  }

  private String generateRandomCell(int length) {
    StringBuilder sb = new StringBuilder(length);

    for (int index = 0; index < length; index++) {
      char randomChar;
      do {
        randomChar = AsciiMatrixConventions.getRandomChar();
      } while (AsciiMatrixConventions.isIllegal(randomChar));

      sb.append(randomChar);
    }

    return sb.toString();
  }

  public static boolean isValid(char c) {
    return !AsciiMatrixConventions.isIllegal(c);
  }


  public int[] getQueryOccurrences(String query) {
    int[] occurrences = new int[size()];

    for (int index = 0; index < size(); index++) {
      String element = get(index);
      for (int pos = 0; pos <= element.length() - query.length(); pos++) {
        String substr = element.substring(pos, pos + query.length());
        if (substr.equals(query)) {
          occurrences[index]++;
        }
      }
    }

    return occurrences;
  }

  private String concatenate(String delimiter) {
    StringBuilder sb = new StringBuilder();
    
    for (int index = 0; index < size(); index++) {
      sb.append(get(index));
      if (index < size() - 1) { 
        sb.append(delimiter); 
      }
    }

    return sb.toString();
  }

  public String concatenate() {
    return concatenate("");
  }

  @Override
  public int compareTo(AsciiMatrixCell other) {
    String thisConcat = this.concatenate();
    String otherConcat = other.concatenate();

    return thisConcat.compareToIgnoreCase(otherConcat);
  }

  @Override
  public String toString() {
    return concatenate(AsciiMatrixConventions.textElementDivider());
  }
}