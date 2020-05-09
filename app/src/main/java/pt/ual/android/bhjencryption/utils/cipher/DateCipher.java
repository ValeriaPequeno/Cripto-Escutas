package pt.ual.android.bhjencryption.utils.cipher;

import java.util.ArrayList;
import java.util.List;

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
        return new CipherResult(encrypt(getMessage(), this.password));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage(), this.password));
    }

    public static String encrypt(String enc, String pass) {
        StringBuilder encoded = new StringBuilder();

        char[][] alfaNumerico = new char[][]{{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'},
                {'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3'},
                {'4', '5', '6', '7', '8', '9', '\u0000', '\u0000', '\u0000', '\u0000'}};

        String[] palavras = enc.split(" ");

        for (String pal : palavras) {
            int nEspaco = 1;

            if (nEspaco > 1) {
                encoded.append(" ");
                nEspaco++;
            } else {
                nEspaco++;
            }

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
            int nEspaco = 1;

            if (nEspaco > 1) {
                decoded.append(" ");
                nEspaco++;
            } else {
                nEspaco++;
            }

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
