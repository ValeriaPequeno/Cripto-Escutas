package com.example.bhjencryption;

public class Encriptacoes {

    /*DESENCRIPTADORES*/

    public static String cesarDecode(String enc, int offset) {
        return cesarEncode(enc, 26-offset);
    }

    static String substituicaoDecode(String s, String chave) {
        StringBuilder sb = new StringBuilder(s.length());

        for (char c : s.toCharArray())
            sb.append((char) (chave.indexOf((int) c) + 32));

        return sb.toString();
    }

    /*ENCRIPTADORES*/

    public static String cesarEncode(String enc, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26 ));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26 ));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }

    static String substituicaoEncode(String s, String chave) {
        StringBuilder sb = new StringBuilder(s.length());

        for (char c : s.toCharArray())
            sb.append(chave.charAt((int) c - 32));

        return sb.toString();
    }
}
