package pt.ual.android.bhjencryption.cipher;

import java.util.Random;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class SnailCipher  extends Cipher {

    public SnailCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validate(boolean isEncrypt) {

        CipherValidationResult result = super.validate(isEncrypt);

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

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate(true);

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ALPHABET_LOWER, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return null;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(SnailCipher.snailEnc(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsInt()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(SnailCipher.snailDecode(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsInt()));
    }

    public static String snailEnc(String enc, int size) {
        StringBuilder output = new StringBuilder();
        String word = enc.replaceAll(" ", "").toUpperCase();
        char[][] spiral = new char[size][size];
        char[] alfabeto = CipherUtils.ALPHABET_LOWER.toUpperCase().toCharArray();
        Random random = new Random();
        int diff = (size * size) - word.length();
        int value = 0, minCol = 0, maxCol = size - 1, minRow = 0, maxRow = size - 1;

        if (word.length() < size * size) {
            for (int i = 1; i <= diff; i++) {
                int randomChartPosition = random.nextInt(alfabeto.length - 1);
                word = word + alfabeto[randomChartPosition];
            }
        }

        if (word.length() > size * size) {
            System.out.print("O nº da altura/largura da tabela é muito pequeno!");
            System.exit(0);
        }

        while (value < size * size) {
            for (int i = minRow; i <= maxRow; i++) {
                spiral[i][minCol] = word.charAt(value);
                value++;
            }

            for (int i = minCol + 1; i <= maxCol; i++) {
                spiral[maxRow][i] = word.charAt(value);
                value++;
            }

            for (int i = maxRow - 1; i >= minRow; i--) {
                spiral[i][maxCol] = word.charAt(value);
                value++;
            }

            for (int i = maxCol - 1; i >= minCol + 1; i--) {
                spiral[minRow][i] = word.charAt(value);
                value++;
            }

            minCol++;
            minRow++;
            maxCol--;
            maxRow--;

        }

        for (char[] spiral1 : spiral) {
            for (int j = 0; j < spiral.length; j++) {
                System.out.print(spiral1[j] + " ");
                output.append(spiral1[j]);
            }
            System.out.println();
        }

        return output.toString();
    }

    public static String snailDecode(String enc, int size) {
        char[][] spiral = new char[size][size];
        StringBuilder wordBuilder = new StringBuilder();

        int n = 0;
        for (char[] spiral1 : spiral) {
            for (int x = 0; x < spiral1.length; x++) {
                spiral1[x] = enc.charAt(n);
                n++;
            }
        }

        int value = 0, minCol = 0, maxCol = size - 1, minRow = 0, maxRow = size - 1;

        while (value < size * size) {
            for (int i = minRow; i <= maxRow; i++) {
                wordBuilder.append(spiral[i][minCol]);
                value++;
            }

            for (int i = minCol + 1; i <= maxCol; i++) {
                wordBuilder.append(spiral[maxRow][i]);
                value++;
            }

            for (int i = maxRow - 1; i >= minRow; i--) {
                wordBuilder.append(spiral[i][maxCol]);
                value++;
            }

            for (int i = maxCol - 1; i >= minCol + 1; i--) {
                wordBuilder.append(spiral[minRow][i]);
                value++;
            }

            minCol++;
            minRow++;
            maxCol--;
            maxRow--;

        }

        return wordBuilder.toString();
    }
}