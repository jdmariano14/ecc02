import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Exercise2 {
  private static final Scanner INPUT_SCANNER;
  private static final String[] OPTIONS = {
      "search", "edit", "print", "add row", 
      "add cell", "sort", "reset", "exit"
    };

  static {
    INPUT_SCANNER = new Scanner(System.in);
  }

  public static void main(String [] args) {
    System.out.println("Welcome.");

    AsciiMatrixCell.setDomain(new AlphaCharDomain());

    AsciiMatrix matrix = initializeAsciiMatrixFromUserOption();
    printMatrixToConsole(matrix);

    int choice = -1;
    do {
      System.out.println(System.lineSeparator() + getMenu());
      choice = promptUserForInt("");

      if (choice < 1 || choice > OPTIONS.length) {
        continue;
      }

      switch (OPTIONS[choice - 1]) {
        case "search": 
          searchMatrix(matrix);
          break;
        case "edit": 
          editMatrixElement(matrix);
          break;
        case "print":
          printMatrixToConsole(matrix);
          break;
        case "add row":
          addRowToMatrix(matrix);
          break;
        case "add cell":
          addCellToMatrix(matrix);
          break;
        case "sort":
          sortMatrix(matrix);
          break;
        case "reset":
          matrix = resetMatrix(matrix);
          break;
      }
    } while (choice != OPTIONS.length);

    saveMatrix(matrix);

    INPUT_SCANNER.close();

    System.out.println("Goodbye.");

    System.exit(0);
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder("Select an option:");
    menu.append(System.lineSeparator());

    for (int index = 0; index < OPTIONS.length; index++) {
      menu.append((index + 1) + ". ");
      menu.append(OPTIONS[index]);
      if (index < OPTIONS.length - 1) {
        menu.append(System.lineSeparator());
      }
    }

    return menu.toString();
  }

  private static AsciiMatrix initializeAsciiMatrixFromUserOption() {
    AsciiMatrix matrix = null;

    do {
      StringBuilder prompt = new StringBuilder("Enter the path to read from");
      prompt.append(" (leave blank to initialize in console): ");

      String path = promptUserForLine(prompt.toString());

      if (path.isEmpty()) {
        matrix = initializeAsciiMatrixFromConsole();
      } else {
        try {
          matrix = new AsciiMatrix();
          matrix.setInputStrategy(new Utf8InputStrategy(path));
          matrix.initializeFromInput();
        } catch (IOException e) {
          matrix = null;
          System.err.println("Error accessing file. Matrix initializion aborted.");
        }
      }
    } while (matrix == null);

    return matrix;
  }

  private static AsciiMatrix initializeAsciiMatrixFromConsole() {
    AsciiMatrix matrix = null;

    do {
      matrix = new AsciiMatrix();
      int rows = promptUserForInt("Enter the number of rows: ");
      int cols = promptUserForInt("Enter the number of columns: ");

      try {
        matrix.autoFill(rows, cols);
      } catch (IllegalArgumentException e) {
        matrix = null;
        System.err.println(e.getMessage());
      }
    } while (matrix == null);

    return matrix;
  }

  private static void searchMatrix(AsciiMatrix matrix) {
    String query = promptUserForLine("Enter the query string: ");

    if (query.isEmpty()) {
      System.err.println("Blank query entered. Matrix search aborted.");
    } else {
      ArrayList results = matrix.getQueryOccurrences(query);  
      printSeachResults(results);
    }
  }

  private static void printSeachResults(ArrayList results) {
    for (int row = 0; row < results.size(); row++) {
      ArrayList rowResults = (ArrayList)results.get(row);

      for (int col = 0; col < rowResults.size(); col++) {
        int[] cellResults = (int[])rowResults.get(col);

        for (int ele = 0; ele < cellResults.length; ele++) {
          int occurrences = cellResults[ele];

          if (occurrences > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(row + "," + col + "," + ele);
            sb.append(" with " + occurrences + " occ.");

            System.out.println(sb.toString());
          }
        }
      }
    }
  }

  private static void editMatrixElement(AsciiMatrix matrix) {
    int row = promptUserForInt("Enter row index (all indices are 0-based): ");
    int col = promptUserForInt("Enter column index: ");
    int ele = promptUserForInt("Enter element index: ");
    String newVal = promptUserForLine("Enter the new value: ");

    try {
      matrix.get(row).get(col).set(ele, newVal);
    } catch (IndexOutOfBoundsException e) {
      System.err.println("Invalid index entered. Matrix update aborted.");
    } catch (IllegalArgumentException e) {
      System.err.println("Invalid character entered. Matrix update aborted.");
    }
  }

  private static void printMatrixToConsole(AsciiMatrix matrix) {
    matrix.setOutputStrategy(new ConsoleOutputStrategy());
    try {
      matrix.outputContents();
    } catch (Exception e) {

    }
  }

  private static void addRowToMatrix(AsciiMatrix matrix) {
    matrix.add(new AsciiMatrixRow());
    System.out.println("Row added. Matrix now has " + matrix.size() + " rows.");
  }

  private static void addCellToMatrix(AsciiMatrix matrix) {
    int row = promptUserForInt("Enter row index (all indices are 0-based): ");
    AsciiMatrixCell cell = new AsciiMatrixCell();

    try {
      for (int ctr = 0; ctr < AsciiMatrixConventions.DEFAULT_CELL_SIZE; ctr++) {
        String newVal = promptUserForLine("Enter a value for element " + ctr + ": ");
        cell.set(ctr, newVal);
      }

      matrix.get(row).add(cell);
    } catch (IndexOutOfBoundsException e) {
      System.err.println("Invalid index entered. Matrix update aborted.");
    } catch (IllegalArgumentException e) {
      System.err.println("Invalid character(s) entered. Matrix update aborted.");
    }
  }  

  private static void sortMatrix(AsciiMatrix matrix) {
    matrix.sort();
  }

  private static AsciiMatrix resetMatrix(AsciiMatrix matrix) {
    String src = null;

    if (matrix.getSource() != null) {
      src = matrix.getSource();
    }

    AsciiMatrix newMatrix = initializeAsciiMatrixFromConsole();

    if (src != null) {
      newMatrix.setSource(src);
    }

    return newMatrix;
  }

  private static void saveMatrix(AsciiMatrix matrix) {
    try {
      System.out.println("Saving matrix...");

      matrix.setOutputStrategy(new Utf8OutputStrategy(matrix.getSource()));
      matrix.outputContents();

      System.out.println("Matrix saved to " + matrix.getSource() + ".");
    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (NullPointerException e) {
      System.err.println("No output path could be determined. Matrix save aborted.");
    }
  }

  private static int promptUserForInt(String promptMsg) {
    int input = Integer.MIN_VALUE;

    do {
      try {
        input = Integer.parseInt(promptUserForLine(promptMsg));
      } catch (NumberFormatException e) {
        System.out.println("Please enter an integer value.");
      }
    } while (input == Integer.MIN_VALUE);

    return input;
  }

  private static String promptUserForLine(String promptMsg) {
    System.out.print(promptMsg);
    return INPUT_SCANNER.nextLine();
  }
}