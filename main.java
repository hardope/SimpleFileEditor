import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        System.out.println("Basic Command Line Interface (CLI) File I/O\n\n");

        System.out.println("1. Read file");
        System.out.println("2. Write to file");
        System.out.println("3. Exit\n");

        while (true) {
            System.out.print("Choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                String content = new ReadFile().read();
                System.out.println(content);
            } else if (choice == 2) {
                new WriteFile().write();
            } else if (choice == 3) {
                break;
            }
        }
    }
}

class ReadFile {

    String read() {
        try {
            String filename = new GetInput().getFilename();
            File file = new File(filename);
            String content = "";

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                content += scanner.nextLine() + "\n";
            }

            scanner.close();
            System.out.println();
            return content;
        } catch (Exception e) {
            System.out.println("File Not Found.\n");
            return "";
        }
    }
}

class WriteFile {

    boolean write() {
        try {
            String filename = new GetInput().getFilename();
            String content = new GetInput().getContent();
            boolean append = new GetInput().append();

            FileWriter fileWriter;

            if (append) {
                fileWriter = new FileWriter(filename, true);
            } else {
                fileWriter = new FileWriter(filename);
            }

            fileWriter.write(content + "\n");

            fileWriter.close();
            System.out.println("Successfully wrote to the file.\n");
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred.");
            return false;
        }
    }
}

class GetInput {
    String getFilename() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Filename: ");
        return scanner.nextLine();
    }

    String getContent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Content: ");
        return scanner.nextLine();
    }

    boolean append() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Append? (y/n): ");
        String choice = scanner.nextLine();
        return choice.equals("y");
    }
}