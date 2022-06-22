package com.mishinyura.calculator.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class ConverterTest.
 * Tests com.mishinyura.calculator.utils.Converter class.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 21.06.2022
 */
class ConverterTest {
    private static Converter converter;

    @BeforeAll
    static void setup() {
        converter = new Converter();
    }

    /**
     * Tests toArabic() method.
     */
    @ParameterizedTest
    @MethodSource("provideStringsForToArabic")
    void shouldTrueWhenToArabic(String input, int expected) {
        // when
        var actual = converter.toArabic(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForToArabic() {
        return Stream.of(
                Arguments.of("I", 1),
                Arguments.of("MCMLXXXVI", 1986),
                Arguments.of("MMXXII", 2022)
        );
    }

    /**
     * Tests toArabic() method.
     */
    @ParameterizedTest
    @MethodSource("provideStringsForToRoman")
    void shouldTrueWhenToRoman(int input, String expected) {
        // when
        var actual = converter.toRoman(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStringsForToRoman() {
        return Stream.of(
                Arguments.of(1, "I"),
                Arguments.of(1986, "MCMLXXXVI"),
                Arguments.of(2022, "MMXXII")
        );
    }
}
