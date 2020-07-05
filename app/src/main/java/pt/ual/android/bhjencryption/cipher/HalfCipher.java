package pt.ual.android.bhjencryption.cipher;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class HalfCipher  extends Cipher {

    public HalfCipher(CipherMessage cipherMessage) {
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
        return new CipherResult(HalfCipher.halfEnc(getCipherMessage().getMessageAsText()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(HalfCipher.halfDecode(getCipherMessage().getMessageAsText()));
    }

    public static String halfEnc(String enc) {

        String inputLetters = enc.toUpperCase().replaceAll(" ","");
        String output="";
        String output1="";
        for (int i = 0; i < inputLetters.length(); i += 2) {

            output += inputLetters.charAt(i);
        }

        for (int i = 1; i < inputLetters.length(); i += 2) {

            output1 += inputLetters.charAt(i);
        }

        return output +" "+ output1;
    }

    public static String halfDecode(String enc) {
        //String exemplo = "CAAOOORSA HMRSCRIT";
        String[] saida = enc.toUpperCase().split(" ");

        String s = "";
        String a = saida[0];
        String b = saida[1];
        int i = 0;
        while (i < a.length() && i < b.length()){
            s += a.charAt(i) +""+ b.charAt(i);
            i++;
        }
        while (i < a.length() ){
            s += a.charAt(i);
            i++;
        }
        while (i < b.length()){
            s += b.charAt(i);
            i++;
        }
        return s;
    }
}