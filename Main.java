import java.util.HashMap;

class Main {
    public static String calc(String input) {
        String[] values = input.split(" ");

        if (values.length != 3) {
            throw new IllegalArgumentException("Неверный формат данных");
        }

        String operand1 = values[0];
        String operator = values[1];
        String operand2 = values[2];

        boolean isArabic = operand1.matches("[0-9]+") && operand2.matches("[0-9]+");
        boolean isRoman = operand1.matches("[IVXLCDM]+") && operand2.matches("[IVXLCDM]+");

        if (isArabic && isRoman || (!isArabic && !isRoman)) {
            throw new IllegalArgumentException("Неверный формат данных");
        }

        int a, b;
        if (isArabic) {

            a = Integer.parseInt(operand1);
            b = Integer.parseInt(operand2);

        } else {
            RomanConverter romanConverter = new RomanConverter();
            a = romanConverter.romanToInteger(operand1);
            b = romanConverter.romanToInteger(operand2);
        }

        if ((a < 0 || a > 10) || (b < 0 || b > 10)) {
            throw new IllegalArgumentException("Числа не находятся в диапазоне между 1 и 10 включительно");
        }

        int result;
        switch (operator) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
            default -> throw new IllegalArgumentException("Неверный формат данных");
        }

        if (isArabic) {
            return String.valueOf(result);
        } else {
            if (result <= 0) {
                throw new IllegalArgumentException("Римские цифры не могут быть меньше 1");
            }
            return new RomanConverter().integerToRoman(result);
        }
    }

}

class RomanConverter {
    final HashMap<Character, Integer> romanValues;

    RomanConverter() {
        romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);
    }

    int romanToInteger(String roman) {
        int result = 0;
        int prev = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanValues.get(roman.charAt(i));

            if (value < prev) {
                result -= value;
            } else {
                result += value;
            }

            prev = value;
        }

        return result;
    }

    String integerToRoman(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
        }

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < romanValues.size(); i++) {
            char romanChar = (char) romanValues.keySet().toArray()[i];
            int value = romanValues.get(romanChar);

            while (num >= value) {
                roman.append(romanChar);
                num -= value;
            }

            if (num == 0) break;
        }

        return roman.toString();
    }
}





