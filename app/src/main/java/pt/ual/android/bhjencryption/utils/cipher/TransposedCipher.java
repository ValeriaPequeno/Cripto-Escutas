package pt.ual.android.bhjencryption.utils.cipher;

import java.util.HashMap;
import java.util.Map;

public class TransposedCipher extends Cipher {
    private String password;

    public TransposedCipher(String message, String password){
        super(message);
        this.password = password;
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return null;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return null;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage(), this.password));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage(), this.password));
    }

    private static String encrypt(String enc, String pass){
        StringBuilder encoded = new StringBuilder();

        Map<Character, Integer> alfabeto = new HashMap<>();
        alfabeto.put('a', 1);
        alfabeto.put('b', 2);
        alfabeto.put('c', 3);
        alfabeto.put('d', 4);
        alfabeto.put('e', 5);
        alfabeto.put('f', 6);
        alfabeto.put('g', 7);
        alfabeto.put('h', 8);
        alfabeto.put('i', 9);
        alfabeto.put('j', 10);
        alfabeto.put('k', 11);
        alfabeto.put('l', 12);
        alfabeto.put('m', 13);
        alfabeto.put('n', 14);
        alfabeto.put('o', 15);
        alfabeto.put('p', 16);
        alfabeto.put('q', 17);
        alfabeto.put('r', 18);
        alfabeto.put('s', 19);
        alfabeto.put('t', 20);
        alfabeto.put('u', 21);
        alfabeto.put('v', 22);
        alfabeto.put('w', 23);
        alfabeto.put('x', 24);
        alfabeto.put('y', 25);
        alfabeto.put('z', 26);

        int offset = alfabeto.get(pass) % 26 + 26;

        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26));
                }
            } else {
                encoded.append(i);
            }
        }

        return encoded.toString();
    }

    private static String decrypt(String enc, String pass){
        StringBuilder decoded = new StringBuilder();

        Map<Character, Integer> alfabeto = new HashMap<>();
        alfabeto.put('a', 1);
        alfabeto.put('b', 2);
        alfabeto.put('c', 3);
        alfabeto.put('d', 4);
        alfabeto.put('e', 5);
        alfabeto.put('f', 6);
        alfabeto.put('g', 7);
        alfabeto.put('h', 8);
        alfabeto.put('i', 9);
        alfabeto.put('j', 10);
        alfabeto.put('k', 11);
        alfabeto.put('l', 12);
        alfabeto.put('m', 13);
        alfabeto.put('n', 14);
        alfabeto.put('o', 15);
        alfabeto.put('p', 16);
        alfabeto.put('q', 17);
        alfabeto.put('r', 18);
        alfabeto.put('s', 19);
        alfabeto.put('t', 20);
        alfabeto.put('u', 21);
        alfabeto.put('v', 22);
        alfabeto.put('w', 23);
        alfabeto.put('x', 24);
        alfabeto.put('y', 25);
        alfabeto.put('z', 26);

        int offset = (26 - alfabeto.get(pass)) % 26 + 26;

        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    decoded.append((char) ('A' + (i - 'A' + offset) % 26));
                } else {
                    decoded.append((char) ('a' + (i - 'a' + offset) % 26));
                }
            } else {
                decoded.append(i);
            }
        }

        return decoded.toString();
    }
}
