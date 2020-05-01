package pt.ual.android.bhjencryption.utils.cipher;

public class CipherErrorCode {
    public static final String MESSAGE_HAS_DIGITS = "IAC00";
    public static final String EMPTY_MESSAGE = "C00";
    public static final String UNKNOWN_CIPHER = "CF00";

    private String errorCode;

    public CipherErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
