package com.exist.ecc.matrix.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import com.exist.ecc.matrix.model.api.CharMatrix;
import com.exist.ecc.matrix.model.api.CharDomain;

import com.exist.ecc.matrix.model.impl.NestedListCharMatrix;
import com.exist.ecc.matrix.model.impl.KeyboardCharDomain;

import com.exist.ecc.matrix.service.CharMatrixPopulateService;
import com.exist.ecc.matrix.service.CharMatrixSearchService;

import com.exist.ecc.matrix.service.impl.Utf8InputService;
import com.exist.ecc.matrix.service.impl.Utf8OutputService;

public class Exercise5 {

  public static void main(String[] args) {
    CharMatrix matrix = new NestedListCharMatrix(new KeyboardCharDomain());

    CharMatrixPopulateService populator = new CharMatrixPopulateService();

    populator.populate(matrix, 2, 2, 3);


    System.out.println(matrix);
    Utf8OutputService writer = new Utf8OutputService("output");

    try {
      writer.writeMatrix(matrix);
    } catch (Exception e) {
      
    }

    CharMatrix clone = new NestedListCharMatrix(new KeyboardCharDomain());

    Utf8InputService reader = new Utf8InputService("output");

    try {
      reader.readMatrix(clone);
    } catch (Exception e) {

    }

    System.out.println(clone);

  }
}