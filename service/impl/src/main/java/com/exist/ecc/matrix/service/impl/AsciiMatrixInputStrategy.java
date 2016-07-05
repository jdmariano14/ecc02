package com.exist.ecc.matrix.service.impl;

import java.io.IOException;

import com.exist.ecc.matrix.model.impl.AsciiMatrix;

public interface AsciiMatrixInputStrategy {
  public void readMatrix(AsciiMatrix matrix) throws IOException;
}