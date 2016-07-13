  package com.exist.ecc.matrix.model.impl;

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
    validateRowIndex(row);
    validateColumnIndex(row, col);

    return "";
  }

  public void setKey(int row, int col, String newKey) throws IllegalArgumentException {
    validateRowIndex(row);
    validateColumnIndex(row, col);
    validateContent(newKey);
  }

  public String getValue(int row, int col) throws IllegalArgumentException {
    validateRowIndex(row);
    validateColumnIndex(row, col);

    return "";
  }

  public void setValue(int row, int col, String newValue) throws IllegalArgumentException {
    validateRowIndex(row);
    validateColumnIndex(row, col);
    validateContent(newValue);
  }

  public void put(int row, String key, String value) throws IllegalArgumentException {
    validateRowIndex(row);
    validateContent(key);
    validateContent(value);
  }

  public int cols(int row) throws IllegalArgumentException {
    validateRowIndex(row);

    return 0;
  }

  public void sortRow(int row) throws IllegalArgumentException {
    validateRowIndex(row);
  }

  public void sortRow(int row, boolean descending) throws IllegalArgumentException {
    validateRowIndex(row);
  }

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

  private void validateRowIndex(int row) throws IllegalArgumentException {
    if (row >= rows()) {
      throw new IllegalArgumentException("row index out of bounds");
    }

    if (row < 0) {
      throw new IllegalArgumentException("negative row index not allowed");
    }
  }

  private void validateColumnIndex(int row, int col) throws IllegalArgumentException {
    if (col >= cols(row)) {
      throw new IllegalArgumentException("column index out of bounds");
    }

    if (col < 0) {
      throw new IllegalArgumentException("negative column index not allowed");
    }
  }

  private void validateContent(String content) throws IllegalArgumentException {
    for (char c : content.toCharArray()) {
      if (!domain.contains(c)) {
        String msg = "input contains an illegal character ('" + c + "')";
        throw new IllegalArgumentException(msg);
      }
    }
  }
}