package pt.ual.android.bhjencryption.utils.cipher;

public class DateCipher extends Cipher{
    private String password;

    private DateCipher(String message){super(message);}

    @Override
    public CipherValidationResult validateEncrypt() {
        return null;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return null;
    }

    public DateCipher(String message, String password){
        this(message);
        this.password = password;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(CipherUtils.dataEncode(getMessage(), this.password));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(CipherUtils.dataDecode(getMessage(), this.password));
    }
}
