package com.mishinyura.calculator.utils;

import com.mishinyura.calculator.exceptions.CustomException;

import java.util.List;

/**
 * Class Validator.
 * Implements Validator.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 20.06.2022
 */
public class Validator {
    /**
     * Method checks if expression is valid.
     *
     * @param exp Expression.
     * @return boolean
     */
    public boolean isValidExpression(final String exp) {
        var parts = exp.split(" ");
        if (parts.length != 3) {
            throw new CustomException(Constants.MATH_FORMAT_EXCEPTION);
        }

        var operand = parts[1];
        if (!isOperandValid(operand)) {
            throw new CustomException(Constants.ILLEGAL_OPERAND_EXCEPTION);
        }

        var a = parts[0];
        var b = parts[2];
        if (!isArabic(a) && isArabic(b) || isArabic(a) && !isArabic(b)) {
            throw new CustomException(Constants.VAR_MISMATCH_EXCEPTION);
        }
        if (!isArabic(a) && !isArabic(b) && !isRoman(a) && !isRoman(b)) {
            throw new CustomException(Constants.VAR_MISMATCH_EXCEPTION);
        }
        if (!isRoman(a) && isRoman(b) || isRoman(a) && !isRoman(b)) {
            throw new CustomException(Constants.VAR_MISMATCH_EXCEPTION);
        }
        return true;
    }

    /**
     * Method checks if the string is arabic number.
     *
     * @param str String
     * @return boolean
     */
    public boolean isArabic(final String str) {
        return str.matches("[1-9]|10");
    }

    /**
     * Method checks if the string is roman number.
     *
     * @param str String
     * @return boolean
     */
    public boolean isRoman(final String str) {
        return List.of(
                "I", "II", "III", "IV", "V",
                "VI", "VII", "VIII", "IX", "X")
                .contains(str);
    }

    /**
     * Method checks if the operand is valid.
     *
     * @param operand Operand
     * @return boolean
     */
    public boolean isOperandValid(final String operand) {
        return operand.matches("[-+*/]");
    }
}
