import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input file: ");
        String inputFile = scanner.nextLine();

        System.out.print("Output file: ");
        String outputFile = scanner.nextLine();

        String text = FileManager.readFile(inputFile);

        System.out.println("Choose cipher");
        System.out.println("1 Monoalphabetic");
        System.out.println("2 Vigenere");
        System.out.println("3 Transposition");
        System.out.println("4 DES");

        int cipher = scanner.nextInt();

        System.out.println("1 Encrypt\n2 Decrypt");
        int mode = scanner.nextInt();

        scanner.nextLine();

        String result = "";

        if (cipher == 1) {

            System.out.print("Enter key: ");
            String key = scanner.nextLine();

            if (mode == 1)
                result = MonoalphabeticCipher.encrypt(text, key);
            else
                result = MonoalphabeticCipher.decrypt(text, key);
        }

        else if (cipher == 2) {

            System.out.print("Enter key: ");
            String key = scanner.nextLine();

            if (mode == 1)
                result = VigenereCipher.encrypt(text, key);
            else
                result = VigenereCipher.decrypt(text, key);
        }

        else if (cipher == 3) {

            System.out.print("Enter key: ");
            String key = scanner.nextLine();

            if (mode == 1)
                result = TranspositionCipher.encrypt(text, key);
            else
                result = TranspositionCipher.decrypt(text, key);
        }

        else if (cipher == 4) {

            System.out.print("Enter 64-bit plaintext in HEX: ");
            String plaintextInput = scanner.nextLine();

            System.out.print("Enter 64-bit key in HEX: ");
            String keyInput = scanner.nextLine();

            long plaintext = Long.parseUnsignedLong(plaintextInput, 16);
            long key = Long.parseUnsignedLong(keyInput, 16);

            long output;

            if (mode == 1)
                output = DES.encrypt(plaintext, key);
            else
                output = DES.decrypt(plaintext, key);

            result = String.format("%016X", output);
        }

        FileManager.writeFile(outputFile, result);

        System.out.println("Operation completed.");
    }
}