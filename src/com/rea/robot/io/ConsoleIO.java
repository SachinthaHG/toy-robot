package com.rea.robot.io;

import java.util.Scanner;

/**
 * This class has implemented the InputOutput interface to read from and write to console
 */
public class ConsoleIO implements InputOutput {
    private Scanner scanner;

    private ConsoleIO() {
        scanner = new Scanner(System.in);
    }

    private final static class CosoleIOHelper {
        private final static ConsoleIO instance = new ConsoleIO();
    }

    public static ConsoleIO getInstance() {
        return CosoleIOHelper.instance;
    }

    /**
     * Read from console
     */
    @Override
    public String read() {
        return scanner.nextLine();
    }

    /**
     * Write to console
     */
    @Override
    public void write(String line) {
        System.out.println(line);
    }

    public void close() {
        scanner.close();
    }
}
