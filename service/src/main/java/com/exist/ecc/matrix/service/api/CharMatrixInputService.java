package com.exist.ecc.matrix.service.api;

import java.io.IOException;

import com.exist.ecc.matrix.model.api.CharMatrix;

public interface CharMatrixInputService {
  public void readMatrix(CharMatrix matrix) throws IOException;
}