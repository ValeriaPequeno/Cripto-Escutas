package pt.ual.android.bhjencryption.utils.cipher;

import java.util.Random;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class Blackbird2Cipher  extends Cipher {

    public Blackbird2Cipher(String message) {
        super(message);
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = super.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getMessage(), CipherUtils.ALPHABET_LOWER, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = super.validate();

        if(!result.hasErrors()) {
            // TODO: código para validar a mensagem da maneira específica desta cifra.
        }

        return result;
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
        char[] alfabeto = CipherUtils.ALPHABET_LOWER.toUpperCase().toCharArray();

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