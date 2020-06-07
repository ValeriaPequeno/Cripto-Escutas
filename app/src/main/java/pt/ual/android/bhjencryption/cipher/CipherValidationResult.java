package pt.ual.android.bhjencryption.cipher;

public interface CipherValidationResult {
    public CipherErrorCode getCipherErrorCode();
    public boolean hasErrors();
}
