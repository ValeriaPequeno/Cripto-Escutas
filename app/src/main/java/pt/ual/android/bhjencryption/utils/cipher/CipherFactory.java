package pt.ual.android.bhjencryption.utils.cipher;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class CipherFactory {
    private String message;
    private String password;
    private String cipherType;
    private Cipher cipher;

    public CipherFactory(String message, String password, String cipherType) {
        this.message = message;
        this.password = password;
        this.cipherType = cipherType;

        initCipherMethod();
    }

    private void initCipherMethod() {
        switch (cipherType) {
            case "Alfabeto Invertido":
                this.cipher = new ReverseAlphatedCipher(this.message);
                break;
            case "Alfabeto Numeral":
                this.cipher = new NumeralAlphabetCipher(this.message, parsePasswordToInt());
                break;
            case "Angular":
                break;
            case "Batalha Naval":
                this.cipher = new TheRightBattleshipCipher(this.message, this.password);
                break;
            case "Caracol":
                this.cipher = new SnailCipher(this.message, Integer.parseInt(this.password));
            case "Caranguejo":
                this.cipher = new CrabCipher(this.message);
                break;
            case "César":
                this.cipher = new CesarCipher(this.message, parsePasswordToInt());
                break;
            case "Código Braille (Falso)":
                break;
            case "Código +3":
                this.cipher = new Code3PlusCipher(this.message);
                break;
            case "Código Chinês 1":
                break;
            case "Código Chinês 2":
                break;
            case "Data":
                this.cipher = new DateCipher(this.message, Integer.parseInt(this.password));
            case "Frase-Chave-Vertical":
                this.cipher = new VerticalKeyPhraseCipher(this.message, this.password);
                break;
            case "Frase-Chave-Horizontal":
                this.cipher = new HorizontalKeyPhraseCipher(this.message, this.password);
                break;
            case "Homógrafo":
                break;
            case "Horizontal":
                this.cipher = new HorizontalCipher(this.message, parsePasswordToInt());
                break;
            case "Metades":
                this.cipher = new HalfCipher(this.message);
            case "Morse":
                this.cipher = new MorseCipher(this.message);
                break;
            case "Nós de Morse":
                break;
            case "Passa um Melro":
                this.cipher = new BlackbirdCipher(this.message);
            case "Passa dois Melros":
                this.cipher = new Blackbird2Cipher(this.message);
            case "Picos de Morse":
                break;
            case "Primeira Letra Falsa":
                this.cipher = new FirstFakeCipher(this.message);
            case "Romano-Árabe":
                this.cipher = new RomanArabCipher(this.message);
                break;
            case "SMS":
                this.cipher = new SmsCipher(this.message);
            case "Transposto":
                this.cipher = new TransposedCipher(this.message, this.password);
            case "Última Letra Falsa":
                this.cipher = new LastFakeCipher(this.message);
            case "Vertical":
                this.cipher = new VerticalCipher(this.message, parsePasswordToInt());
                break;
            case "Vogais por Pontos":
                this.cipher = new VowelsByPointsCipher(this.message);
            default:
                this.cipher = null;
        }
    }

    /**
     * Por convenção, se a Password na forma de string necessitar de ser convertida para inteiro é necessário tratar das possíveis excepções.
     *
     * Para já, fica deste modo mas futuramente poderemos ter de evoluir.
     *
     * Há mais abordagens, mas deixemos assim para já.
     * @return Integer.MIN_VALUE se a string for nula ou vazia. Integer.MIN_VALUE + 1 se a conversão correr mal e o valor inteiro da conversão seja uma string válida
     */
    private int parsePasswordToInt() {
        if(this.password != null)
            if(this.password.length() > 0) { // não ficou incluída a validação se a string tem dígitos ou não pois valores negativos poderão ser admitidos e será melhor o tipo de cifra validar posteriormente.
                try {
                    return Integer.parseInt(this.password);
                }
                catch(Exception ex) {
                    return Integer.MIN_VALUE + 1;
                }
            }

        return Integer.MIN_VALUE;
    }

    public CipherValidationResult validateEncrypt() {
        if(cipher != null)
            return this.cipher.validateEncrypt();

        return new CipherResult(new CipherErrorCode(CipherErrorCode.UNKNOWN_CIPHER));
    }

    public CipherValidationResult validateDecrypt() {
        if(cipher != null)
            return this.cipher.validateDecrypt();

        return new CipherResult(new CipherErrorCode(CipherErrorCode.UNKNOWN_CIPHER));
    }

    public CipherResult encrypt() {
        if(cipher != null)
            return this.cipher.encrypt();

        return new CipherResult(new CipherErrorCode(CipherErrorCode.UNKNOWN_CIPHER));
    }

    public CipherResult decrypt() {
        if(cipher != null)
            return this.cipher.decrypt();

        return new CipherResult(new CipherErrorCode(CipherErrorCode.UNKNOWN_CIPHER));
    }

}
