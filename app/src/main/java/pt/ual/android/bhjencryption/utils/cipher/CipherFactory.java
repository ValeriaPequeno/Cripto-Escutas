package pt.ual.android.bhjencryption.utils.cipher;

public class CipherFactory {
    private String message;
    private String password;
    private String cipherType;

    public CipherFactory(String message, String password, String cipherType) {
        this.message = message;
        this.password = password;
        this.cipherType = cipherType;
    }

    public CipherResult encrypt() {
        switch (cipherType) {
            case "Alfabeto Invertido":
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
                return new CipherResult(CipherUtils.cesarEncode(
                        this.message,
                        Integer.parseInt(this.password))
                );
            case "Código Braille (Falso)":
                break;
            case "Código +3":
                return new CipherResult(CipherUtils.cesarEncode(
                        this.message,
                        3)
                );
            case "Código Chinês 1":
                break;
            case "Código Chinês 2":
                break;
            case "Data":
                return new CipherResult(CipherUtils.dataEncode(this.message, this.password));
            case "Frase-Chave-Vertical":
                break;
            case "Frase-Chave-Horizontal":
                break;
            case "Homógrafo":
                break;
            case "Horizontal":
                return new CipherResult(CipherUtils.horizontalEncode(this.message,
                        Integer.parseInt(this.password),
                        "esquerda"));
            case "Metades":
                break;
            case "Morse":
                return new CipherResult(CipherUtils.morseEncode(this.message));
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
                return new CipherResult(CipherUtils.romaArabEncode(this.message));
            case "SMS":
                break;
            case "Transposto":
                break;
            case "Última Letra Falsa":
                break;
            case "Vertical":
                return new CipherResult(CipherUtils.verticalEncode(this.message,
                        Integer.parseInt(this.password),
                        "cima"));
            case "Vogais por Pontos":
                break;
            default:
                return null;
        }

        return new CipherResult();
    }

    public CipherResult decrypt() {
        switch (cipherType) {
            case "Alfabeto Invertido":
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
                return new CipherResult(CipherUtils.cesarDecode(
                        this.message,
                        Integer.parseInt(this.password))
                );
            case "Código Braille (Falso)":
                break;
            case "Código +3":
                return new CipherResult(CipherUtils.cesarDecode(
                        this.message,
                        3)
                );
            case "Código Chinês 1":
                break;
            case "Código Chinês 2":
                break;
            case "Data":
                return new CipherResult(CipherUtils.dataDecode(this.message, this.password));
            case "Frase-Chave-Vertical":
                break;
            case "Frase-Chave-Horizontal":
                break;
            case "Horizontal":
                return new CipherResult(CipherUtils.horizontalDecode(this.message,
                        Integer.parseInt(this.password),
                        "esquerda"));
            case "Homógrafo":
                break;
            case "Metades":
                break;
            case "Morse":
                return new CipherResult(CipherUtils.morseDecode(this.message));
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
                return new CipherResult(CipherUtils.romaArabDecode(this.message));
            case "SMS":
                break;
            case "Transposto":
                break;
            case "Última Letra Falsa":
                break;
            case "Vertical":
                return new CipherResult(CipherUtils.verticalDecode(this.message,
                        Integer.parseInt(this.password),
                        "cima"));
            case "Vogais por Pontos":
                break;
            default:
                return null;
        }

        return null;
    }
}
