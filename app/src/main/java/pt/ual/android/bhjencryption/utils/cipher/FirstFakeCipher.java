package pt.ual.android.bhjencryption.utils.cipher;

import java.util.Random;

public class FirstFakeCipher extends Cipher {

    public FirstFakeCipher(String message) {
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
        return new CipherResult(FirstFakeCipher.firstFakeEnc(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(FirstFakeCipher.firstFakeDecode(getMessage()));
    }


    public static String firstFakeEnc(String enc) {
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Random random = new Random();
        String output="";
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {
            int randomChartPosition = random.nextInt(alfabeto.length -1);
            if (!inputLetter.isEmpty()) {
                output +=  alfabeto[randomChartPosition]+ "" + inputLetter+ " ";

            }
        }
        return output;
    }


    public static String firstFakeDecode(String enc) {

        String output="";
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {

            if (!inputLetter.isEmpty()) {
                output += inputLetter.substring(1) + " ";
            }
        }
        return output;
    }
}
