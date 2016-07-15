package com.exist.ecc.matrix.model.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.collections4.map.LinkedMap;

import com.exist.ecc.matrix.model.api.CharDomain;

public class MapListCharMatrix extends AbstractCharMatrix {
  private List<LinkedMap<String, String>> data;

  public MapListCharMatrix(CharDomain domain) {
    super(domain);
    data = new ArrayList();
  }

  protected String getKeyImplementation(int row, int col) {
    return data.get(row).get(col);
  }

  protected void setKeyImplementation(int row, int col, String newKey) {
    String value = data.get(row).getValue(col);
    data.get(row).put(newKey, value);
  }

  protected String getValueImplementation(int row, int col) {
    return data.get(row).getValue(col);
  }

  protected void setValueImplementation(int row, int col, String newValue) {
    String key = data.get(row).get(col);
    data.get(row).put(key, newValue);
  }

  protected void putImplementation(int row, String key, String value) {
    data.get(row).put(key, value);
  }

  public int rows() {
    return data.size();
  }

  protected int colsImplementation(int row) {
    int cols = data.isEmpty() ? 0 : data.get(row).size();

    return cols;
  }

  public void addRow() {
    LinkedMap<String, String> row = new LinkedMap();
    data.add(row);
  }

  protected void sortRowImplementation(int row, boolean descending) {
    /*
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

    Collections.sort(data.get(row), order);*/
  }

  public void clear() {
    data.clear();
  }
}