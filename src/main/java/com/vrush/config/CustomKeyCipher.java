package com.vrush.config;

import java.util.Base64;

public class CustomKeyCipher {
    public static String encrypt(String plainText, String key) {
        char[] input = plainText.toCharArray();
        char[] k = key.toCharArray();
        byte[] output = new byte[input.length];
        int shift = 4;

        for (int i = 0; i < input.length; i++) {
            output[i] = (byte) (((input[i] ^ k[i % k.length]) + shift) & 0xFF);
        }
        return Base64.getEncoder().encodeToString(output);
    }

    public static String decrypt(String cipherText, String key) {
        byte[] input = Base64.getDecoder().decode(cipherText);
        char[] k = key.toCharArray();
        char[] output = new char[input.length];
        int shift = 4;

        for (int i = 0; i < input.length; i++) {
            output[i] = (char) (((input[i] - shift) ^ k[i % k.length]) & 0xFF);
        }
        return new String(output);
    }

    public static void main(String[] args) {
        String key = "12345";
        String original = "user-manager";
        String encrypted = encrypt(original, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Original: " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

    }
}