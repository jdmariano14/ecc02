package com.exist.ecc.matrix.model.api;

import java.nio.file.Paths;

public abstract class AbstractCharMatrix implements CharMatrix {
  protected String source;
  protected CharDomain domain;

  public String getSource() {
    return source;
  }

  public void setSource(String newSource) throws IllegalArgumentException {
    try {
      source = Paths.get(newSource).toString();
    } catch (Exception e) {
      throw new IllegalArgumentException("Could not find or access file.");
    }
  }

  public String getKey(int row, int col) throws IllegalArgumentException {
    validateRowIndex(row);
    validateColumnIndex(col);

    return "";
  }

  public void setKey(int row, int col, String newKey) throws IllegalArgumentException {
    validateRowIndex(row);
    validateColumnIndex(col);
    validateContent(newKey);
  }

  public String getValue(int row, int col) throws IllegalArgumentException {
    validateRowIndex(row);
    validateColumnIndex(col);

    return "";
  }

  public void setValue(int row, int col, String newValue) throws IllegalArgumentException {
    validateRowIndex(row);
    validateColumnIndex(col);
    validateContent(newValue);
  }

  public void put(int row, String key, String value) throws IllegalArgumentException {
    validateRowIndex(row);
    validateContent(key);
    validateContent(value);
  }

  private void validateRowIndex(int row) throws IllegalArgumentException {
    if (row >= rows()) {
      throw new IllegalArgumentException("Row index out of bounds.");
    }

    if (row < 0) {
      throw new IllegalArgumentException("Negative row index not allowed.");
    }
  }

  private void validateColumnIndex(int col) throws IllegalArgumentException {
    if (col >= cols()) {
      throw new IllegalArgumentException("Column index out of bounds.");
    }

    if (col < 0) {
      throw new IllegalArgumentException("Negative column index not allowed.");
    }
  }

  private void validateContent(String content) throws IllegalArgumentException {
    for (char c : content.toCharArray()) {
      if (!domain.contains(c)) {
        String msg = "Input contains an illegal character ('" + c + "').";
        throw new IllegalArgumentException(msg);
      }
    }
  }
}