package pt.ual.android.bhjencryption.cipher;

import java.util.HashMap;
import java.util.Map;

import pt.ual.android.bhjencryption.utils.IntegerUtils;
import pt.ual.android.bhjencryption.utils.StringUtils;

/**
 * Password só admite valores Naturais com 0 incluído.
 */
public class NumeralAlphabetCipher extends Cipher {

    public NumeralAlphabetCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validate() {
        CipherValidationResult result = super.validate();

        if(!result.hasErrors()) {
            result = validatePassword();
        }

        return result;
    }

    public CipherValidationResult validatePassword() {
        if(getCipherMessage().getPasswordAsInt() == Integer.MIN_VALUE)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(getCipherMessage().getPasswordAsInt() == Integer.MIN_VALUE + 1)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        if(getCipherMessage().getPasswordAsInt() < 0)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.NEGATIVE_INTEGER_PASSWORD));

        return new CipherResult();
    }

    /**
     * Validação para quando se cifra do alfabeto para numérico
     * @return
     */
    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ASCII_ALPHABET_LOWER, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
        }

        return result;
    }

    /**
     * validar se contém conjuntos de valores numéricos válidos (o valor tem de ser convertido
     * para inteiro e deve ser igual ao maior que a pass e menor (ou igual??) à password + o
     * lenght total do alfabeto
     *
     * Validação para quando se cifra do numérico para alfabeto de volta
     * @return
     */
    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.NUMERIC, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));

            if (!IntegerUtils.validateRangeValues( // validar se mensagem contém range numérico para cada letra do alfabeto de forma correcto.
                    IntegerUtils.strArrayToIntArray(getCipherMessage().getMessageAsText().split(" ")), getCipherMessage().getPasswordAsInt(),
                    CipherUtils.ASCII_ALPHABET_LOWER.length() + getCipherMessage().getPasswordAsInt() - 1))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_INVALID_FORMAT));
        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(NumeralAlphabetCipher.encrypt(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsInt()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(NumeralAlphabetCipher.decrypt(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsInt()));
    }

    public static String encrypt(String message, int password) {
        return NumeralAlphabetCipher.encryptCustomAlphabet(message, password, CipherUtils.ASCII_ALPHABET_LOWER, false, false);
    }

    public static String decrypt(String message, int password) {
        return NumeralAlphabetCipher.encryptCustomAlphabet(message, password, CipherUtils.ASCII_ALPHABET_LOWER, true, false);
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
    public static String encryptCustomAlphabet(String message, int password, String alphabet, boolean isNumeralMessage, boolean isAlphaCaseSensitive) {
        String input = isAlphaCaseSensitive ? message.trim() : message.toUpperCase().trim();
        Map<String, String> alphaEncodingTable = buildEncodingTable(password, alphabet, isNumeralMessage, isAlphaCaseSensitive);
        String[] splitedMessage = isNumeralMessage ? input.split(" ") : input.split("");
        StringBuilder sbOut = new StringBuilder();

        for(int i = 0; i < splitedMessage.length; i++) {
            sbOut.append(alphaEncodingTable.get(splitedMessage[i]));

            if(i + 1 < splitedMessage.length && !isNumeralMessage)
                sbOut.append(" ");
        }

        return sbOut.toString();
    }

    /**
     *
     * @param password
     * @param alphabet
     * @param isNumeralMessage
     * @param isAlphaCaseSensitive Por defeito, um alfabeto não sensível é sempre em maiúsculas
     * @return
     */
    private static Map<String, String> buildEncodingTable(int password, String alphabet, boolean isNumeralMessage, boolean isAlphaCaseSensitive) {
        String caseSensitiveAlphabet = isAlphaCaseSensitive ? new String(alphabet.toLowerCase() + alphabet.toUpperCase()) : alphabet.toUpperCase();
        String[] alphaArr = caseSensitiveAlphabet.split("");
        Map<String, String> alphaEncodingTable = new HashMap<String, String>();

        for(int i = 0; i < alphaArr.length; i++) {
            if(isNumeralMessage)
                alphaEncodingTable.put(String.valueOf(password++), alphaArr[i]);
            else alphaEncodingTable.put(alphaArr[i], String.valueOf(password++));
        }

        if(isNumeralMessage)
            alphaEncodingTable.put(String.valueOf(password), " "); // incluír o espaço na conversão da mensagem, caso contrário não se converterá frases.
        else alphaEncodingTable.put(" ", String.valueOf(password));

        return alphaEncodingTable;
    }
}
