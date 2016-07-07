package com.exist.ecc.matrix.service;

import java.io.IOException;

import com.exist.ecc.matrix.model.AsciiMatrix;

public interface AsciiMatrixOutputStrategy {
  public void writeMatrix(AsciiMatrix matrix) throws IOException;
}