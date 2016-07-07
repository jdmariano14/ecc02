package com.exist.ecc.matrix.model.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.exist.ecc.matrix.model.api.CharDomain;

public class NestedListCharMatrix extends AbstractCharMatrix {
  private List<List<List<String>>> data;

  public NestedListCharMatrix(CharDomain domain) {
    super(domain);
    data = new ArrayList();
  }

  public String getKey(int row, int col) throws IllegalArgumentException {
    super.getKey(row, col);
    return data.get(row).get(col).get(0);
  }

  public void setKey(int row, int col, String newKey) throws IllegalArgumentException {
    super.setKey(row, col, newKey);
    data.get(row).get(col).set(0, newKey);
  }

  public String getValue(int row, int col) throws IllegalArgumentException {
    super.getValue(row, col);
    return data.get(row).get(col).get(1);
  }

  public void setValue(int row, int col, String newValue) throws IllegalArgumentException {
    super.setValue(row, col, newValue);
    data.get(row).get(col).set(1, newValue);
  }

  public void put(int row, String key, String value) throws IllegalArgumentException {
    super.put(row, key, value);

    List<String> cell = new ArrayList(2);
    cell.add(key);
    cell.add(value);

    data.get(row).add(cell);
  }

  public int rows() {
    return data.size();
  }

  public int cols() {
    int cols = 0;

    if (!data.isEmpty()) {
      cols = data.get(0).size();
    }

    return cols;
  }

  public void addRow() {
    List<List<String>> row = new ArrayList();
    data.add(row);
  }

  public void sortRow(int row) {
    super.sortRow(row);
    // Collections.sort(data.get(row));
  }

  public void clear() {
    data.clear();
  }
}