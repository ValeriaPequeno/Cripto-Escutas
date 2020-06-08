package pt.ual.android.bhjencryption.cipher;

public class CipherErrorCode {
    public static final String MESSAGE_HAS_NOT_ALLOWED_CHARS = "C00";
    public static final String EMPTY_MESSAGE = "C01";
    public static final String UNKNOWN_CIPHER = "C02";
    public static final String MESSAGE_INVALID_FORMAT = "C03";
    public static final String PASSWORD_HAS_NOT_ALLOWED_CHARS = "C04";
    public static final String INVALID_PASSWORD_SIZE = "C05";
    public static final String EMPTY_PASSWORD = "C06";
    public static final String NEGATIVE_INTEGER_PASSWORD = "C07";

    private String errorCode;

    public CipherErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
