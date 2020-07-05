package pt.ual.android.bhjencryption.cipher;

import java.util.HashMap;
import java.util.Map;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class TransposedCipher extends Cipher {

    public TransposedCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validate(boolean isEncrypt) {

        CipherValidationResult result = super.validate(isEncrypt);

        if(!result.hasErrors()) {
            result = validatePassword();
        }

        return result;
    }

    public CipherValidationResult validatePassword() {
        if(getCipherMessage().getPasswordAsText().isEmpty())
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(!StringUtils.matchingChars(getCipherMessage().getPasswordAsText(), CipherUtils.ASCII_ALPHABET_LOWER, false, false))
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        if(getCipherMessage().getPasswordAsText().length() > 1){
            return new CipherResult(new CipherErrorCode(CipherErrorCode.INVALID_PASSWORD_SIZE));
        }

        return new CipherResult();
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate(true);

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate(false);

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsText()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsText()));
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

        int offset = alfabeto.get(pass.charAt(0)) % 26 + 26;

        String[] palavra = enc.split(" ");

        for(String p : palavra){
            for (char i : p.toCharArray()) {
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
            encoded.append(" ");
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

        int offset = (26 - alfabeto.get(pass.charAt(0))) % 26 + 26;

        String[] palavra = enc.split(" ");
        for(String p : palavra){
            for (char i : p.toCharArray()) {
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
            decoded.append(" ");
        }

        return decoded.toString();
    }
}
