import java.util.Scanner;
import java.io.IOException;

public class Exercise2 {
  private static final Scanner INPUT_SCANNER;
  private static final String[] OPTIONS = {
      "search", "edit", "print", "add row", 
      "add cell", "sort", "exit"
    };

  static {
    INPUT_SCANNER = new Scanner(System.in);
  }

  public static void main(String [] args) {
    System.out.println("Welcome.");

    AsciiMatrix matrix = initializeAsciiMatrixFromUserOption();
    printMatrixToConsole(matrix);

    int choice = -1;
    do {
      System.out.println(System.lineSeparator() + getMenu());
      choice = promptUserForInt("");

      switch (OPTIONS[choice - 1]) {
        case "search": 
          //searchMatrix(matrix);
          break;
        case "edit": 
          //editMatrixCell(matrix);
          break;
        case "print":
          printMatrixToConsole(matrix);
          break;
        case "add row":
          //matrix = initializeAsciiMatrix();
          break; 
      }
    } while (choice != OPTIONS.length);

    System.out.println("Goodbye.");

    INPUT_SCANNER.close();
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

  private static void printMatrixToConsole(AsciiMatrix matrix) {
    matrix.setOutputStrategy(new ConsoleOutputStrategy());
    try {
      matrix.outputContents();
    } catch (Exception e) {

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

/*
  // ****************************************
  // Main
  // ****************************************
  public static void main(String args[]) {
    AsciiMatrix matrix = initializeAsciiMatrix();

    int choice = 0;
    do {
      System.out.println();
      System.out.println(getMenu());

      choice = promptUserForPostiveInt("");

      switch (choice) {
        case 1: 
          searchMatrix(matrix);
          break;
        case 2: 
          editMatrixCell(matrix);
          break;
        case 3: 
          System.out.println(matrix);
          break;
        case 4:
          matrix = initializeAsciiMatrix();
          break; 
      }
    } while (choice != 5);

    System.out.println("Goodbye.");
  }
  // ****************************************
  // Menu
  // ****************************************
  // ****************************************
  // Search matrix
  // ****************************************
  private static void searchMatrix(AsciiMatrix m) {
    String query = promptUserForString("Enter the search query: ");
    System.out.println(m.getSearchResults(query));
  }
  // ****************************************
  // Initialize a matrix
  // ****************************************
  private static AsciiMatrix initializeAsciiMatrix() {
    int rows = promptUserForPostiveInt("Enter the number of rows: ");
    int cols = promptUserForPostiveInt("Enter the number of columns: ");

    AsciiMatrix matrix = new AsciiMatrix(rows, cols);

    System.out.println();
    System.out.println(matrix);

    return matrix;
  }
  // ****************************************
  // Edit a cell
  // ****************************************
  private static void editMatrixCell(AsciiMatrix m) {
    int row = promptUserForNonNegativeInt("Enter row (first is 0): ");
    int col = promptUserForNonNegativeInt("Enter column (first is 0): ");
    String newVal = promptUserForString("Enter the new value: ");

    m.set(row, col, newVal);
  }
  // ****************************************
  // Prompts
  // ****************************************
  private static int promptUserForNonNegativeInt(String promptMsg) {
    return promptUserForIntWithLowerBound(promptMsg, 0);
  }

  private static int promptUserForPostiveInt(String promptMsg) {
    return promptUserForIntWithLowerBound(promptMsg, 1);
  }

  private static int promptUserForIntWithLowerBound(String promptMsg, int bound) {    
    System.out.print(promptMsg);

    int input;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      input = Integer.parseInt(reader.readLine());
      
      if (input < bound) {
        input = bound;
      }
    }
    catch (IOException | NumberFormatException e) {
      input = bound;
    }

    return input;
  }*/
}