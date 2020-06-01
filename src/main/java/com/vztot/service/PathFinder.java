package com.vztot.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {

    public void drawMatrix(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(Arrays.toString(matrix[i]));
            if (i != matrix.length - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private void recursiveSearch(Deque<Cell> path, int depth, String word, char[][] matrix) {
        if (path.size() == word.length()) {
            return;
        }
        List<Cell> possibleWays = getAdjacentCellsWithValueOf(path.peek(),
                word.charAt(depth + 1), matrix);
        if (possibleWays.size() == 0) {
            path.poll();
            depth--;
        }
        depth++;
        for (Cell possibleWay : possibleWays) {
            path.push(possibleWay);
            recursiveSearch(path, depth, word, matrix);
            if (path.size() == word.length()) {
                return;
            }
        }
    }

    public String findPath(char[][] matrix, String word) {
        Deque<Cell> path = new ArrayDeque<>();
        for (Cell root : findRootCell(word.charAt(0), matrix)) {
            path.clear();
            path.push(root);
            recursiveSearch(path, 0, word, matrix);
            if (path.size() == word.length()) {
                break;
            }
        }

        int size = path.size();
        if (size == word.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    sb.append(" ");
                }
                sb.append(path.pollLast());
                if (i != size - 1) {
                    sb.append("->");
                }
            }
            return sb.toString();
        }
        return "Solution not found.";
    }

    private List<Cell> findRootCell(char c, char[][] matrix) {
        List<Cell> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == c) {
                    list.add(new Cell(i, j, matrix[i][j]));
                }
            }
        }
        return list;
    }

    private List<Cell> getAdjacentCellsWithValueOf(Cell cell, char c, char[][] matrix) {
        List<Cell> result = new ArrayList<>();
        if (matrix.length > 0) {
            if (cell.cordI > 0) {
                result.add(new Cell(cell.cordI - 1, cell.cordJ,
                        matrix[cell.cordI - 1][cell.cordJ]));
            }
            if (cell.cordI != matrix.length - 1) {
                result.add(new Cell(cell.cordI + 1, cell.cordJ,
                        matrix[cell.cordI + 1][cell.cordJ]));
            }
        }
        if (matrix[0].length > 0) {
            if (cell.cordJ > 0) {
                result.add(new Cell(cell.cordI, cell.cordJ - 1,
                        matrix[cell.cordI][cell.cordJ - 1]));
            }
            if (cell.cordJ != matrix[0].length - 1) {
                result.add(new Cell(cell.cordI, cell.cordJ + 1,
                        matrix[cell.cordI][cell.cordJ + 1]));
            }
        }
        return result.stream()
                .filter(e -> e.value == c)
                .collect(Collectors.toList());
    }

    private class Cell {
        private int cordI;
        private int cordJ;
        private char value;

        public Cell(int i, int j, char value) {
            this.cordI = i;
            this.cordJ = j;
            this.value = value;
        }

        @Override
        public String toString() {
            return value + "[" + cordI + "," + cordJ + "]";
        }
    }
}
