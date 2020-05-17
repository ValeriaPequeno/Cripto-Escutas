package pt.ual.android.bhjencryption.utils.cipher;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class HorizontalCipher extends Cipher {
    private int password;

    public HorizontalCipher(String message, int password) {
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

        if(this.password == Integer.MIN_VALUE + 1)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        if(this.password < 0)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.NEGATIVE_INTEGER_PASSWORD));

        return new CipherResult();
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ALPHABET_LOWER_AND_NUMERIC, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ALPHABET_LOWER_AND_NUMERIC, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage(), password,"esquerda"));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage(), this.password,"esquerda"));
    }

    public static String encrypt(String enc, int size, String sentido) {
        StringBuilder encoded = new StringBuilder();

        String[] palavras = enc.split(" ");

        for (String pal : palavras) {
            /*int nEspaco = 1;

            if (nEspaco > 1) {
                encoded.append(" ");
                nEspaco++;
            } else {
                nEspaco++;
            }*/

            int tamVertical = (int) Math.ceil((double) pal.length() / (double) size);

            char[][] palMatriz = new char[tamVertical][size];

            if (sentido == "esquerda") {
                int xIndex = 0, yIndex = size - 1;
                for (char letra : pal.toCharArray()) {
                    palMatriz[xIndex][yIndex] = letra;
                    yIndex--;
                    if (yIndex < 0) {
                        yIndex = size - 1;
                        xIndex++;
                    }
                }

                if (palMatriz[tamVertical - 1][0] == '\u0000') {
                    while (palMatriz[tamVertical - 1][0] == '\u0000') {
                        for (int i = 0; i < size - 1; i++) {
                            palMatriz[tamVertical - 1][i] = palMatriz[tamVertical - 1][i + 1];
                            palMatriz[tamVertical - 1][i + 1] = '\u0000';
                        }
                    }
                }
            } else if (sentido == "direita") {
                int xIndex = 0, yIndex = 0;
                for (char letra : pal.toCharArray()) {
                    palMatriz[xIndex][yIndex] = letra;
                    yIndex++;
                    if (yIndex == size) {
                        yIndex = 0;
                        xIndex++;
                    }
                }
            }

            for (char[] linha : palMatriz) {
                for (char n : linha) {
                    if (n != '\u0000') {
                        encoded.append(n);
                    }
                }
            }
            encoded.append(" ");
        }

        return encoded.toString();
    }

    public static String decrypt(String enc, int size, String sentido) {
        StringBuilder decoded = new StringBuilder();

        String[] palavras = enc.split(" ");

        for (String pal : palavras) {
            int nEspaco = 1;

            if (nEspaco > 1) {
                decoded.append(" ");
                nEspaco++;
            } else {
                nEspaco++;
            }

            int tamVertical = (int) Math.ceil((double) pal.length() / (double) size);

            char[][] palMatriz = new char[tamVertical][size];
            int xIndex = 0, yIndex = 0;

            for (char letra : pal.toCharArray()) {
                palMatriz[xIndex][yIndex] = letra;
                int[] proximoPonto = getProximaCoordenada(pal.length(), tamVertical, size, xIndex, yIndex);
                xIndex = proximoPonto[0];
                yIndex = proximoPonto[1];
            }

            if (sentido == "esquerda") {
                xIndex = 0;
                yIndex = size - 1;
                while (xIndex < tamVertical) {
                    while (yIndex >= 0) {
                        if (palMatriz[xIndex][yIndex] != '\u0000') {
                            decoded.append(palMatriz[xIndex][yIndex]);
                            yIndex--;
                        } else {
                            yIndex--;
                        }
                    }
                    xIndex++;
                    yIndex = size - 1;
                }
            } else if (sentido == "direita") {
                xIndex = 0;
                yIndex = 0;
                while (xIndex < tamVertical) {
                    while (yIndex < size) {
                        if (palMatriz[xIndex][yIndex] != '\u0000') {
                            decoded.append(palMatriz[xIndex][yIndex]);
                            yIndex++;
                        } else {
                            yIndex++;
                        }
                    }
                    xIndex++;
                    yIndex = 0;
                }
            }
        }

        return decoded.toString();
    }

    private static int[] getProximaCoordenada(int tamanhoPalavra, int tamanhoV, int tamanhoH, int posicaoV, int posicaoH) {
        int leap = tamanhoPalavra % tamanhoV;
        if (leap > 0 && ((posicaoV == leap && posicaoH == tamanhoH - 2) || (posicaoV > leap))) {
            tamanhoH = tamanhoH - 1;
        }
        if (posicaoH < tamanhoH - 1) {
            return new int[]{posicaoV, posicaoH + 1};
        }
        return new int[]{posicaoV + 1, (posicaoH + 1) % tamanhoH};
    }
}
