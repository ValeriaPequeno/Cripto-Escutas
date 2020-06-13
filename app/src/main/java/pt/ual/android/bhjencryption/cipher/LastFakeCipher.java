package pt.ual.android.bhjencryption.cipher;

import java.util.Random;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class LastFakeCipher extends Cipher {

    public LastFakeCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validateEncrypt() {

        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ALPHABET_LOWER, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
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
        return new CipherResult(LastFakeCipher.lastFakeEnc(getCipherMessage().getMessageAsText()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(LastFakeCipher.lastFakeDecode(getCipherMessage().getMessageAsText()));
    }

    public static String lastFakeEnc(String enc) {
        StringBuilder output = new StringBuilder();
        char[] alfabeto = CipherUtils.ALPHABET_LOWER.toUpperCase().toCharArray();

        Random random = new Random();
        String [] inputLetters;
        inputLetters = enc.toUpperCase().split("\\s+");
        for (String inputLetter : inputLetters) {
            int randomChartPosition = random.nextInt(alfabeto.length -1);
            if (!inputLetter.isEmpty()) {
                output.append(inputLetter).append(alfabeto[randomChartPosition]).append(" ");
            }
        }
        return output.toString();
    }


    public static String lastFakeDecode(String enc) {

        StringBuilder output = new StringBuilder();
        String [] inputLetters;
        inputLetters = enc.toUpperCase().split("\\s+");
        for (String inputLetter : inputLetters) {

            if (!inputLetter.isEmpty()) {
                output.append(inputLetter.substring(0, inputLetter.length()-1)).append(" ");
            }
        }
        return output.toString();
    }
}