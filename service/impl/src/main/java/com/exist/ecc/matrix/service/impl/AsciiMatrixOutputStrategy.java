package com.exist.ecc.matrix.service.impl;

import java.io.IOException;

import com.exist.ecc.matrix.model.impl.AsciiMatrix;

public interface AsciiMatrixOutputStrategy {
  public void writeMatrix(AsciiMatrix matrix) throws IOException;
}