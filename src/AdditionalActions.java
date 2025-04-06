import java.util.Scanner;

public class AdditionalActions {
    public void performActions() {
        ExternalFileHandler externalFileHandler = new ExternalFileHandler();
        System.out.println("Для просмотра истории введите 1");
        System.out.println("Для очистки истории введите 2");
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        if (Integer.parseInt(input) == 1) {
            externalFileHandler.readFile();
        }
        if (Integer.parseInt(input) == 2) {
            externalFileHandler.clearFile();
        }
    }
}
