import java.util.Stack;

public class Converter {
    public String convertToRPN (String input) {
        int priorityOfAnElementInTheStack;
        int priorityOfTheCurrentElement;

        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            Character character = input.charAt(i);
            if (Character.isDigit(character)) {
                stringBuilder.append(character);
                continue;
            }
            if (stack.empty()) {
                stack.push(character);
                stringBuilder.append(" ");
                continue;
            }

            if (character.toString().equals("(")) {
                stack.push(character);
                continue;
            }
            if (character.toString().equals(")")) {
                while (!stack.peek().toString().equals("(")) {
                    stringBuilder.append(" ");
                    stringBuilder.append(stack.pop());
                }
                stack.pop();
            } else {
                priorityOfAnElementInTheStack = getPriority(String.valueOf(stack.peek()));
                priorityOfTheCurrentElement = getPriority(String.valueOf(character));
                if (priorityOfAnElementInTheStack >= priorityOfTheCurrentElement & !character.toString().equals("^")) {
                    stringBuilder.append(" ");
                    stringBuilder.append(stack.pop());
                    stringBuilder.append(" ");
                    stack.push(character);
                } else {
                    stack.push(character);
                    stringBuilder.append(" ");
                }
            }
        }
        while (!stack.empty()) {
            stringBuilder.append(" ");
            stringBuilder.append(stack.pop());
        }

        return stringBuilder.toString();
    }

    private static int getPriority(String operator) {
        int priority = 0;
        switch (operator) {
            case "^":
                priority =4;
                break;
            case "*":
            case "/":
                priority = 3;
                break;
            case "+":
            case "-":
                priority = 2;
                break;
        }

        return priority;
    }
}
