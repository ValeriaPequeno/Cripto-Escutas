package pt.ual.android.bhjencryption.cipher;

import android.graphics.Bitmap;

/**
 * O tipo genérico serve para segregar a lógica da View para o Controller, de modo a que implementações futuras para outros SOs seja mais fácil
 */
public class CipherMessage {
    private CipherImageMessage imageTextMessage;
    private String passwordAsText;
    private Integer passwordAsInt;

    public CipherMessage(CipherImageMessage imageTextMessage) {
        this.imageTextMessage = imageTextMessage;
        this.passwordAsText = null;
        this.passwordAsInt = null;
    }

    public CipherMessage(CipherImageMessage imageTextMessage, String passwordAsText) {
        this.passwordAsText = passwordAsText;
        this.passwordAsInt = null;
        this.imageTextMessage = imageTextMessage;
    }

    public CipherImageMessage getImageTextMessage() {
        return imageTextMessage;
    }

    public String getMessageAsText() {
        return ((CipherImageMessage) imageTextMessage).getMessageAsText();
    }

    public <T> Bitmap getMessageAsImage(T value) {
        return ((CipherImageMessage) imageTextMessage).getMessageAsImage(value);
    }

    public String getPasswordAsText() {
        return passwordAsText;
    }

    /**
     * Lazy parsing - A password por vezes é uma string e não um inteiro
     * @return
     */
    public int getPasswordAsInt() {
        if(this.passwordAsInt == null)
            this.passwordAsInt = parsePasswordToInt();

        return this.passwordAsInt.intValue();
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
        if(this.passwordAsText != null)
            if(this.passwordAsText.length() > 0) { // não ficou incluída a validação se a string tem dígitos ou não pois valores negativos poderão ser admitidos e será melhor o tipo de cifra validar posteriormente.
                try {
                    return Integer.parseInt(this.passwordAsText);
                }
                catch(Exception ex) {
                    return Integer.MIN_VALUE + 1;
                }
            }

        return Integer.MIN_VALUE;
    }

    public int getMessageMaxLengthEncrypt() {
        return imageTextMessage.getImageMessageOptions().getMaxMessageCharsEncrypt();
    }

    public int getMessageMaxLengthDecrypt() {
        return imageTextMessage.getImageMessageOptions().getMaxMessageCharsDecrypt();
    }
}
