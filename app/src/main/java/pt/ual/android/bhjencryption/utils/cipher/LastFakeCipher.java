package pt.ual.android.bhjencryption.utils.cipher;

import java.util.Random;

public class LastFakeCipher extends Cipher {

    public LastFakeCipher(String message) {
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
        return new CipherResult(LastFakeCipher.lastFakeEnc(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(LastFakeCipher.lastFakeDecode(getMessage()));
    }

    public static String lastFakeEnc(String enc) {
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Random random = new Random();
        String output="";
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {
            int randomChartPosition = random.nextInt(alfabeto.length -1);
            if (!inputLetter.isEmpty()) {
                output += inputLetter + alfabeto[randomChartPosition]+ " " ;
            }
        }
        return output;
    }


    public static String lastFakeDecode(String enc) {

        String output="";
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {

            if (!inputLetter.isEmpty()) {
                output += inputLetter.substring(0, inputLetter.length()-1) + " ";
            }
        }
        return output;
    }
}