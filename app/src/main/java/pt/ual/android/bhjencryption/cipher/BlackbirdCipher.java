package pt.ual.android.bhjencryption.cipher;

import java.util.Random;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class BlackbirdCipher extends Cipher {

    public BlackbirdCipher(CipherMessage cipherMessage) {
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
        return new CipherResult(BlackbirdCipher.blackbirdEnc(getCipherMessage().getMessageAsText()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(BlackbirdCipher.blackbirdDecode(getCipherMessage().getMessageAsText()));
    }

    public static String blackbirdEnc(String enc) {
        StringBuilder output = new StringBuilder();
        char[] alfabeto = CipherUtils.ALPHABET_LOWER.toUpperCase().toCharArray();

        Random random = new Random();
        char[] inputLetters = enc.toUpperCase().toCharArray();

        for (int i=0; i<inputLetters.length; i++){

            int randomChartPosition = random.nextInt(alfabeto.length -1);
            output.append(inputLetters[i]).append("").append(alfabeto[randomChartPosition]);
        }
        return output.toString();
    }

    public static String blackbirdDecode(String enc) {
        StringBuilder output = new StringBuilder();
        char[] inputLetters = enc.toUpperCase().toCharArray();

        for (int i = 0; i < inputLetters.length; i += 2) {
            output.append(inputLetters[i]);
        }
        return output.toString();
    }

}
