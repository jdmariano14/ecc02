package com.exist.ecc.matrix.service.api;

import java.io.IOException;

import com.exist.ecc.matrix.model.api.CharMatrix;

public interface CharMatrixOutputService {
  public void writeMatrix(CharMatrix matrix) throws IOException;
}