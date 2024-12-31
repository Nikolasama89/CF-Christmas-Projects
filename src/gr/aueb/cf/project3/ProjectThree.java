package gr.aueb.cf.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProjectThree {

    public static void main(String[] args) {


    }

    public static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
