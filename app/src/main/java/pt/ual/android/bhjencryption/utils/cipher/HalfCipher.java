package pt.ual.android.bhjencryption.utils.cipher;

public class HalfCipher  extends Cipher {

    public HalfCipher(String message) {
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
        return new CipherResult(HalfCipher.halfEnc(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(HalfCipher.halfDecode(getMessage()));
    }

    public static String halfEnc(String enc) {

        String inputLetters = enc.replaceAll(" ","");
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
        String[] saida = enc.split(" ");

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