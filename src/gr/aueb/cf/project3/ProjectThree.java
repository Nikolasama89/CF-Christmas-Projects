package gr.aueb.cf.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ProjectThree {

    public static void main(String[] args) {
        int[][] characters =  readFile("C:\\Users\\niqos\\Desktop\\testing.txt");
        printResults(characters);

    }

    public static int[][] readFile(String fileName) {
        int[][] characters = new int[128][2];
        int count = 0;


        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = reader.read()) != -1) {
                char character = Character.toLowerCase((char) c);
                if (!Character.isWhitespace(character) && Character.isLetter(character)) {
                    boolean found = false;

                    for (int i = 0; i < count; i++) {
                        if (characters[i][0] == character) {
                            characters[i][1]++;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        if (count < 128) {
                            characters[count][0] = character;
                            characters[count][1] = 1;
                            count++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.copyOfRange(characters, 0, count);
    }

    public static void printResults(int[][] arr) {
        //Arrays.sort(arr, (a, b)-> Character.compare((char) a[0], (char) b[0]));

        Arrays.sort(arr, (a, b)-> Integer.compare(b[1], a[1]));

        for (int i = 0; i < arr.length; i++) {
            if (arr[i][1] > 0) {
                System.out.println((char) arr[i][0] + " : " + arr[i][1]);
            }
        }
    }
}