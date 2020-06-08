package pt.ual.android.bhjencryption.cipher;

import java.util.HashMap;
import java.util.Map;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class RomanArabCipher extends Cipher {
    public RomanArabCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ALPHABET_LOWER, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.NUMERIC_AND_ROMAN, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
            String[] letra = getCipherMessage().getMessageAsText().split(" ");
            for(String i : letra){

                if(Character.isDigit(i.charAt(0)) && (Integer.parseInt(i) < 1 || Integer.parseInt(i) > 21)){
                    return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_INVALID_FORMAT));
                }

                if(Character.isAlphabetic(i.charAt(0))){
                    if(i.equals("I") || i.equals("II") || i.equals("III") || i.equals("IV") || i.equals("V")){}
                    else{
                        return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_INVALID_FORMAT));
                    }
                }


            }
        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getCipherMessage().getMessageAsText()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getCipherMessage().getMessageAsText()));
    }

    public static String encrypt(String enc) {
        StringBuilder encoded = new StringBuilder();

        Map<Character, String> alfabeto = new HashMap<>();
        alfabeto.put('a', "I");
        alfabeto.put('b', "1");
        alfabeto.put('c', "2");
        alfabeto.put('d', "3");
        alfabeto.put('e', "II");
        alfabeto.put('f', "4");
        alfabeto.put('g', "5");
        alfabeto.put('h', "6");
        alfabeto.put('i', "III");
        alfabeto.put('j', "7");
        alfabeto.put('k', "8");
        alfabeto.put('l', "9");
        alfabeto.put('m', "10");
        alfabeto.put('n', "11");
        alfabeto.put('o', "IV");
        alfabeto.put('p', "12");
        alfabeto.put('q', "13");
        alfabeto.put('r', "14");
        alfabeto.put('s', "15");
        alfabeto.put('t', "16");
        alfabeto.put('u', "V");
        alfabeto.put('v', "17");
        alfabeto.put('w', "18");
        alfabeto.put('x', "19");
        alfabeto.put('y', "20");
        alfabeto.put('z', "21");

        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                String val = alfabeto.get(Character.toLowerCase(i));
                encoded.append(val);
                encoded.append(" ");
            } else {
                encoded.append(i);
                encoded.append(" ");
            }
        }

        return encoded.toString();
    }

    public static String decrypt(String enc) {
        StringBuilder decoded = new StringBuilder();

        Map<String, Character> alfabeto = new HashMap<>();
        alfabeto.put("I", 'a');
        alfabeto.put("1", 'b');
        alfabeto.put("2", 'c');
        alfabeto.put("3", 'd');
        alfabeto.put("II", 'e');
        alfabeto.put("4", 'f');
        alfabeto.put("5", 'g');
        alfabeto.put("6", 'h');
        alfabeto.put("III", 'i');
        alfabeto.put("7", 'j');
        alfabeto.put("8", 'k');
        alfabeto.put("9", 'l');
        alfabeto.put("10", 'm');
        alfabeto.put("11", 'n');
        alfabeto.put("IV", 'o');
        alfabeto.put("12", 'p');
        alfabeto.put("13", 'q');
        alfabeto.put("14", 'r');
        alfabeto.put("15", 's');
        alfabeto.put("16", 't');
        alfabeto.put("V", 'u');
        alfabeto.put("17", 'v');
        alfabeto.put("18", 'w');
        alfabeto.put("19", 'x');
        alfabeto.put("20", 'y');
        alfabeto.put("21", 'z');

        String[] letra = enc.split(" ");

        for (String i : letra) {
            char val = alfabeto.get(i);
            decoded.append(val);
        }

        return decoded.toString();
    }
}
