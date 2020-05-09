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
        return new CipherResult(Blackbird2Cipher.blackbird2Enc(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(Blackbird2Cipher.blackbird2Decode(getMessage()));
    }

    public static String blackbird2Enc(String enc) {
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Random random = new Random();
        char[] inputLetters = enc.toCharArray();
        String output="";

        for (int i=0; i<inputLetters.length; i++){

            int randomChartPosition = random.nextInt(alfabeto.length -1);
            output += inputLetters[i] + "" + alfabeto[randomChartPosition]+ "" + alfabeto[randomChartPosition];
        }
        return output;
    }

    public static String blackbird2Decode(String enc) {

        char[] inputLetters = enc.toCharArray();
        String output="";
        for (int i = 0; i < inputLetters.length; i += 3) {
            //char result = input.charAt(i);
            output += inputLetters[i];
        }
        return output;
    }
}