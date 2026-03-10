import java.io.*;

public class FileManager {

    public static String readFile(String path) {
        StringBuilder text = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return text.toString();
    }

    public static void writeFile(String path, String text) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            writer.write(text);

        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}