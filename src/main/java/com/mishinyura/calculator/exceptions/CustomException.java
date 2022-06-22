package com.mishinyura.calculator.exceptions;

/**
 * Class CustomException.
 * Implements Custom Exception.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 21.06.2022
 */
public class CustomException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message Message
     */
    public CustomException(final String message) {
        super(message);
    }
}
