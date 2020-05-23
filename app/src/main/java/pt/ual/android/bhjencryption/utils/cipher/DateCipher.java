package pt.ual.android.bhjencryption.utils.cipher;

import java.util.ArrayList;
import java.util.List;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class DateCipher extends Cipher{
    private int password;

    public DateCipher(String message, int password){
        super(message);
        this.password = password;
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
        if(this.password == Integer.MIN_VALUE)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(String.valueOf(this.password).length() != 4)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.INVALID_PASSWORD_SIZE));

        if(this.password == Integer.MIN_VALUE + 1)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        if(this.password  < 0)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.NEGATIVE_INTEGER_PASSWORD));

        return new CipherResult();
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ASCII_ALPHABET_LOWER_AND_NUMERIC, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.NUMERIC, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }

            String[] palavras = getMessage().split(" ");
            for(String i : palavras){
                String[] paresIndex = splitLetras(i);

                for(String n : paresIndex){
                    if(n.length() != 2){
                        return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_INVALID_FORMAT));
                    }
                }
            }

        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage(), String.valueOf(this.password)));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage(), String.valueOf(this.password)));
    }

    public static String encrypt(String enc, String pass) {
        StringBuilder encoded = new StringBuilder();

        char[][] alfaNumerico = new char[][]{{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'},
                {'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3'},
                {'4', '5', '6', '7', '8', '9', '\u0000', '\u0000', '\u0000', '\u0000'}};

        String[] palavras = enc.split(" ");

        for (String pal : palavras) {
            for (char letra : pal.toCharArray()) {
                for (int y = 0; y < alfaNumerico.length; y++) {
                    for (int x = 0; x < alfaNumerico[y].length; x++) {
                        if (Character.toLowerCase(letra) == alfaNumerico[y][x]) {
                            encoded.append(pass.charAt(y));
                            int xIndex = x + 1;
                            if (xIndex == 10) {
                                encoded.append(0);
                            } else {
                                encoded.append(xIndex);
                            }
                        }
                    }
                }
            }
            encoded.append(" ");
        }

        return encoded.toString();
    }

    public static String decrypt(String enc, String pass) {
        StringBuilder decoded = new StringBuilder();

        char[][] alfaNumerico = new char[][]{{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'},
                {'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3'},
                {'4', '5', '6', '7', '8', '9', '\u0000', '\u0000', '\u0000', '\u0000'}};

        String[] palavras = enc.split(" ");

        for (String pal : palavras) {
            String[] paresIndex = splitLetras(pal);
            int xIndex = 0, yIndex = 0;

            for (String letra : paresIndex) {
                yIndex = Integer.valueOf(Character.toString(letra.charAt(0)));
                xIndex = Integer.valueOf(Character.toString(letra.charAt(1)));

                for (int i = 0; i < 4; i++) {
                    if (yIndex == Integer.valueOf(Character.toString(pass.charAt(i)))) {
                        yIndex = i;
                        break;
                    }
                }

                if (xIndex == 0) {
                    xIndex = 9;
                } else {
                    xIndex--;
                }

                decoded.append(alfaNumerico[yIndex][xIndex]);
            }

            decoded.append(" ");
        }

        return decoded.toString();
    }

    private static String[] splitLetras(String palavra) {
        List<String> partes = new ArrayList<>();

        int length = palavra.length();
        for (int i = 0; i < length; i += 2) {
            partes.add(palavra.substring(i, Math.min(length, i + 2)));
        }
        return partes.toArray(new String[0]);
    }
}
