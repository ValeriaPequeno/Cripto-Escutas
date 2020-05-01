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
                break;
            case "Angular":
                break;
            case "Batalha Naval":
                break;
            case "Caranguejo":
                break;
            case "César":
                this.cipher = new CesarCipher(this.message, Integer.parseInt(this.password));
            case "Código Braille (Falso)":
                break;
            case "Código +3":
                this.cipher = new Code3PlusCipher(this.message);
            case "Código Chinês 1":
                break;
            case "Código Chinês 2":
                break;
            case "Data":
                break;
            case "Frase-Chave-Vertical":
                break;
            case "Frase-Chave-Horizontal":
                break;
            case "Homógrafo":
                break;
            case "Horizontal":
                this.cipher = new HorizontalCipher(this.message, Integer.parseInt(this.password));
            case "Metades":
                break;
            case "Morse":
                this.cipher = new MorseCipher(this.message);
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
            case "SMS":
                break;
            case "Transposto":
                break;
            case "Última Letra Falsa":
                break;
            case "Vertical":
                this.cipher = new VerticalCipher(this.message, Integer.parseInt(this.password));
            case "Vogais por Pontos":
                break;
            default:
                this.cipher = null;
        }
    }

    public CipherValidationResult validate() {
        if(cipher != null)
            return this.cipher.validate();

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
