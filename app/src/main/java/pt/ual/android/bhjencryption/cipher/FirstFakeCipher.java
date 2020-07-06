package pt.ual.android.bhjencryption.cipher;

import java.util.Random;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class FirstFakeCipher extends Cipher {

    public FirstFakeCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validateEncrypt() {

        CipherValidationResult result = this.validate(true);

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ALPHABET_LOWER, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }
    @Override
    public CipherValidationResult validateDecrypt() {

        CipherValidationResult result = this.validate(false);

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ALPHABET_LOWER, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
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
        char[] alfabeto = CipherUtils.ALPHABET_LOWER.toUpperCase().toCharArray();

        Random random = new Random();
        String [] inputLetters;
        inputLetters = enc.toUpperCase().split("\\s+");
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
        inputLetters = enc.toUpperCase().split("\\s+");
        for (String inputLetter : inputLetters) {

            if (!inputLetter.isEmpty()) {
                output.append(inputLetter.substring(1)).append(" ");
            }
        }
        return output.toString();
    }
}
