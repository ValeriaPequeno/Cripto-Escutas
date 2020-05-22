package pt.ual.android.bhjencryption.utils.cipher;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class VowelsByPointsCipher extends Cipher {
    public VowelsByPointsCipher(String message){ super(message); }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ALPHABET_LOWER_AND_NUMERIC, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ASCII_CONSONANTS_LOWER_NUMERIC_AND_POINT, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage()));
    }

    public static String encrypt(String enc){
        StringBuilder encoded = new StringBuilder();

        String[] palavras = enc.split(" ");

        for(String pal : palavras){
            for(char let : pal.toCharArray()){
                for(char vow : CipherUtils.VOWELS_LOWER.toCharArray()){
                    if(let == Character.toLowerCase(vow) || let == Character.toUpperCase(vow)){
                        encoded.append('.');
                        break;
                    }
                    else if(let == '.'){
                        break;
                    }
                    else{
                        encoded.append(let);
                    }
                }
            }

            encoded.append(" ");
        }

        return encoded.toString();
    }

    public static String decrypt(String enc){
        StringBuilder decoded = new StringBuilder();

        String[] palavras = enc.split(" ");

        /**
         *  TODO:
         *  - https://developer.android.com/guide/topics/text/spell-checker-framework ,
         *  - https://code.tutsplus.com/tutorials/an-introduction-to-androids-spelling-checker-framework--cms-23754
         */

        return decoded.toString();
    }
}
