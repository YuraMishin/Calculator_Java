package com.mishinyura.calculator.services;

import com.mishinyura.calculator.exceptions.CustomException;
import com.mishinyura.calculator.utils.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

/**
 * Class CalculatorTest.
 * Tests com.mishinyura.calculator.services.Calculator class.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 20.06.2022
 */
class CalculatorTest {
    private static Calculator calculator;

    @BeforeAll
    static void setup() {
        calculator = new Calculator();
    }

    /**
     * Tests perform() method.
     */
    @ParameterizedTest
    @MethodSource("provideStringsForPerform")
    void shouldTrueWhenPerform(String expression, String expected) {
        // when
        var actual = calculator.perform(expression);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForPerform() {
        return Stream.of(
                Arguments.of("1 + 2", "3"),
                Arguments.of("1 - 1", "0"),
                Arguments.of("1 - 2", "-1"),
                Arguments.of("1 * 2", "2"),
                Arguments.of("1 / 2", "0"),
                Arguments.of("1 / 1", "1"),
                Arguments.of("4 / 2", "2"),
                Arguments.of("10 / 9", "1"),
                Arguments.of("X + X", "XX"),
                Arguments.of("X - IX", "I")
        );
    }

    /**
     * Tests perform() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "I - I",
            "I - X"
    })
    void shouldThrowExceptionWhenPerform(String expression) {
        // when
        var thrown = catchThrowable(() -> calculator.perform(expression));

        // then
        assertThat(thrown)
                .isInstanceOf(CustomException.class)
                .hasMessage(Constants.OUT_OF_ROMAN_EXCEPTION);
    }
}
