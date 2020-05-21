package com.vztot;

import com.vztot.controller.ConsoleHandler;

public class Main {
    public static void main(String[] args) {
        System.out.print("\nWelcome to the jv-matrix-and-word homework!\n"
                + "Please enter two strings:\n"
                + "  1. String of size N^2, that describes square matrix of characters N*N.\n"
                + "  2. String that describes the given word.\n\n");
        ConsoleHandler console = new ConsoleHandler();
    }
}
