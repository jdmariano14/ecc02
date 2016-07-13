package com.exist.ecc.matrix.model.api;

import java.nio.file.Path;

public class BaseDummyCharMatrix implements CharMatrix {
  
  public CharDomain getDomain() {
    return null;
  }

  public Path getSource() {
    return null;
  }

  public void setSource(Path newSource) { 

  }

  public String getKey(int row, int col) throws IllegalArgumentException {
    return new String();
  }

  public void setKey(int row, int col, String newKey) throws IllegalArgumentException {

  }

  public String getValue(int row, int col) throws IllegalArgumentException {
    return new String(); 
  }

  public void setValue(int row, int col, String newValue) throws IllegalArgumentException {

  }

  public void put(int row, String key, String value) throws IllegalArgumentException {

  }

  public int rows() {
    return 0;
  }

  public int cols(int row) throws IllegalArgumentException {
    return 0;
  }

  public void addRow() {

  }

  public void sortRow(int row, boolean descending) throws IllegalArgumentException {

  }
  
  public void clear() {

  }
}