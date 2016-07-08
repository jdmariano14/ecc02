package com.exist.ecc.matrix.service.impl;

import java.util.List;
import java.util.LinkedList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.stream.Stream;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    Stream<String> lines = Files.lines(Paths.get(filepath));

    String wholeFile = lines.reduce("", (a, b) -> a + System.lineSeparator() + b);

    List<String> matrixStrings = getElements(wholeFile, "matrix");

    for (String matrixString : matrixStrings) {
      matrix.clear();
      parseMatrix(matrix, matrixString);
    }
  }

  private void parseMatrix(CharMatrix matrix, String matrixString) {
    int rowNum = 0;

    List<String> rowStrings = getElements(matrixString, "row");

    for (String rowString : rowStrings) {
      matrix.addRow();
      parseMatrixRow(matrix, rowNum, rowString);
      rowNum++;
    }
  }

  private void parseMatrixRow(CharMatrix matrix, int row, String rowString) {
    List<String> cellStrings = getElements(rowString, "cell");
    
    for (String cellString : cellStrings) {
      String key = unescapeTags(getElements(cellString, "key").get(0));
      String value = unescapeTags(getElements(cellString, "value").get(0));

      matrix.put(row, key, value);
    }
  }

  private List<String> getElements(String input, String elementName) {
    List<String> elements = new LinkedList();

    String openingTag = "<" + elementName + ">";
    String closingTag = "</" + elementName + ">";

    Pattern pattern = Pattern.compile(enclosedByTags(openingTag, closingTag), Pattern.DOTALL);

    Matcher matcher = pattern.matcher(input);

    while (matcher.find()) {
      int startPos = matcher.start() + openingTag.length();
      int endPos = matcher.end() - closingTag.length();

      elements.add(input.substring(startPos, endPos));
    }

    return elements;
  }

  private String enclosedByTags(String openingTag, String closingTag) {
    return openingTag + ".*?" + closingTag;
  }

  private String unescapeTags(String str) {
    char escapeChar = 0x00FF;
    String unescape = str.replaceAll(escapeChar + "<" + escapeChar, "<")
                         .replaceAll(escapeChar + ">" + escapeChar, ">");
    return unescape;
  }
}