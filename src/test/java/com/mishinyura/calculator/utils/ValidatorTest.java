package com.mishinyura.calculator.utils;

import com.mishinyura.calculator.exceptions.CustomException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

/**
 * Class ValidatorTest.
 * Tests com.mishinyura.calculator.utils.Validator class.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 20.06.2022
 */
class ValidatorTest {
    private static Validator validator;

    @BeforeAll
    static void setup() {
        validator = new Validator();
    }

    /**
     * Tests isValidExpression() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "1 + 2 + 3"
    })
    void shouldThrowExceptionWhenCheckValidExpression(String s) {
        // when-then
        var thrown = catchThrowable(() -> validator.isValidExpression(s));

        assertThat(thrown)
                .isInstanceOf(CustomException.class)
                .hasMessage(Constants.MATH_FORMAT_EXCEPTION);
    }

    /**
     * Tests isValidExpression() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "1 ++ 2",
            "1 ) 2",
            "1 % 2"
    })
    void shouldThrowExceptionWhenCheckValidExpression2(String s) {
        // when-then
        var thrown = catchThrowable(() -> validator.isValidExpression(s));

        assertThat(thrown)
                .isInstanceOf(CustomException.class)
                .hasMessage(Constants.ILLEGAL_OPERAND_EXCEPTION);
    }

    /**
     * Tests isValidExpression() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "1 + X",
            "V + 3",
            "1 + 11",
            "0 + 9",
            "11 + 12",
            "I + XI",
            "XI + XII"
    })
    void shouldThrowExceptionWhenCheckValidExpression3(String s) {
        // when-then
        var thrown = catchThrowable(() -> validator.isValidExpression(s));

        assertThat(thrown)
                .isInstanceOf(CustomException.class)
                .hasMessage(Constants.VAR_MISMATCH_EXCEPTION);
    }

    /**
     * Tests isValidExpression() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "1 + 2",
            "V + I"
    })
    void shouldTrueWhenCheckValidExpression4(String str) {
        // when
        var actual = validator.isValidExpression(str);

        // then
        assertThat(actual).isTrue();
    }

    /**
     * Tests isArabic() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1", "3", "10"})
    void shouldTrueWhenCheckArabic(String s) {
        // when
        var actual = validator.isArabic(s);

        // then
        assertThat(actual).isTrue();
    }

    /**
     * Tests isArabic() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "11", "100"})
    void shouldFalseWhenCheckArabicOutOfRange(String s) {
        // when
        var actual = validator.isArabic(s);

        // then
        assertThat(actual).isFalse();
    }

    /**
     * Tests isArabic() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"aa", "fgf", "t434", "fgrt"})
    void shouldTrueWhenCheckNonArabic(String s) {
        // when
        var actual = validator.isArabic(s);

        // then
        assertThat(actual).isFalse();
    }

    /**
     * Tests isRoman() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X"})
    void shouldTrueWhenCheckRoman(String s) {
        // when
        var actual = validator.isRoman(s);

        // then
        assertThat(actual).isTrue();
    }

    /**
     * Tests isRoman() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"XI", "XX", "L"})
    void shouldFalseWhenCheckRomanOutOfRange(String s) {
        // when
        var actual = validator.isRoman(s);

        // then
        assertThat(actual).isFalse();
    }

    /**
     * Tests isOperandValid() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "/"})
    void shouldTrueWhenCheckOperand(String s) {
        // when
        var actual = validator.isOperandValid(s);

        // then
        assertThat(actual).isTrue();
    }

    /**
     * Tests isOperandValid() method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"+++", "---", "*gtt", "/555"})
    void shouldFalseWhenCheckOperand(String s) {
        // when
        var actual = validator.isOperandValid(s);

        // then
        assertThat(actual).isFalse();
    }
}
