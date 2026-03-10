public class TranspositionCipher {

    public static String encrypt(String text, String key) {

        int k = key.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += k) {

            String block = text.substring(i, Math.min(i + k, text.length()));

            while (block.length() < k)
                block += "x";

            for (int j = 0; j < k; j++) {

                int pos = key.charAt(j) - '1';
                result.append(block.charAt(pos));
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, String key) {

        int k = key.length();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += k) {

            String block = text.substring(i, i + k);
            char[] temp = new char[k];

            for (int j = 0; j < k; j++) {

                int pos = key.charAt(j) - '1';
                temp[pos] = block.charAt(j);
            }

            result.append(temp);
        }

        return result.toString();
    }
}