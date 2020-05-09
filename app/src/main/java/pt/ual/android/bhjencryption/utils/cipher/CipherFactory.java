package pt.ual.android.bhjencryption.utils.cipher;

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
                this.cipher = new NumeralAlphabetCipher(this.message, Integer.parseInt(this.password));
                break;
            case "Angular":
                break;
            case "Batalha Naval":
                break;
            case "Caracol":
                break;
            case "Caranguejo":
                this.cipher = new CrabCipher(this.message);
                break;
            case "César":
                this.cipher = new CesarCipher(this.message, Integer.parseInt(this.password));
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
                this.cipher = new DateCipher(this.message, this.password);
            case "Frase-Chave-Vertical":
                break;
            case "Frase-Chave-Horizontal":
                break;
            case "Homógrafo":
                break;
            case "Horizontal":
                this.cipher = new HorizontalCipher(this.message, Integer.parseInt(this.password));
                break;
            case "Metades":
                break;
            case "Morse":
                this.cipher = new MorseCipher(this.message);
                break;
            case "Nós de Morse":
                break;
            case "Passa um Melro":
                break;
            case "Passa dois Melros":
                break;
            case "Picos de Morse":
                break;
            case "Primeira Letra Falsa":
                break;
            case "Romano-Árabe":
                this.cipher = new RomanArabCipher(this.message);
                break;
            case "SMS":
                break;
            case "Transposto":
                break;
            case "Última Letra Falsa":
                break;
            case "Vertical":
                this.cipher = new VerticalCipher(this.message, Integer.parseInt(this.password));
                break;
            case "Vogais por Pontos":
                break;
            default:
                this.cipher = null;
        }
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
