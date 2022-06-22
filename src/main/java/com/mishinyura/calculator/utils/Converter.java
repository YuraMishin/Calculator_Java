package com.mishinyura.calculator.utils;

import java.util.Map;

/**
 * Class Converter.
 * Implements Converter.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 21.06.2022
 */
public class Converter {
    /**
     * Method converts roman number to arabic.
     *
     * @param romanNumber RomanNumber
     * @return int
     */
    public int toArabic(final String romanNumber) {
        Map<Character, Integer> valueMap = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000);
        int value = 0;
        int lastDigitValue = 0;
        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            char romanDigit = romanNumber.charAt(i);
            int digitvalue = valueMap.getOrDefault(romanDigit, 0);
            boolean addMode = digitvalue >= lastDigitValue;
            if (addMode) {
                value += digitvalue;
                lastDigitValue = digitvalue;
            } else {
                value -= digitvalue;
            }
        }
        return value;
    }

    /**
     * Method converts arabic number to roman.
     *
     * @param arabicNumber ArabicNumber
     * @return String
     */
    public String toRoman(final int arabicNumber) {
        var arabicNumberIn = arabicNumber;

        int[] values = {
                1000, 900, 500, 400, 100,
                90, 50, 40, 10, 9,
                5, 4, 1
        };
        String[] romanLetters = {
                "M", "CM", "D", "CD", "C",
                "XC", "L", "XL", "X", "IX",
                "V", "IV", "I"
        };
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (arabicNumberIn >= values[i]) {
                arabicNumberIn = arabicNumberIn - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }
}
