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
  private static final Scanner INPUT_SCANNER;
  private static final String[] OPTIONS = {
      "search", "edit", "print",
      "add row", "add cell", "sort",
      "reset", "save", "exit"
    };

  private static boolean dirty = false;

  static {
    INPUT_SCANNER = new Scanner(System.in);
  }

  public static void main(String [] args) {
    System.out.println("Welcome.");

    CharMatrix matrix = initializeCharMatrixFromUserOption();

    System.out.println(matrix);

    int choice = -1;
    for (;;) {
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
          System.out.println(matrix);
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
        case "save":
          saveMatrixAs(matrix);
          break;
        case "exit":
          confirmExit();
          break;
      }
    }
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

  private static CharMatrix initializeCharMatrixFromUserOption() {
    CharMatrix matrix = null;

    do {
      StringBuilder prompt = new StringBuilder("Enter the path to read from");
      prompt.append(" (leave blank to initialize in console): ");

      String path = promptUserForLine(prompt.toString());

      if (path.isEmpty()) {
        matrix = initializeCharMatrixFromConsole();
      } else {
        matrix = initializeMatrixFromFile(path);
      }
    } while (matrix == null);

    return matrix;
  }

  private static CharMatrix initializeMatrixFromFile(String path) {
    CharMatrix matrix = null;

    try {
      matrix = new NestedListCharMatrix(new KeyboardCharDomain());
      Utf8InputService utf8reader = new Utf8InputService(path);
      utf8reader.readMatrix(matrix);
    } catch (IOException e) {
      matrix = null;
      System.err.println("Error accessing file. Matrix initializion aborted.");
    } catch (IllegalArgumentException e) {
      matrix = null;
      System.err.println("Incompatible character domain used in file. Matrix initialization aborted.");
    }

    return matrix;
  }

  private static CharMatrix initializeCharMatrixFromConsole() {
    CharMatrix matrix = null;

    do {
      matrix = new NestedListCharMatrix(new KeyboardCharDomain());
      int rows = promptUserForInt("Enter the number of rows: ");
      int cols = promptUserForInt("Enter the number of columns: ");

      try {
        CharMatrixPopulateService populator = new CharMatrixPopulateService();
        populator.populate(matrix, rows, cols, 3);
      } catch (IllegalArgumentException e) {
        matrix = null;
        System.err.println(e.getMessage());
      }
    } while (matrix == null);

    return matrix;
  }

  private static void searchMatrix(CharMatrix matrix) {
    String query = promptUserForLine("Enter the query string: ");

    if (query.isEmpty()) {
      System.err.println("Blank query entered. Matrix search aborted.");
    } else {
      CharMatrixSearchService searcher = new CharMatrixSearchService();
      List<int[][]> results = searcher.getQueryOccurrences(matrix, query);
      printSeachResults(results);
    }
  }

  private static void printSeachResults(List<int[][]> results) {
    for (int row = 0; row < results.size(); row++) {
      for (int col = 0; col < results.get(row).length; col++) {
        for (int ele = 0; ele < results.get(row)[col].length; ele++) {
          int occurrences = results.get(row)[col][ele];

          if (occurrences > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(row + "," + col + " (" + ((ele > 0) ? "value" : "key") + ")");
            sb.append(" with " + occurrences + " occ.");

            System.out.println(sb.toString());
          }
        }
      }
    }
  }

  private static void editMatrixElement(CharMatrix matrix) {
    int row = promptUserForInt("Enter row index (all indices are 0-based): ");
    int col = promptUserForInt("Enter column index: ");
    String choice = promptUserForLine("Edit key or value? (k/v): ");
    String newKV = promptUserForLine("Enter the new content: ");

    try {
      if (choice.toLowerCase().equals("k")) {
        matrix.setKey(row, col, newKV);
      } else {
        matrix.setValue(row, col, newKV);
      }
      dirty = true;
      System.out.println("Matrix updated successfully.");
    } catch (IllegalArgumentException e) {
      System.err.println("Error: " + e.getMessage() + ". Matrix update aborted.");
    }
  }

  private static void addRowToMatrix(CharMatrix matrix) {
    matrix.addRow();
    dirty = true;
    System.out.println("Row added. Matrix now has " + matrix.rows() + " rows.");
  }

  private static void addCellToMatrix(CharMatrix matrix) {
    int row = promptUserForInt("Enter row index (all indices are 0-based): ");

    try {
      String key = promptUserForLine("Enter the key: ");
      String value = promptUserForLine("Enter the value: ");

      matrix.put(row, key, value);

      dirty = true;
      //int cells = matrix.get(row).size();
      //System.out.println("Cell added. Row " + row + " now has " + cells + " cells.");
    } catch (IllegalArgumentException e) {
      System.err.println("Error: " + e.getMessage() + ". Matrix update aborted.");
    }
  }  

  private static void sortMatrix(CharMatrix matrix) {
    int row = promptUserForInt("Enter row index (all indices are 0-based): ");
    String order = promptUserForLine("Enter asc or desc (default: asc): ");

    try {
      if (order.isEmpty()) {
        matrix.sortRow(row);
      } else if (!order.equals("desc")) {
        matrix.sortRow(row);
      } else {
        // TODO descending sort
        // matrix.sortRowDescending(row);
      }
      
      dirty = true;
      System.out.println("Matrix sorted successfully.");
    } catch (IllegalArgumentException e) {
      System.err.println("Invalid index entered. Matrix sort aborted.");
    }
  }

  private static CharMatrix resetMatrix(CharMatrix matrix) {
    CharMatrix newMatrix = null;

    if (matrix.getSource() == null) {
      newMatrix = initializeCharMatrixFromConsole();
    } else {
      newMatrix = initializeMatrixFromFile(matrix.getSource());
    }

    if (newMatrix == null) {
      newMatrix = matrix;
      System.out.println("Matrix reset failed.");
    } else {
      System.out.println("Matrix reset successfully.");
    }

    return newMatrix;
  }

  private static void saveMatrixAs(CharMatrix matrix) {
    String path = "";

    if (matrix.getSource() == null) {
      path = promptUserForLine("Enter the filename (leave blank to abort): ");

      if (!path.isEmpty()) {
        saveMatrix(matrix, path);
      }
    } else {
      path = promptUserForLine("Enter the filename (" + matrix.getSource() + "): ");

      if (path.isEmpty()) {
        saveMatrix(matrix);
      } else {
        saveMatrix(matrix, path);
      }
    }
  } 

  private static void saveMatrix(CharMatrix matrix, String path) {
    try {
      System.out.println("Saving matrix...");

      Utf8OutputService output = new Utf8OutputService(path);
      output.writeMatrix(matrix);

      dirty = false;
      System.out.println("Matrix saved to " + path + ".");
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }

  private static void saveMatrix(CharMatrix matrix) {
    try {
      saveMatrix(matrix, matrix.getSource());
    }
    catch (NullPointerException e) {
      System.err.println("Output path could not be determined. Matrix save aborted."); 
    }
  }

  private static void confirmExit() {
    if (dirty) {
      String confirm = promptUserForLine("You have unsaved changes. Continue to exit? (y/n) ");

      if (!confirm.toLowerCase().equals("y")) {
        return;
      }
    }

    INPUT_SCANNER.close();
    System.out.println("Goodbye.");
    System.exit(0);
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