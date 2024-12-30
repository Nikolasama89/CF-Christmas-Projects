package gr.aueb.cf.project1;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static int[] readFileNumbers(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            int count = 0;
            int num;

            int[] inputNumbers = new int[49];

            while ((num = scanner.nextInt()) != -1 && count <= 48) {
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

    public static boolean isEven(int[] arr, int maxEvens) {
        int evensCount = 0;

        for(int number : arr) {
            if (number % 2 == 0) evensCount++;
        }
        return evensCount > maxEvens;
    }

    public static boolean isOdd(int[] arr, int maxOdds) {
        int oddsCount = 0;

        for (int number : arr) {
            if (number % 2 != 0) oddsCount++;
        }
        return oddsCount > maxOdds;
    }

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
