package pt.ual.android.bhjencryption.cipher;

public class CipherFactory {
    private CipherMessage cipherMessage;
    private Cipher cipher;
    private String cipherType;

    public CipherFactory(CipherMessage cipherMessage, String cipherType) {
        this.cipherMessage = cipherMessage;
        this.cipherType = cipherType;

        initCipherMethod();
    }

    /**
     * No limite, o conteúdo deste método poderá ser substituido por um mecanismo de instacioção da class por Reflection.
     */
    private void initCipherMethod() {
        switch (cipherType) {
            case "Alfabeto Invertido":
                this.cipher = new ReverseAlphatedCipher(this.cipherMessage);
                break;
            case "Alfabeto Numeral":
                this.cipher = new NumeralAlphabetCipher(this.cipherMessage);
                break;
            case "Angular":
                this.cipher = new AngularCipher(this.cipherMessage);
                break;
            case "Batalha Naval":
                this.cipher = new TheRightBattleshipCipher(this.cipherMessage);
                break;
            case "Caracol":
                this.cipher = new SnailCipher(this.cipherMessage);
                break;
            case "Caranguejo":
                this.cipher = new CrabCipher(this.cipherMessage);
                break;
            case "César":
                this.cipher = new CesarCipher(this.cipherMessage);
                break;
            case "Código Braille (Falso)":
                this.cipher = new FalseBrailleCodeCipher(this.cipherMessage);
                break;
            case "Código +3":
                this.cipher = new Code3PlusCipher(this.cipherMessage);
                break;
            case "Código Chinês 1":
                this.cipher = new ChineseCode1Cipher(this.cipherMessage);
                break;
            case "Código Chinês 2":
                this.cipher = new ChineseCode2Cipher(this.cipherMessage);
                break;
            case "Data":
                this.cipher = new DateCipher(this.cipherMessage);
                break;
            case "Frase-Chave-Vertical":
                this.cipher = new VerticalKeyPhraseCipher(this.cipherMessage);
                break;
            case "Frase-Chave-Horizontal":
                this.cipher = new HorizontalKeyPhraseCipher(this.cipherMessage);
                break;
            case "Homógrafo":
                this.cipher = new HomographCipher(this.cipherMessage);
                break;
            case "Horizontal":
                this.cipher = new HorizontalCipher(this.cipherMessage);
                break;
            case "Metades":
                this.cipher = new HalfCipher(this.cipherMessage);
                break;
            case "Morse":
                this.cipher = new MorseCipher(this.cipherMessage);
                break;
            case "Nós de Morse":
                this.cipher = new MorseKnotsCipher(this.cipherMessage);
                break;
            case "Passa um Melro":
                this.cipher = new BlackbirdCipher(this.cipherMessage);
                break;
            case "Passa dois Melros":
                this.cipher = new Blackbird2Cipher(this.cipherMessage);
                break;
            case "Picos de Morse":
                this.cipher = new MorsePeaksCipher(this.cipherMessage);
                break;
            case "Primeira Letra Falsa":
                this.cipher = new FirstFakeCipher(this.cipherMessage);
                break;
            case "Romano-Árabe":
                this.cipher = new RomanArabCipher(this.cipherMessage);
                break;
            case "SMS":
                this.cipher = new SmsCipher(this.cipherMessage);
                break;
            case "Transposto":
                this.cipher = new TransposedCipher(this.cipherMessage);
                break;
            case "Última Letra Falsa":
                this.cipher = new LastFakeCipher(this.cipherMessage);
                break;
            case "Vertical":
                this.cipher = new VerticalCipher(this.cipherMessage);
                break;
            case "Vogais por Pontos":
                this.cipher = new VowelsByPointsCipher(this.cipherMessage);
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
