import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reads lines of space-separated integers from an input file, sorts each line
 * using selection sort, and writes the sorted lines to an output file.
 *
 * @author  Carel Gumuyire
 * @version 1.0
 * @since   2026-11-25
 */
public final class SelectSort {

    /**
     * Private constructor to prevent instantiation.
     *
     * @throws IllegalStateException Utility class
     */
    private SelectSort() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Sorts an array of integers in ascending order using selection sort.
     *
     * @param array The array of integers to sort.
     * @return The sorted array of integers.
     */
    public static int[] selectionSort(final int[] array) {
        for (int index = 0; index < array.length - 1; index++) {
            int minIndex = index;

            for (int search = index + 1; search < array.length; search++) {
                if (array[search] < array[minIndex]) {
                    minIndex = search;
                }
            }

            if (minIndex != index) {
                final int temp = array[index];
                array[index] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        return array;
    }

    /**
     * Reads space-separated lines from input.txt, sorts each line,
     * and writes the results to output.txt.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(final String[] args) {
        final File inputFile = new File("input.txt");

        try (Scanner scanner = new Scanner(inputFile);
             FileWriter writer = new FileWriter("output.txt")) {

            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                final String[] parts = line.split("\\s+");
                final int[] numbers = new int[parts.length];

                for (int index = 0; index < parts.length; index++) {
                    numbers[index] = Integer.parseInt(parts[index]);
                }

                final int[] sortedNumbers = selectionSort(numbers);

                for (int index = 0; index < sortedNumbers.length; index++) {
                    writer.write(String.valueOf(sortedNumbers[index]));
                    if (index < sortedNumbers.length - 1) {
                        writer.write(" ");
                    }
                }
                writer.write(System.lineSeparator());
            }
            System.out.println("Sorting complete. Check output.txt.");

        } catch (IOException | NumberFormatException error) {
            System.out.println("Error processing file: " + error.getMessage());
        }
    }
}
