public class DES {

    private static final int[] IP = {
            58,50,42,34,26,18,10,2,
            60,52,44,36,28,20,12,4,
            62,54,46,38,30,22,14,6,
            64,56,48,40,32,24,16,8,
            57,49,41,33,25,17, 9,1,
            59,51,43,35,27,19,11,3,
            61,53,45,37,29,21,13,5,
            63,55,47,39,31,23,15,7
    };

    private static final int[] IP_INV = {
            40,8,48,16,56,24,64,32,
            39,7,47,15,55,23,63,31,
            38,6,46,14,54,22,62,30,
            37,5,45,13,53,21,61,29,
            36,4,44,12,52,20,60,28,
            35,3,43,11,51,19,59,27,
            34,2,42,10,50,18,58,26,
            33,1,41, 9,49,17,57,25
    };

    private static final int[] E = {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9,10,11,12,13,
            12,13,14,15,16,17,
            16,17,18,19,20,21,
            20,21,22,23,24,25,
            24,25,26,27,28,29,
            28,29,30,31,32, 1
    };


    private static final int[] P = {
            16, 7,20,21,29,12,28,17,
            1,15,23,26, 5,18,31,10,
            2, 8,24,14,32,27, 3, 9,
            19,13,30, 6,22,11, 4,25
    };

    private static final int[] PC1 = {
            57,49,41,33,25,17, 9,
            1,58,50,42,34,26,18,
            10, 2,59,51,43,35,27,
            19,11, 3,60,52,44,36,
            63,55,47,39,31,23,15,
            7,62,54,46,38,30,22,
            14, 6,61,53,45,37,29,
            21,13, 5,28,20,12, 4
    };

    private static final int[] PC2 = {
            14,17,11,24, 1, 5,
            3,28,15, 6,21,10,
            23,19,12, 4,26, 8,
            16, 7,27,20,13, 2,
            41,52,31,37,47,55,
            30,40,51,45,33,48,
            44,49,39,56,34,53,
            46,42,50,36,29,32
    };


    private static final int[] SHIFTS = {
            1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1
    };

    private static final int[][][] S_BOXES = {
            // S1
            {{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                    {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                    {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                    {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},
            // S2
            {{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                    {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                    {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                    {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}},
            // S3
            {{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                    {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                    {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                    {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},
            // S4
            {{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                    {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                    {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                    {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},
            // S5
            {{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                    {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                    {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                    {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},
            // S6
            {{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                    {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
                    {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                    {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},
            // S7
            {{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                    {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                    {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                    {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},
            // S8
            {{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                    {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                    {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                    {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}}
    };

    // ══════════════════════════════════════════════════════
    //  HELPER METHODS
    // ══════════════════════════════════════════════════════

    private static int[] longToBits(long value, int length) {
        int[] bits = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            bits[i] = (int)(value & 1);  // grab the rightmost bit
            value >>= 1;                 // shift right to get the next bit
        }
        return bits;
    }

     //Converts an array of bits back into a long.

    private static long bitsToLong(int[] bits) {
        long value = 0;
        for (int bit : bits) {
            value = (value << 1) | bit;
        }
        return value;
    }

    private static int[] permute(int[] input, int[] table) {
        int[] output = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            // table[i] is 1-based, so subtract 1 for 0-based array indexing
            output[i] = input[table[i] - 1];
        }
        return output;
    }

    private static int[] xor(int[] a, int[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] ^ b[i];
        }
        return result;
    }

    private static int[] leftRotate(int[] bits, int n) {
        int len = bits.length;
        int[] rotated = new int[len];
        for (int i = 0; i < len; i++) {
            rotated[i] = bits[(i + n) % len];
        }
        return rotated;
    }

    // ══════════════════════════════════════════════════════
    //  STEP 1: KEY SCHEDULE  (generate 16 sub-keys)
    // ══════════════════════════════════════════════════════
    private static int[][] generateSubKeys(long keyLong) {
        System.out.println("\n══════════════════════════════");
        System.out.println("    KEY SCHEDULE");
        System.out.println("══════════════════════════════");

        // Convert the 64-bit key to bits
        int[] key64 = longToBits(keyLong, 64);
        System.out.printf("Original 64-bit key: %016X%n", keyLong);

        // Apply PC-1 to get 56-bit key (drops parity bits)
        int[] key56 = permute(key64, PC1);
        System.out.println("After PC-1 (56 bits): " + bitsToString(key56));

        // Split into two 28-bit halves
        int[] C = new int[28], D = new int[28];
        System.arraycopy(key56,  0, C, 0, 28);
        System.arraycopy(key56, 28, D, 0, 28);

        int[][] subKeys = new int[16][48];

        for (int round = 0; round < 16; round++) {
            // Left-rotate both halves
            C = leftRotate(C, SHIFTS[round]);
            D = leftRotate(D, SHIFTS[round]);

            // Merge C and D back into 56 bits
            int[] CD = new int[56];
            System.arraycopy(C, 0, CD,  0, 28);
            System.arraycopy(D, 0, CD, 28, 28);

            // Apply PC-2 to get 48-bit sub-key
            subKeys[round] = permute(CD, PC2);

            System.out.printf("Sub-key K%02d: %s%n", round + 1, bitsToString(subKeys[round]));
        }

        return subKeys;
    }

    // ══════════════════════════════════════════════════════
    //  STEP 3: THE FEISTEL FUNCTION  f(R, K)
    // ══════════════════════════════════════════════════════

    private static int[] feistel(int[] R, int[] K, int roundNum) {
        // a. Expand 32 → 48
        int[] expanded = permute(R, E);

        // b. XOR with sub-key
        int[] xored = xor(expanded, K);

        // c. S-box substitution (48 → 32)
        int[] sBoxOutput = new int[32];
        for (int s = 0; s < 8; s++) {
            // Take 6 bits for this S-box
            int[] sixBits = new int[6];
            System.arraycopy(xored, s * 6, sixBits, 0, 6);

            // Row = first and last bit (bits 1 and 6 → index 0 and 5)
            int row = (sixBits[0] << 1) | sixBits[5];

            // Col = middle 4 bits (bits 2,3,4,5 → index 1,2,3,4)
            int col = (sixBits[1] << 3) | (sixBits[2] << 2) | (sixBits[3] << 1) | sixBits[4];

            // Look up the S-box value (it's 4 bits)
            int val = S_BOXES[s][row][col];

            // Convert 4-bit value back to bits
            int[] fourBits = longToBits(val, 4);
            System.arraycopy(fourBits, 0, sBoxOutput, s * 4, 4);
        }

        // d. P-box permutation
        return permute(sBoxOutput, P);
    }

    // ══════════════════════════════════════════════════════
    //  MAIN ENCRYPTION
    // ══════════════════════════════════════════════════════

    public static long encrypt(long plaintext, long key) {
        System.out.println("\n══════════════════════════════");
        System.out.println("    DES ENCRYPTION");
        System.out.println("══════════════════════════════");
        System.out.printf("Plaintext : %016X%n", plaintext);
        System.out.printf("Key       : %016X%n", key);

        // Generate all 16 sub-keys from the original key
        int[][] subKeys = generateSubKeys(key);

        // Convert plaintext to 64-bit array
        int[] block = longToBits(plaintext, 64);

        // ── STEP 2: Initial Permutation ──
        int[] ip = permute(block, IP);
        System.out.println("\n══════════════════════════════");
        System.out.println("    ROUNDS");
        System.out.println("══════════════════════════════");
        System.out.println("After IP: " + bitsToString(ip));

        // Split into Left (L) and Right (R) halves, each 32 bits
        int[] L = new int[32], R = new int[32];
        System.arraycopy(ip,  0, L, 0, 32);
        System.arraycopy(ip, 32, R, 0, 32);

        // ── STEP 3: 16 Feistel Rounds ──
        for (int round = 0; round < 16; round++) {
            int[] newR = xor(L, feistel(R, subKeys[round], round + 1));
            int[] newL = R;  // old right becomes new left

            L = newL;
            R = newR;

            System.out.printf("Round %02d → L: %s  R: %s%n",
                    round + 1, bitsToString(L), bitsToString(R));
        }

        // After 16 rounds, combine R16 + L16  (NOTE: reversed order!)
        int[] combined = new int[64];
        System.arraycopy(R, 0, combined,  0, 32);
        System.arraycopy(L, 0, combined, 32, 32);

        // ── STEP 4: Final (Inverse) Permutation ──
        int[] cipherBits = permute(combined, IP_INV);
        long ciphertext = bitsToLong(cipherBits);

        System.out.println("\n══════════════════════════════");
        System.out.printf("Ciphertext: %016X%n", ciphertext);
        System.out.println("══════════════════════════════");

        return ciphertext;
    }

    public static long decrypt(long ciphertext, long key) {
        System.out.println("\n══════════════════════════════");
        System.out.println("    DES DECRYPTION");
        System.out.println("══════════════════════════════");

        int[][] subKeys = generateSubKeys(key);

        // Reverse the sub-keys
        int[][] reversedKeys = new int[16][48];
        for (int i = 0; i < 16; i++) {
            reversedKeys[i] = subKeys[15 - i];
        }

        int[] block = longToBits(ciphertext, 64);
        int[] ip = permute(block, IP);

        int[] L = new int[32], R = new int[32];
        System.arraycopy(ip,  0, L, 0, 32);
        System.arraycopy(ip, 32, R, 0, 32);

        for (int round = 0; round < 16; round++) {
            int[] newR = xor(L, feistel(R, reversedKeys[round], round + 1));
            int[] newL = R;
            L = newL;
            R = newR;
        }

        int[] combined = new int[64];
        System.arraycopy(R, 0, combined,  0, 32);
        System.arraycopy(L, 0, combined, 32, 32);

        int[] plainBits = permute(combined, IP_INV);
        long plaintext = bitsToLong(plainBits);

        System.out.printf("Decrypted : %016X%n", plaintext);
        return plaintext;
    }

    // ── Utility: print a bit array as a string of 0s and 1s ──
    private static String bitsToString(int[] bits) {
        StringBuilder sb = new StringBuilder();
        for (int b : bits) sb.append(b);
        return sb.toString();
    }

    // ══════════════════════════════════════════════════════
    //  MAIN
    // ══════════════════════════════════════════════════════
    public static void main(String[] args) {

        long plaintext  = 0x0123456789ABCDEFL;
        long key        = 0x133457799BBCDFF1L;

        long ciphertext = encrypt(plaintext, key);
        long decrypted  = decrypt(ciphertext, key);

        System.out.println("\n══════════════════════════════");
        System.out.println("    FINAL VERIFICATION");
        System.out.println("══════════════════════════════");
        System.out.printf("Original  : %016X%n", plaintext);
        System.out.printf("Encrypted : %016X%n", ciphertext);
        System.out.printf("Decrypted : %016X%n", decrypted);
        System.out.println("Match: " + (plaintext == decrypted ? "✓ YES" : "✗ NO"));
    }
}