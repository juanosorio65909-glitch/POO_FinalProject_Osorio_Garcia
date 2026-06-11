package ui;

import java.util.Scanner;

/**
 * Provides basic console input and output operations for the user interface.
 */
public class Console {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Prints a message followed by a line break.
     */
    public static void writeLine(Object message) {
        System.out.println(message);
    }

    /**
     * Prints a prompt and reads a complete line from the console.
     */
    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    /**
     * Reads an integer value with validation.
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException error) {
                writeLine("- You must enter an integer number.");
            }
        }
    }

    /**
     * Reads a long value with validation.
     */
    public static long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(readLine(prompt));
            } catch (NumberFormatException error) {
                writeLine("- You must enter an integer number.");
            }
        }
    }

    /**
     * Reads a double value with validation.
     */
    public static double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(prompt));
            } catch (NumberFormatException error) {
                writeLine("- You must enter an integer or decimal number.");
            }
        }
    }
}
