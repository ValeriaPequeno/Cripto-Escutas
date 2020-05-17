package pt.ual.android.bhjencryption.utils.cipher;

public class VowelsByPointsCipher extends Cipher {
    public VowelsByPointsCipher(String message){ super(message); }

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

        return decoded.toString();
    }
}
