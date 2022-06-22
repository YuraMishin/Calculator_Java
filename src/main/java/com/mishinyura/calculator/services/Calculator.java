package com.mishinyura.calculator.services;

import com.mishinyura.calculator.utils.Constants;
import com.mishinyura.calculator.utils.Converter;
import com.mishinyura.calculator.utils.Validator;
import com.mishinyura.calculator.exceptions.CustomException;

import java.util.Map;
import java.util.function.IntBinaryOperator;

/**
 * Class Calculator.
 * Implements Calculator.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 20.06.2022
 */
public class Calculator {
    /**
     * Math operands.
     */
    private final Map<String, IntBinaryOperator> operands;
    /**
     * Validator.
     */
    private final Validator validator;
    /**
     * Converter.
     */
    private final Converter converter;

    /**
     * Constructor.
     */
    public Calculator() {
        validator = new Validator();
        converter = new Converter();
        operands = Map.of(
                "+", Integer::sum,
                "-", (x, y) -> x - y,
                "*", (x, y) -> x * y,
                "/", (x, y) -> x / y);
    }

    /**
     * Method calculates the math expression.
     *
     * @param expression Expression
     * @return String Result
     */
    public String perform(final String expression) {
        validator.isValidExpression(expression);

        var words = expression.split(" ");
        var a = words[0];
        var operand = words[1];
        var b = words[2];
        var isResultArabic = true;

        if (validator.isRoman(a)) {
            a = String.valueOf(converter.toArabic(a));
            b = String.valueOf(converter.toArabic(b));
            isResultArabic = false;
        }

        var result = operands.get(operand).applyAsInt(
                Integer.parseInt(a), Integer.parseInt(b));

        if (isResultArabic) {
            return String.valueOf(result);
        } else {
            if (result < 1) {
                throw new CustomException(Constants.OUT_OF_ROMAN_EXCEPTION);
            }
            return converter.toRoman(result);
        }
    }
}
