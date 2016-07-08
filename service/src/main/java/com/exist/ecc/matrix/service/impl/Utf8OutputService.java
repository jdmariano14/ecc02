package com.exist.ecc.matrix.service.impl;

import java.util.List;
import java.util.LinkedList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import java.io.IOException;

import com.exist.ecc.matrix.model.api.CharMatrix;

import com.exist.ecc.matrix.service.api.CharMatrixOutputService;

public class Utf8OutputService implements CharMatrixOutputService {
  private String filepath;

  public Utf8OutputService(String filepath) {
    this.filepath = filepath;
  }

  public String getOutputPath() {
    return filepath;
  }

  public void setOutputPath(String filepath) {
    this.filepath = filepath;
  }

  public void writeMatrix(CharMatrix matrix) throws IOException {
    Path path = Paths.get(filepath);
    List<String> lines = new LinkedList();

    lines.add("<matrix>");

    for (int row = 0; row < matrix.rows(); row++) {
      lines.add("\t<row>");

      for (int col = 0; col < matrix.cols(); col++) {
        lines.add("\t\t<cell>");
        lines.add("\t\t\t<key>" + escapeTags(matrix.getKey(row, col)) + "</key>");
        lines.add("\t\t\t<value>" + escapeTags(matrix.getValue(row, col)) + "</value>");
        lines.add("\t\t</cell>");
      }

      lines.add("\t</row>");
    }

    lines.add("</matrix>");

    Files.write(path, lines, StandardCharsets.UTF_8);
  }

  private String escapeTags(String str) {
    char escapeChar = 0x00FF;
    String escape = str.replaceAll("<", escapeChar + "<" + escapeChar)
                       .replaceAll(">", escapeChar + ">" + escapeChar);
    return escape;
  }
}