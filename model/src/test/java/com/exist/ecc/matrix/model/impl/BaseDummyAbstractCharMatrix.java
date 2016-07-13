package com.exist.ecc.matrix.model.impl;

import java.nio.file.Path;

import com.exist.ecc.matrix.model.api.CharDomain;

public class BaseDummyAbstractCharMatrix extends AbstractCharMatrix {
  public BaseDummyAbstractCharMatrix(CharDomain domain) {
    super(domain);
  }
  
  public CharDomain getDomain() {
    return null;
  }

  public Path getSource() {
    return null;
  }

  public void setSource(Path newSource) { 

  }

  protected String getKeyImplementation(int row, int col) {
    return new String();
  }

  protected void setKeyImplementation(int row, int col, String newKey){

  }

  protected String getValueImplementation(int row, int col) {
    return new String(); 
  }

  protected void setValueImplementation(int row, int col, String newValue) {

  }

  protected void putImplementation(int row, String key, String value) {

  }

  public int rows() {
    return 0;
  }

  protected int colsImplementation(int row) {
    return 0;
  }

  public void addRow() {

  }

  protected void sortRowImplementation(int row, boolean descending) {

  }
  
  public void clear() {

  }
}