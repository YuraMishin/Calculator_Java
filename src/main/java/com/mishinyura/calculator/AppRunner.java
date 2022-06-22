package com.mishinyura.calculator;

import com.mishinyura.calculator.services.Calculator;

import java.util.Scanner;

/**
 * Class AppRunner.
 * Implements Application Runner.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 20.06.2022
 */
public final class AppRunner {
    /**
     * Constructor.
     */
    private AppRunner() {
    }

    /**
     * The entry point of the Java application.
     *
     * @param args Args
     */
    public static void main(final String[] args) {
        try (var scanner = new Scanner(System.in)) {
            System.out.println("Введите выражение:");
            System.out.println(new Calculator().perform(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
