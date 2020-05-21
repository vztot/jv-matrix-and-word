package com.vztot.controller;

import com.vztot.service.PathFinder;
import java.util.Scanner;

public class ConsoleHandler {

    public ConsoleHandler() {
        handle();
    }

    public void handle() {
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = null;
        String word = null;
        while (true) {
            if (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();

                if (matrix == null) {
                    matrix = buildMatrixFromString(inputLine);
                } else if (word == null) {
                    word = validateAndGetWord(inputLine, matrix.length);
                }

                if (matrix != null && word != null) {
                    PathFinder nautilus = new PathFinder(matrix, word);
                    System.out.println("Matrix:");
                    nautilus.drawMatrix();
                    System.out.println("\nResult:");
                    System.out.println(nautilus.findPath());
                    System.exit(0);
                }
            }
        }
    }

    private char[][] buildMatrixFromString(String matrixString) {
        int len = matrixString.length();
        if (len > 0 && Math.sqrt(len) % 1 == 0) {
            int size = (int) Math.sqrt(matrixString.length());
            char[][] matrix = new char[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = matrixString.charAt(i * size + j);
                }
            }
            return matrix;
        }
        throw new RuntimeException("Entered string for matrix is not valid.");
    }

    private String validateAndGetWord(String wordString, int countMatrixElements) {
        if (wordString.length() > 0 && wordString.length() <= Math.pow(countMatrixElements, 2)) {
            return wordString;
        }
        throw new RuntimeException("Entered word is not valid.");
    }
}
