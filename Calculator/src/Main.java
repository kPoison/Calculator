import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(calc(line));
    }

    public static String calc(String input) throws IOException {
        int result, firstNumber, secondNumber;
        String output;
        String[] expressionElements = input.split(" ");

        if (expressionElements.length > 3) {
            throw new IOException();
        }

        try {
            firstNumber = toIntNumber(expressionElements[0]);
            secondNumber = toIntNumber(expressionElements[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IOException();
        }

        boolean isFirstNumberRoman = isValidRomanNumber(expressionElements[0]),
                isSecondNumberRoman = isValidRomanNumber(expressionElements[2]);

        if (isFirstNumberRoman != isSecondNumberRoman) {
            throw new IOException();
        }

        switch (expressionElements[1]) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> result = firstNumber / secondNumber;
            default -> throw new IOException();
        }

        if (isFirstNumberRoman) {
            if (result < 1) {
                throw new ArithmeticException();
            }
            output = RomanNumber.values()[result - 1].toString();
        } else {
            output = String.valueOf(result);
        }

        return output;
    }

    static int toIntNumber(String inputNumber) throws IOException {
        int number;

        if (isValidRomanNumber(inputNumber)) {
            number = RomanNumber.valueOf(inputNumber).ordinal() + 1;
        } else if (isValidArabNumber(inputNumber)) {
            number = Integer.parseInt(inputNumber);
        } else {
            throw new IOException();
        }

        return number;
    }
    static boolean isValidRomanNumber(String inputNumber) {
        int number = 0;

        try {
            number = RomanNumber.valueOf(inputNumber).ordinal() + 1;
        } catch (IllegalArgumentException ignored) {}

        return number > 0 && number <= 10;
    }

    static boolean isValidArabNumber(String inputNumber) {
        int number = 0;

        try {
            number = Integer.parseInt(inputNumber);
        } catch (NumberFormatException ignored) {}

        return number > 0 && number <= 10;
    }
}