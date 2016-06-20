import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner;

public class Utf8InputStrategy implements AsciiMatrixInputStrategy {
  private String filepath;

  public Utf8InputStrategy(String filepath) {
    this.filepath = filepath;
  }

  public void readMatrix(AsciiMatrix matrix) throws IOException {
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

  private void parseMatrix(AsciiMatrix matrix, String input) {
    Scanner matrixScanner = new Scanner(input);

    while (matrixScanner.hasNext()) {
      String nextRow = scanBetweenDelimters(matrixScanner, "<row>", "</row>");

      if (!nextRow.isEmpty()) {
        matrix.add(parseMatrixRow(nextRow));
      }
    }

    matrixScanner.close();
  }

  private AsciiMatrixRow parseMatrixRow(String input) {
    AsciiMatrixRow row = new AsciiMatrixRow();
    Scanner rowScanner = new Scanner(input);

    while (rowScanner.hasNext()) {
      String nextCell = scanBetweenDelimters(rowScanner, "<cell>", "</cell>");

      if (!nextCell.isEmpty()) {
        row.add(parseMatrixCell(nextCell));
      }
    }

    rowScanner.close();

    return row;
  }

  private AsciiMatrixCell parseMatrixCell(String input) {
    AsciiMatrixCell cell = new AsciiMatrixCell();
    Scanner cellScanner = new Scanner(input);

    while (cellScanner.hasNext()) {
      String nextElement = scanBetweenDelimters(cellScanner, "<element>", "</element>");

      if (!nextElement.isEmpty()) {
        cell.add(nextElement);
      }
    }

    cellScanner.close();

    return cell;
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