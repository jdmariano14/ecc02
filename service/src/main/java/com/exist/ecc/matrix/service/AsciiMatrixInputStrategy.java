package com.exist.ecc.matrix.service;

import java.io.IOException;

import com.exist.ecc.matrix.model.AsciiMatrix;

public interface AsciiMatrixInputStrategy {
  public void readMatrix(AsciiMatrix matrix) throws IOException;
}