package pt.ual.android.bhjencryption.cipher;

import java.util.Random;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class Blackbird2Cipher extends Cipher {

    public Blackbird2Cipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = super.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ALPHABET_LOWER, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {

        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ALPHABET_LOWER, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(blackbird2Enc(getCipherMessage().getMessageAsText()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(blackbird2Decode(getCipherMessage().getMessageAsText()));
    }

    public static String blackbird2Enc(String enc) {


        char[] alfabeto = CipherUtils.ALPHABET_LOWER.toUpperCase().toCharArray();
        StringBuilder output = new StringBuilder();

        Random random = new Random();
        char[] inputLetters = enc.toUpperCase().toCharArray();

        for (int i=0; i<inputLetters.length; i++){

            int randomChartPosition = random.nextInt(alfabeto.length -1);
            output.append(inputLetters[i]).append("").append(alfabeto[randomChartPosition]).append("").append(alfabeto[randomChartPosition]);
        }
        return output.toString();
    }

    public static String blackbird2Decode(String enc) {
        StringBuilder output = new StringBuilder();
        char[] inputLetters = enc.toUpperCase().toCharArray();

        for (int i = 0; i < inputLetters.length; i += 3) {
            output.append(inputLetters[i]);
        }
        return output.toString();
    }
}