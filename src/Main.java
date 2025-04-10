import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение для вычисления");
        System.out.println("Список поддерживаемых символов: 0-9, +, -, *, /, ^, (, )");
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        Converter converter = new Converter();
        Calculator calculator = new Calculator();
        ExternalFileHandler externalFileHandler = new ExternalFileHandler();
        AdditionalActions additionalActions = new AdditionalActions();
        int calculationResult;

        if (validateTheInput(input)) {
            calculationResult = calculator.calculateRPN(converter.convertToRPN(input));
            System.out.println(calculationResult);
            externalFileHandler.saveToHistory(input, calculationResult);
        }

        System.out.println("Требуются ли дополнительные действия?");
        System.out.println("Введите: Да / Нет");

        String addActions = console.nextLine();
        if (addActions.equals("Да")) {
            additionalActions.performActions();
        }
    }

    private static boolean validateTheInput(String input) {
        if (input.isEmpty()) {
            System.out.println("Выражение для вычисления не введено.");
            System.out.println();
            return false;
        }

        if (!input.matches("[\\d()*/^+-]+")) {
            System.out.println("В выражении для вычисления имеется неподдерживаемый символ.");
            System.out.println("Список поддерживаемых символов: 0-9, +, -, *, /, ^, (, )");
            System.out.println();
            return false;
        }

        Pattern pattern = Pattern.compile("\\([\\d*/^+-]*\\)");
        Matcher matcher = pattern.matcher(input);
        do {
            input = matcher.replaceAll("");
            matcher = pattern.matcher(input);
        } while (matcher.find());
        if (!input.matches("[\\d*/^+-]*")) {
            System.out.println("В выражении для вычисления неправильное количество открытых и закрытых скобок.");
            System.out.println();
            return false;
        }

        return true;
    }
}