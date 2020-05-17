package pt.ual.android.bhjencryption.utils.cipher;

import java.util.Random;

public class Blackbird2Cipher  extends Cipher {

    public Blackbird2Cipher(String message) {
        super(message);
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
        return new CipherResult(blackbird2Enc(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(blackbird2Decode(getMessage()));
    }

    public static String blackbird2Enc(String enc) {
        StringBuilder output = new StringBuilder();
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random random = new Random();
        char[] inputLetters = enc.toCharArray();

        for (int i=0; i<inputLetters.length; i++){

            int randomChartPosition = random.nextInt(alfabeto.length -1);
            output.append(inputLetters[i]).append("").append(alfabeto[randomChartPosition]).append("").append(alfabeto[randomChartPosition]);
        }
        return output.toString();
    }

    public static String blackbird2Decode(String enc) {
        StringBuilder output = new StringBuilder();
        char[] inputLetters = enc.toCharArray();

        for (int i = 0; i < inputLetters.length; i += 3) {
            output.append(inputLetters[i]);
        }
        return output.toString();
    }
}