package pt.ual.android.bhjencryption.utils.cipher;

import java.util.HashMap;
import java.util.Map;

public class CipherUtils {

    /*DESENCRIPTADORES*/

    public static String cesarDecode(String enc, int offset) {
        return cesarEncode(enc, 26 - offset);
    }

    public static String morseDecode(String enc) {
        StringBuilder decoded = new StringBuilder();

        Map<String, Character> alfabeto = new HashMap<>();
        alfabeto.put("._", 'a');
        alfabeto.put("_...", 'b');
        alfabeto.put("_._.", 'c');
        alfabeto.put("_..", 'd');
        alfabeto.put(".", 'e');
        alfabeto.put(".._.", 'f');
        alfabeto.put("__.", 'g');
        alfabeto.put("....", 'h');
        alfabeto.put("..", 'i');
        alfabeto.put(".___", 'j');
        alfabeto.put("_._", 'k');
        alfabeto.put("._..", 'l');
        alfabeto.put("__", 'm');
        alfabeto.put("_.", 'n');
        alfabeto.put("___", 'o');
        alfabeto.put(".__.", 'p');
        alfabeto.put("__._", 'q');
        alfabeto.put("._.", 'r');
        alfabeto.put("...", 's');
        alfabeto.put("_", 't');
        alfabeto.put(".._", 'u');
        alfabeto.put("..._", 'v');
        alfabeto.put(".__", 'w');
        alfabeto.put("_.._", 'x');
        alfabeto.put("_.__", 'y');
        alfabeto.put("__..", 'z');
        alfabeto.put(".____", '1');
        alfabeto.put("..___", '2');
        alfabeto.put("...__", '3');
        alfabeto.put("...._", '4');
        alfabeto.put(".....", '5');
        alfabeto.put("_....", '6');
        alfabeto.put("__...", '7');
        alfabeto.put("___..", '8');
        alfabeto.put("____.", '9');
        alfabeto.put("_____", '0');

        String[] letra = enc.split(" ");

        for (String i : letra) {
            char val = alfabeto.get(i);
            decoded.append(val);
        }

        return decoded.toString();
    }

    public static String romaArabDecode(String enc) {
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

    /*ENCRIPTADORES*/

    public static String cesarEncode(String enc, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }

    public static String morseEncode(String enc) {
        StringBuilder encoded = new StringBuilder();

        Map<Character, String> alfabeto = new HashMap<>();
        alfabeto.put('a', "._");
        alfabeto.put('b', "_...");
        alfabeto.put('c', "_._.");
        alfabeto.put('d', "_..");
        alfabeto.put('e', ".");
        alfabeto.put('f', ".._.");
        alfabeto.put('g', "__.");
        alfabeto.put('h', "....");
        alfabeto.put('i', "..");
        alfabeto.put('j', ".___");
        alfabeto.put('k', "_._");
        alfabeto.put('l', "._..");
        alfabeto.put('m', "__");
        alfabeto.put('n', "_.");
        alfabeto.put('o', "___");
        alfabeto.put('p', ".__.");
        alfabeto.put('q', "__._");
        alfabeto.put('r', "._.");
        alfabeto.put('s', "...");
        alfabeto.put('t', "_");
        alfabeto.put('u', ".._");
        alfabeto.put('v', "..._");
        alfabeto.put('w', ".__");
        alfabeto.put('x', "_.._");
        alfabeto.put('y', "_.__");
        alfabeto.put('z', "__..");
        alfabeto.put('1', ".____");
        alfabeto.put('2', "..___");
        alfabeto.put('3', "...__");
        alfabeto.put('4', "...._");
        alfabeto.put('5', ".....");
        alfabeto.put('6', "_....");
        alfabeto.put('7', "__...");
        alfabeto.put('8', "___..");
        alfabeto.put('9', "____.");
        alfabeto.put('0', "_____");

        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i) || Character.isDigit(i)) {
                String val = alfabeto.get(Character.toLowerCase(i));
                encoded.append(val);
                encoded.append(" ");
            }
        }

        return encoded.toString();
    }

    public static String romaArabEncode(String enc) {
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

}
