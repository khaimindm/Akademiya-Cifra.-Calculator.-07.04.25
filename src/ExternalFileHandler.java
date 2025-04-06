import java.io.*;
import java.util.Scanner;

public class ExternalFileHandler {
    public void saveToHistory (String expression, int calculationResult) {
        try {
            FileWriter writer = new FileWriter("history", true);
            writer.write(expression + " = " + calculationResult);
            writer.write(System.getProperty( "line.separator" ));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл. Возможно файл не существует в корне каталога.");
        }
    }

    public void readFile() {
        File file = new File("history");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла. Возможно файл не существует в корне каталога.");
        }
    }

    public void clearFile() {
        try {
            PrintWriter writer = new PrintWriter("history");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла. Возможно файл не существует в корне каталога.");
        }
    }
}
