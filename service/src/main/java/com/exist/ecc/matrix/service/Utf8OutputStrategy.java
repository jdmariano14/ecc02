package com.exist.ecc.matrix.service;

import java.util.List;
import java.util.LinkedList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

import com.exist.ecc.matrix.model.*;

public class Utf8OutputStrategy implements AsciiMatrixOutputStrategy {
  private String filepath;

  public Utf8OutputStrategy(String filepath) {
    this.filepath = filepath;
  }

  public void writeMatrix(AsciiMatrix matrix) throws IOException {
    Path path = Paths.get(filepath);
    List<String> lines = new LinkedList();

    lines.add("<matrix>");

    for (AsciiMatrixRow row : matrix) {
      lines.add("<row>");

      for (AsciiMatrixCell cell : row) {
        lines.add("<cell>");

        for (String element : cell) {
          lines.add("<element>" +  escapeTags(element) + "</element>");
        }

        lines.add("</cell>");
      }

      lines.add("</row>");
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