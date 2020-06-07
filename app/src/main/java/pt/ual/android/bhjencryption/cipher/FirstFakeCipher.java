package pt.ual.android.bhjencryption.cipher;

import java.util.Random;

public class FirstFakeCipher extends Cipher {

    public FirstFakeCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
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
        return new CipherResult(FirstFakeCipher.firstFakeEnc(getCipherMessage().getMessageAsText()));
    }

    @Override
    public CipherResult decrypt() {
        StringBuilder output = new StringBuilder();
        return new CipherResult(FirstFakeCipher.firstFakeDecode(getCipherMessage().getMessageAsText()));
    }


    public static String firstFakeEnc(String enc) {
        StringBuilder output = new StringBuilder();
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Random random = new Random();
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {
            int randomChartPosition = random.nextInt(alfabeto.length -1);
            if (!inputLetter.isEmpty()) {
                output.append(alfabeto[randomChartPosition]).append("").append(inputLetter).append(" ");

            }
        }
        return output.toString();
    }


    public static String firstFakeDecode(String enc) {

        StringBuilder output = new StringBuilder();
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {

            if (!inputLetter.isEmpty()) {
                output.append(inputLetter.substring(1)).append(" ");
            }
        }
        return output.toString();
    }
}
