package com.exist.ecc.matrix.model.api;

import java.nio.file.Path;

public interface CharMatrix {

  public abstract CharDomain getDomain();

  public abstract Path getSource();

  public abstract void setSource(Path newSource);

  public abstract String getKey(int row, int col) throws IllegalArgumentException;

  public abstract void setKey(int row, int col, String newKey) throws IllegalArgumentException;

  public abstract String getValue(int row, int col) throws IllegalArgumentException;

  public abstract void setValue(int row, int col, String newValue) throws IllegalArgumentException;

  public abstract void put(int row, String key, String value) throws IllegalArgumentException;

  public abstract int rows();

  public abstract int cols(int row);

  public abstract void addRow();

  public abstract void sortRow(int row) throws IllegalArgumentException;

  public abstract void sortRow(int row, boolean descending) throws IllegalArgumentException;

  public abstract void clear();

}