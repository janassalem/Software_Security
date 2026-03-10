public class MonoalphabeticCipher {

    public static String encrypt(String text, String key) {

        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {

            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                result.append(key.charAt(c - 'a'));
            }
            else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, String key) {

        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {

            if (Character.isLetter(c)) {

                int index = key.indexOf(Character.toLowerCase(c));
                result.append((char)(index + 'a'));
            }
            else {
                result.append(c);
            }
        }

        return result.toString();
    }
}