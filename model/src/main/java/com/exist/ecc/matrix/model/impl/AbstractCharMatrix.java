package com.exist.ecc.matrix.model.impl;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Stream;
import static java.util.stream.Collectors.joining;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.exist.ecc.matrix.model.api.CharMatrix;
import com.exist.ecc.matrix.model.api.CharDomain;

public abstract class AbstractCharMatrix implements CharMatrix {
  protected Path source;
  protected CharDomain domain;

  public AbstractCharMatrix(CharDomain domain) {
    this.domain = domain;
  }

  public CharDomain getDomain() {
    return domain;
  }

  public Path getSource() {
    return source;
  }

  public void setSource(Path newSource) {
    this.source = newSource;
  }

  public String getKey(int row, int col) throws IllegalArgumentException {
    validateAndThrow(validateRowIndex(row), 
                     validateColumnIndex(row, col));

    return getKeyImplementation(row, col);
  }

  protected abstract String getKeyImplementation(int row, int col);

  public void setKey(int row, int col, String newKey) throws IllegalArgumentException {
    validateAndThrow(validateRowIndex(row), 
                     validateColumnIndex(row, col),
                     validateContent(newKey));

    setKeyImplementation(row, col, newKey);
  }

  protected abstract void setKeyImplementation(int row, int col, String newKey);

  public String getValue(int row, int col) throws IllegalArgumentException {
    validateAndThrow(validateRowIndex(row), 
                     validateColumnIndex(row, col));

    return getValueImplementation(row, col);
  }

  protected abstract String getValueImplementation(int row, int col);

  public void setValue(int row, int col, String newValue) throws IllegalArgumentException {
    validateAndThrow(validateRowIndex(row), 
                     validateColumnIndex(row, col),
                     validateContent(newValue));

    setValueImplementation(row, col, newValue);
  }

  protected abstract void setValueImplementation(int row, int col, String newKey);

  public void put(int row, String key, String value) throws IllegalArgumentException {
    validateAndThrow(validateRowIndex(row),
                     validateContent(key),
                     validateContent(value));

    putImplementation(row, key, value);
  }

  protected abstract void putImplementation(int row, String key, String value);

  public int cols(int row) throws IllegalArgumentException {
    validateAndThrow(validateRowIndex(row));

    return colsImplementation(row);
  }

  protected abstract int colsImplementation(int row);

  public void sortRow(int row, boolean descending) throws IllegalArgumentException {
    validateAndThrow(validateRowIndex(row));

    sortRowImplementation(row, descending);
  }

  protected abstract void sortRowImplementation(int row, boolean descending);

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < rows(); row++) {
      for (int col = 0; col < cols(row); col++) {
        sb.append(getKey(row, col));
        sb.append(", ");
        sb.append(getValue(row, col));

        if (col < cols(row) - 1) {
          sb.append("  |  ");
        }
      }

      if (row < rows() - 1) {
        sb.append(System.lineSeparator());
      }
    }
    
    return sb.toString();
  }

  protected Set<String> validateRowIndex(int row) {
    Set<String> errors = new HashSet();

    if (row >= rows()) {
      errors.add("row index out of bounds (" + row + ")");
    }

    if (row < 0) {
      errors.add("negative row index not allowed (" + row + ")");
    }

    return errors;
  }

  protected Set<String> validateColumnIndex(int row, int col) {
    Set<String> errors = new HashSet();

    try {
      if (col >= cols(row)) {
        errors.add("column index out of bounds (" + row + ", " + col + ")");
      }
    } catch (IllegalArgumentException e) {
      errors.add(e.getMessage());
    }

    if (col < 0) {
      errors.add("negative column index not allowed (" + row + ", " + col + ")");
    }

    return errors;
  }

  protected Set<String> validateContent(String content) {
    Set<String> errors = new HashSet();
    Set<Character> illegalChars = new HashSet();

    for (char c : content.toCharArray()) {
      if (!domain.contains(c)) {
        illegalChars.add(c);
      }
    }

    if (!illegalChars.isEmpty()) {
      String illegalCharString = illegalChars
                                 .stream()
                                 .map(c -> String.valueOf(c))
                                 .collect(joining(", "));
      
      errors.add("input contains illegal character(s): " + illegalCharString);
    }

    return errors;
  }

  protected void validateAndThrow(Set<String>... validations) throws IllegalArgumentException {
    String errors = Arrays.stream(validations)
                    .flatMap(x -> x.stream())
                    .distinct()
                    .collect(joining("; "));

    if (!errors.isEmpty()) {
      throw new IllegalArgumentException(errors);
    }
  }
}