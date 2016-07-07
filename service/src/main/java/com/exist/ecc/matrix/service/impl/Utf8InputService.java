package com.exist.ecc.matrix.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.io.BufferedReader;

import java.util.Scanner;

import com.exist.ecc.matrix.model.api.CharMatrix;

import com.exist.ecc.matrix.service.api.CharMatrixInputService;

public class Utf8InputService implements CharMatrixInputService {
  private String filepath;

  public Utf8InputService(String filepath) throws IllegalArgumentException {
    try {
      this.filepath = Paths.get(filepath).toString();
    } catch (Exception e) {
      throw new IllegalArgumentException("Could not find or access file.");
    }
  }

  public String getInputPath() {
    return filepath;
  }

  public void setInputPath(String filepath) {
    try {
      this.filepath = Paths.get(filepath).toString();
    } catch (Exception e) {
      throw new IllegalArgumentException("Could not find or access file.");
    }
  }

  public void readMatrix(CharMatrix matrix) throws IOException {
    Path path = Paths.get(filepath);

    BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    Scanner fileScanner = new Scanner(reader);

    while (fileScanner.hasNext()) {
      matrix.clear();
      String nextMatrix = scanBetweenDelimters(fileScanner, "<matrix>", "</matrix>");
      parseMatrix(matrix, nextMatrix);
    }

    fileScanner.close();

    matrix.setSource(filepath);
  }

  private void parseMatrix(CharMatrix matrix, String input) {
    Scanner matrixScanner = new Scanner(input);

    int row = 0;

    while (matrixScanner.hasNext()) {
      String nextRow = scanBetweenDelimters(matrixScanner, "<row>", "</row>");

      if (!nextRow.isEmpty()) {
        matrix.addRow();
        parseMatrixRow(matrix, row, nextRow);
        row++;
      }
    }

    matrixScanner.close();
  }

  private void parseMatrixRow(CharMatrix matrix, int row, String input) {
    Scanner rowScanner = new Scanner(input);

    while (rowScanner.hasNext()) {
      String nextCell = scanBetweenDelimters(rowScanner, "<cell>", "</cell>");

      if (!nextCell.isEmpty()) {
        String[] cell = parseMatrixCell(nextCell);

        matrix.put(row, cell[0], cell[1]);
      }
    }

    rowScanner.close();
  }

  public String[] parseMatrixCell(String input) {
    String[] cell = new String[2];
    Scanner cellScanner = new Scanner(input);

    while (cellScanner.hasNext(getDelimiter("<key>", "</key>"))) {
      String nextKey = scanBetweenDelimters(cellScanner, "<key>", "</key>");

      if (!nextKey.isEmpty()) {
        cell[0] = unescapeTags(nextKey);
      }
    }

    while (cellScanner.hasNext(getDelimiter("<value>", "</value>"))) {
      String nextValue = scanBetweenDelimters(cellScanner, "<value>", "</value>");

      if (!nextValue.isEmpty()) {
        cell[1] = unescapeTags(nextValue);
      }
    }

    cellScanner.close();

    return cell;
  }

  private String unescapeTags(String str) {
    char escapeChar = 0x00FF;
    String unescape = str.replaceAll(escapeChar + "<" + escapeChar, "<")
                         .replaceAll(escapeChar + ">" + escapeChar, ">");
    return unescape;
  }

  private String scanBetweenDelimters(Scanner scanner, String opening, String closing) {
    scanner.useDelimiter(getDelimiter(opening, closing));

    String result = scanner.next();

    if (result.contains(closing)) {
      result = result.substring(0, result.indexOf(closing));
    }

    return result;
  }

  private String getDelimiter(String opening, String closing) {
    StringBuilder delimiter = new StringBuilder();
    delimiter.append("(" + closing + ")*");
    delimiter.append("\\s*");
    delimiter.append("(" + opening + ")+");

    return delimiter.toString();
  }
}