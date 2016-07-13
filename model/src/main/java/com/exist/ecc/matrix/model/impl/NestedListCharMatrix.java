package com.exist.ecc.matrix.model.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.exist.ecc.matrix.model.api.CharDomain;

public class NestedListCharMatrix extends AbstractCharMatrix {
  private List<List<List<String>>> data;

  public NestedListCharMatrix(CharDomain domain) {
    super(domain);
    data = new ArrayList();
  }

  protected String getKeyImplementation(int row, int col) {
    return data.get(row).get(col).get(0);
  }

  protected void setKeyImplementation(int row, int col, String newKey) {
    data.get(row).get(col).set(0, newKey);
  }

  protected String getValueImplementation(int row, int col) {
    return data.get(row).get(col).get(1);
  }

  protected void setValueImplementation(int row, int col, String newValue) {
    data.get(row).get(col).set(1, newValue);
  }

  protected void putImplementation(int row, String key, String value) {
    List<String> cell = new ArrayList(2);
    cell.add(key);
    cell.add(value);

    data.get(row).add(cell);
  }

  public int rows() {
    return data.size();
  }

  protected int colsImplementation(int row) {
    int cols = data.isEmpty() ? 0 : data.get(row).size();

    return cols;
  }

  public void addRow() {
    List<List<String>> row = new ArrayList();
    data.add(row);
  }

  protected void sortRowImplementation(int row, boolean descending) {
    Comparator<List<String>> order;

    if (descending) {
      order = (List<String> cell1, List<String> cell2) -> {
        String cell1Concat = cell1.get(0) + cell1.get(1);
        String cell2Concat = cell2.get(0) + cell2.get(1);
        return cell2Concat.compareToIgnoreCase(cell1Concat);
      };
    } else {
      order = (List<String> cell1, List<String> cell2) -> {
        String cell1Concat = cell1.get(0) + cell1.get(1);
        String cell2Concat = cell2.get(0) + cell2.get(1);
        return cell1Concat.compareToIgnoreCase(cell2Concat);
      };
    }

    Collections.sort(data.get(row), order);
  }

  public void clear() {
    data.clear();
  }
}