package com.exist.ecc.matrix.model.api;

public interface CharMatrix {

  public abstract String getSource();

  public abstract void setSource(String newSource) throws IllegalArgumentException;

  public abstract String getKey(int row, int col) throws IllegalArgumentException;

  public abstract void setKey(int row, int col, String newKey) throws IllegalArgumentException;

  public abstract String getValue(int row, int col) throws IllegalArgumentException;

  public abstract void setValue(int row, int col, String newValue) throws IllegalArgumentException;

  public abstract void put(int row, String key, String value) throws IllegalArgumentException;

  public abstract int rows();

  public abstract int cols();

  public abstract void addRow();

  public abstract void clear();

}