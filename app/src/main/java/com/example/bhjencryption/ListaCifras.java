package com.example.bhjencryption;

public class ListaCifras {

    /*DESENCRIPTADORES*/

    public static String cesarDecode(String enc, int offset) {
        return cesarEncode(enc, 26-offset);
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


}
