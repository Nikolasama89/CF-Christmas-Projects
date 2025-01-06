package gr.aueb.cf.project1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ProjectOne {

    public static void main(String[] args) {
        int[] numbers = readFileNumbers("C:\\Users\\niqos\\Desktop\\cfnumbers.txt");
        int[][] validCombos = generateSixers(numbers);
        writeToFile(validCombos, "C:\\Users\\niqos\\Desktop\\valid_combos.txt");

    }

    /**
     * Reads from a file until found -1 or reaching maximum limit (49)
     * Numbers returned sorted.
     *
     * @param file  Path to the file with the numbers.
     * @return      Returns the numbers sorted to ascending order
     *              Returns empty array in case of error
     */
    public static int[] readFileNumbers(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            int count = 0;
            int num;

            int[] inputNumbers = new int[49];

            while ((num = scanner.nextInt()) != -1 && count < 49) {
                inputNumbers[count++] = num;
            }
            int[] numbers = Arrays.copyOf(inputNumbers, count);
            Arrays.sort(numbers);

            return numbers;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new int[0];
    }

    /**
     *  Checks if the array has more than the maximum allowed even numbers.
     *
     * @param arr           The array of numbers to check.
     * @param maxEvens      The maximum allowed even numbers
     * @return              True if the Array has more than the allowed numbers, false otherwise.
     */
    public static boolean isEven(int[] arr, int maxEvens) {
        int evensCount = 0;

        for(int number : arr) {
            if (number % 2 == 0) evensCount++;
        }
        return evensCount > maxEvens;
    }

    /**
     *  Checks if the array has more than the maximum allowed odd numbers.
     *
     * @param arr           The array of numbers to check.
     * @param maxEvens      The maximum allowed odd numbers
     * @return              True if the Array has more than the allowed numbers, false otherwise.
     */
    public static boolean isOdd(int[] arr, int maxOdds) {
        int oddsCount = 0;

        for (int number : arr) {
            if (number % 2 != 0) oddsCount++;
        }
        return oddsCount > maxOdds;
    }

    /**
     *  Checks the array if it has more than two contiguous numbers.
     *
     * @param arr       The array of numbers to check.
     * @return          Returns true if it has more than two numbers.
     */
    public static boolean isContiguous(int[] arr) {
        int consecutiveCount = 1;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1] - 1) {
                consecutiveCount++;
                if (consecutiveCount > 2) return true;
            } else {
                consecutiveCount = 1;
            }
        }
        return false;
    }

    /**
     *  Checks if more than 3 numbers in the array has the same ending.
     *
     * @param arr       The array of numbers to check
     * @return          Returns true if it has more than 3 numbers with same ending otherwise false
     */
    public static boolean isSameEnding(int[] arr) {
        int[] endings = new int[10];

        for (int num : arr) {
            endings[num % 10]++;
        }
        for (int ending : endings) {
            if (ending > 3) return true;
        }
        return false;
    }

    /**
     *  Checks if more than 3 numbers in the array belong to the same ten group
     *
     * @param arr       The array of numbers to check
     * @return          Returns true if more than three numbers otherwise false
     */

    public static boolean isSameTen(int[] arr) {
        int[] ten = new int[5];

        for (int num : arr) {
            ten[num / 10]++;
        }
        for (int num : ten) {
            if (num > 3) return true;
        }
        return false;
    }

    /**
     *  Generates all valid 6 numbers combinations from the input array.
     *  Combinations must have these requirements:
     *  - No more than 4 even numbers
     *  - No more than 4 odd numbers
     *  - No more than two consecutive numbers
     *  - No more than three numbers with same last digit
     *  - No more than three numbers from same tens group
     *
     * @param numbers       Array of numbers to generate combos
     * @return              2d array each row is a valid combo
     */

    public static int[][] generateSixers(int[] numbers) {
        int[][] validSixers = new int[50000][6];
        int count = 0;

        for (int i = 0; i < numbers.length - 5; i++) {
            for (int j = i + 1; j < numbers.length - 4; j++) {
                for (int k = j + 1; k < numbers.length - 3; k++) {
                    for (int l = k + 1; l < numbers.length - 2; l++) {
                        for (int m = l + 1; m < numbers.length - 1; m++) {
                            for (int n = m + 1; n < numbers.length; n++) {
                                int[] combination = {numbers[i], numbers[j], numbers[k], numbers[l], numbers[m], numbers[n]};
                                if (!isEven(combination, 4) && !isOdd(combination, 4) && !isContiguous(combination)
                                        && !isSameEnding(combination) && !isSameTen(combination)) {
                                    validSixers[count++] = combination;
                                }
                            }
                        }
                    }
                }
            }
        }
        return Arrays.copyOf(validSixers, count);
    }

    /**
     * Writes the valid combinations to a specified output file.
     * Every combination is written to a new line.
     *
     * @param validCombos   the 2d array of valid combos to write
     * @param fileName      Path to the output file
     */
    public static void writeToFile(int[][] validCombos, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int[] combo : validCombos) {
                writer.write(Arrays.toString(combo)+ "\n");
            }
            System.out.println("Combinations are ready...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
