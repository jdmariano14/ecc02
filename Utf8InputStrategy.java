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

  public void readMatrix(AsciiMatrix matrix) {
    Path path = Paths.get(filepath);

    try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      Scanner scanner = new Scanner(reader);
      matrix.clear();

      while(scanner.hasNextLine()) {
        matrix.add(parseMatrixRow(scanner.nextLine()));
      }
    }
    catch (IOException e) {
      matrix = new AsciiMatrix();
      System.err.println("Error accessing file. Empty matrix initialized.");
    }
  }

  private AsciiMatrixRow parseMatrixRow(String input) {
    input = input.trim();

    StringBuilder regex = new StringBuilder();
    regex.append("(\\s*[");
    regex.append(AsciiMatrixConventions.TEXT_CELL_DELIMITER);
    regex.append("]+\\s*)");

    String[] tokens = input.split(regex.toString());

    AsciiMatrixRow row = new AsciiMatrixRow();

    for (String token : tokens) {
      row.add(parseMatrixCell(token));
    }

    return row;
  }

  private AsciiMatrixCell parseMatrixCell(String input) {
    input = input.trim();

    StringBuilder regex = new StringBuilder();
    regex.append("(\\s*[");
    regex.append(AsciiMatrixConventions.TEXT_ELEMENT_DELIMITER);
    regex.append("]+\\s*)");

    String[] tokens = input.split(regex.toString());

    AsciiMatrixCell cell = new AsciiMatrixCell(tokens.length);

    for (int index = 0; index < tokens.length; index++) {
      cell.set(index, tokens[index]);
    }

    return cell;
  }
}