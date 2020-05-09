package pt.ual.android.bhjencryption.utils.cipher;

public class VerticalCipher extends Cipher {
    private int password;

    public VerticalCipher(String message, int password) {
        super(message);
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return null;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return null;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage(), this.password, "cima"));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage(), this.password, "cima"));
    }

    public static String encrypt(String enc, int size, String sentido) {
        StringBuilder encoded = new StringBuilder();

        String[] palavras = enc.split(" ");

        for (String pal : palavras) {
            int nEspaco = 1;

            if (nEspaco > 1) {
                encoded.append(" ");
                nEspaco++;
            } else {
                nEspaco++;
            }
            int tamHorizontal = (int) Math.ceil((double) pal.length() / (double) size);

            char[][] palMatriz = new char[size][tamHorizontal];

            //meter letras na matriz
            if (sentido == "cima") {
                int xIndex = size - 1, yIndex = 0;
                for (char letra : pal.toCharArray()) {
                    palMatriz[xIndex][yIndex] = letra;
                    xIndex--;
                    if (xIndex < 0) {
                        xIndex = size - 1;
                        yIndex++;
                    }
                }

                if (palMatriz[0][tamHorizontal - 1] == '\u0000') {
                    while (palMatriz[0][tamHorizontal - 1] == '\u0000') {
                        for (int i = 0; i < size - 1; i++) {
                            palMatriz[i][tamHorizontal - 1] = palMatriz[i + 1][tamHorizontal - 1];
                            palMatriz[i + 1][tamHorizontal - 1] = '\u0000';
                        }
                    }
                }
            } else if (sentido == "baixo") {
                int xIndex = 0, yIndex = 0;
                for (char letra : pal.toCharArray()) {
                    palMatriz[xIndex][yIndex] = letra;
                    xIndex++;
                    if (xIndex == size) {
                        xIndex = 0;
                        yIndex++;
                    }
                }
            }

            for (char[] linha : palMatriz) {
                for (char letra : linha) {
                    if (letra != '\u0000') {
                        encoded.append(letra);
                    }
                }
            }
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

            int tamHorizontal = (int) Math.ceil((double) pal.length() / (double) size);

            char[][] palMatriz = new char[size][tamHorizontal];
            int xIndex = 0, yIndex = 0;

            for (char letra : pal.toCharArray()) {
                palMatriz[xIndex][yIndex] = letra;
                int[] proximoPonto = getProximaCoordenada(pal.length(), size, tamHorizontal, xIndex, yIndex);
                xIndex = proximoPonto[0];
                yIndex = proximoPonto[1];
            }

            if (sentido == "cima") {
                xIndex = size - 1;
                yIndex = 0;
                while (yIndex < tamHorizontal) {
                    while (xIndex >= 0) {
                        if (palMatriz[xIndex][yIndex] != '\u0000') {
                            decoded.append(palMatriz[xIndex][yIndex]);
                            xIndex--;
                        } else {
                            xIndex--;
                        }
                    }
                    xIndex = size - 1;
                    yIndex++;
                }
            } else if (sentido == "baixo") {
                xIndex = 0;
                yIndex = 0;
                while (yIndex < tamHorizontal) {
                    while (xIndex < size) {
                        if (palMatriz[xIndex][yIndex] != '\u0000') {
                            decoded.append(palMatriz[xIndex][yIndex]);
                            xIndex++;
                        } else {
                            xIndex++;
                        }
                    }
                    xIndex = 0;
                    yIndex++;
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
