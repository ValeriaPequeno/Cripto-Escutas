package pt.ual.android.bhjencryption.utils.cipher;

public class CipherErrorCode {
    public static final String MESSAGE_HAS_NOT_ALLOWED_CHARS = "C00";
    public static final String EMPTY_MESSAGE = "C01";
    public static final String UNKNOWN_CIPHER = "C02";

    private String errorCode;

    public CipherErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
