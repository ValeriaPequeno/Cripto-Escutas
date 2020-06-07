package pt.ual.android.bhjencryption.cipher;

import java.util.HashMap;
import java.util.Map;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class SmsCipher extends Cipher{
    public SmsCipher(CipherMessage cipherMessage) {
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
            if(!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.NUMERIC_NATURAL, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
            String[] letra = getCipherMessage().getMessageAsText().split(" ");
            for (String i : letra){
                if((Integer.valueOf(String.valueOf(i.charAt(0))) == 7 || Integer.valueOf(String.valueOf(i.charAt(0))) == 9) && i.length() > 4 ||
                        (Integer.valueOf(String.valueOf(i.charAt(0))) == 1 || Integer.valueOf(String.valueOf(i.charAt(0))) == 2 ||
                                Integer.valueOf(String.valueOf(i.charAt(0))) == 3 || Integer.valueOf(String.valueOf(i.charAt(0))) == 4 ||
                                Integer.valueOf(String.valueOf(i.charAt(0))) == 5 || Integer.valueOf(String.valueOf(i.charAt(0))) == 6 ||
                                Integer.valueOf(String.valueOf(i.charAt(0))) == 8) && i.length() > 3){
                    return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_INVALID_FORMAT));
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
        alfabeto.put('A', "2");
        alfabeto.put('B', "22");
        alfabeto.put('C', "222");
        alfabeto.put('D', "3");
        alfabeto.put('E', "33");
        alfabeto.put('F', "333");
        alfabeto.put('G', "4");
        alfabeto.put('H', "44");
        alfabeto.put('I', "444");
        alfabeto.put('J', "5");
        alfabeto.put('K', "55");
        alfabeto.put('L', "555");
        alfabeto.put('M', "6");
        alfabeto.put('N', "66");
        alfabeto.put('O', "666");
        alfabeto.put('P', "7");
        alfabeto.put('Q', "77");
        alfabeto.put('R', "777");
        alfabeto.put('S', "7777");
        alfabeto.put('T', "8");
        alfabeto.put('U', "88");
        alfabeto.put('V', "888");
        alfabeto.put('W', "9");
        alfabeto.put('X', "99");
        alfabeto.put('Y', "999");
        alfabeto.put('Z', "9999");

        String[] palavras = enc.split(" ");

        for(String pal : palavras){
            for (char i : pal.toCharArray()) {
                char l = Character.toUpperCase(i);
                if(l == 'À' || l == 'Á' || l == 'Ã' || l == 'Â'){
                    String val = alfabeto.get(Character.toLowerCase('A'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(l == 'Ç'){
                    String val = alfabeto.get(Character.toLowerCase('C'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(l == 'É' || l == 'Ê'){
                    String val = alfabeto.get(Character.toLowerCase('E'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(l == 'Í'){
                    String val = alfabeto.get(Character.toLowerCase('I'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(l == 'Ó' || l == 'Õ' || l == 'Ô'){
                    String val = alfabeto.get(Character.toLowerCase('O'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(l == 'Ú'){
                    String val = alfabeto.get(Character.toLowerCase('U'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else{
                    String val = alfabeto.get(l);
                    encoded.append(val);
                    encoded.append(" ");
                }
            }
            encoded.append("/");
        }

        return encoded.toString();
    }

    public static String decrypt(String enc) {
        StringBuilder decoded = new StringBuilder();

        Map<String, Character> alfabeto = new HashMap<>();
        alfabeto.put("2", 'A');
        alfabeto.put("22", 'B');
        alfabeto.put("222", 'C');
        alfabeto.put("3", 'D');
        alfabeto.put("33", 'E');
        alfabeto.put("333", 'F');
        alfabeto.put("4", 'G');
        alfabeto.put("44", 'H');
        alfabeto.put("444", 'I');
        alfabeto.put("5", 'J');
        alfabeto.put("55", 'K');
        alfabeto.put("555", 'L');
        alfabeto.put("6", 'M');
        alfabeto.put("66", 'N');
        alfabeto.put("666", 'O');
        alfabeto.put("7", 'P');
        alfabeto.put("77", 'Q');
        alfabeto.put("777", 'R');
        alfabeto.put("7777", 'S');
        alfabeto.put("8", 'T');
        alfabeto.put("88", 'U');
        alfabeto.put("888", 'V');
        alfabeto.put("9", 'W');
        alfabeto.put("99", 'X');
        alfabeto.put("999", 'Y');
        alfabeto.put("9999", 'Z');

        String[] palavras = enc.split("/");

        for(String pal : palavras){
            String[] letra = pal.split(" ");

            for (String i : letra) {
                char val = alfabeto.get(i);
                decoded.append(val);
            }
            decoded.append(" ");
        }

        return decoded.toString();
    }
}