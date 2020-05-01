package pt.ual.android.bhjencryption.utils.cipher;

import java.util.HashMap;
import java.util.Map;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class NumeralAlphabetCipher extends Cipher{

    public static final String ALPHABET_LOWER = "aàáâãbcçdeéêfghiíjklmnoóôõpqrstuúvwxyz"; // abcdefghijklmnopqrstuvwxyz

    private int password;

    private NumeralAlphabetCipher(String message) {
        super(message);
    }

    public NumeralAlphabetCipher(String message, int password) {
        this(message);

        this.password = password;
    }

    @Override
    public CipherValidationResult validate() {
        CipherValidationResult result = super.validate();

        if(!super.validate().hasErrors())
            if(StringUtils.hasDigit(getMessage()))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_DIGITS));

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(NumeralAlphabetCipher.encrypt(getMessage(), password));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(NumeralAlphabetCipher.decrypt(getMessage(), password));
    }

    public static String encrypt(String message, int password) {
        return NumeralAlphabetCipher.encryptCustomAlphabet(message, password, ALPHABET_LOWER, false);
    }

    public static String decrypt(String message, int password) {
        return NumeralAlphabetCipher.encryptCustomAlphabet(message, password, ALPHABET_LOWER, true);
    }

    /**
     *  A mensagem será dividida caracter a caracter no caso de a mensagem estar em alfabeto clássico e será dividida pelo
     *  número de digitos que a password conter no caso de a mensagem estar em alfabeto numeral.
     * @param message
     * @param password
     * @param alphabet
     * @param isNumeralMessage
     * @return
     */
    public static String encryptCustomAlphabet(String message, int password, String alphabet, boolean isNumeralMessage) {
        //String[][] alphaEncodingTable = buildEncodingTable(password, alphabet);
        Map<String, String> alphaEncodingTable = buildEncodingTable(password, alphabet, isNumeralMessage);
        String[] splitedMessage = isNumeralMessage ? message.split(" ") : message.split("");
        StringBuilder sbOut = new StringBuilder();

        for(int i = 0; i < splitedMessage.length; i++) {
            sbOut.append(alphaEncodingTable.get(splitedMessage[i]));

            if(i + 1 < splitedMessage.length && !isNumeralMessage)
                sbOut.append(" ");
        }

        return sbOut.toString();
    }

    private static Map<String, String> buildEncodingTable(int password, String alphabet, boolean isNumeralMessage) {
        String caseSensitiveAlphabet = new String(alphabet + alphabet.toUpperCase());
        String[] alphaArr = caseSensitiveAlphabet.split("");
        Map<String, String> alphaEncodingTable = new HashMap<>();

        for(int i = 0; i < alphaArr.length; i++) {
            if(isNumeralMessage)
                alphaEncodingTable.put(String.valueOf(password++), alphaArr[i]);
            else alphaEncodingTable.put(alphaArr[i], String.valueOf(password++));
        }

        if(isNumeralMessage)
            alphaEncodingTable.put(String.valueOf(password), " "); // incluír o espaço na conversão da mensagem, caso contrário não se converterá frases.
        else alphaEncodingTable.put(" ", String.valueOf(password)); // incluír o espaço na conversão da mensagem, caso contrário não se converterá frases.

        return alphaEncodingTable;
    }
}
