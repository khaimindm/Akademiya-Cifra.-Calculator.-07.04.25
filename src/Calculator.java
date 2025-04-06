import java.util.Stack;

public class Calculator {
    public int calculateRPN(String notation) {
        Integer operationResult;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < notation.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            Character character = notation.charAt(i);
            if (Character.isDigit((character))) {
                Character nextChar = notation.charAt(i + 1);
                stringBuilder.append(notation.charAt(i));
                while (!nextChar.toString().equals(" ")) {
                    stringBuilder.append(notation.charAt(i + 1));
                    i++;
                    nextChar = notation.charAt(i+1);
                }
                String result = stringBuilder.toString();
                stack.push(Integer.parseInt(result));
                continue;
            }

            if (!Character.toString(notation.charAt(i)).equals(" ")) {
                operationResult = performAnAction(stack.pop(), stack.pop(), Character.toString(character));
                stack.push(operationResult);
            }
        }

        return stack.pop();
    }

    public static Integer performAnAction(int secondVariable, int firstVariable, String action) {
        int result;
        switch (action) {
            case "+":
                result = firstVariable + secondVariable;
                break;
            case "-":
                result = firstVariable - secondVariable;
                break;
            case "*":
                result = firstVariable * secondVariable;
                break;
            case "/":
                result = firstVariable / secondVariable;
                break;
            case "^":
                result = (int)Math.pow(firstVariable, secondVariable);
                break;
            default:
                result = 0;
        }

        return result;
    }
}
