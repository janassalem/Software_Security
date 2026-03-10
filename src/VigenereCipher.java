public class VigenereCipher {

    public static String encrypt(String text, String key) {

        StringBuilder result = new StringBuilder();
        int j = 0;

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);

            if (Character.isLetter(c)) {

                int p = Character.toLowerCase(c) - 'a';
                int k = Character.toLowerCase(key.charAt(j % key.length())) - 'a';

                char encrypted = (char)((p + k) % 26 + 'a');

                result.append(encrypted);
                j++;
            }
            else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, String key) {

        StringBuilder result = new StringBuilder();
        int j = 0;

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);

            if (Character.isLetter(c)) {

                int cVal = Character.toLowerCase(c) - 'a';
                int k = Character.toLowerCase(key.charAt(j % key.length())) - 'a';

                char decrypted = (char)((cVal - k + 26) % 26 + 'a');

                result.append(decrypted);
                j++;
            }
            else {
                result.append(c);
            }
        }

        return result.toString();
    }
}