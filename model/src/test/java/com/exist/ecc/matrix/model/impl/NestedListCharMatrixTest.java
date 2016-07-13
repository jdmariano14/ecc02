package com.exist.ecc.matrix.model.impl;

import org.junit.Before;

import com.exist.ecc.matrix.model.api.CharMatrixLogicTest;

public class NestedListCharMatrixTest extends CharMatrixLogicTest {
  @Before
  public void before() {
    matrix = new NestedListCharMatrix(getDefaultCharDomain());
  }
}