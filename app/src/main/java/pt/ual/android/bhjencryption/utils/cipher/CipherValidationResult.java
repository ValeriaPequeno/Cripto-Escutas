package pt.ual.android.bhjencryption.utils.cipher;

public interface CipherValidationResult {
    public CipherErrorCode getCipherErrorCode();
    public boolean hasErrors();
}
