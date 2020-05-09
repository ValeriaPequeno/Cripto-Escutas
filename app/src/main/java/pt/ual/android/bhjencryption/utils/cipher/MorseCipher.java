package pt.ual.android.bhjencryption.utils.cipher;

import java.util.HashMap;
import java.util.Map;

public class MorseCipher extends Cipher {

    public MorseCipher(String message) {
        super(message);
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return null;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return null;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage()));
    }

    public static String encrypt(String enc) {
        StringBuilder encoded = new StringBuilder();

        Map<Character, String> alfabeto = new HashMap<>();
        alfabeto.put('a', ".-");
        alfabeto.put('b', "-...");
        alfabeto.put('c', "-.-.");
        alfabeto.put('d', "-..");
        alfabeto.put('e', ".");
        alfabeto.put('f', "..-.");
        alfabeto.put('g', "--.");
        alfabeto.put('h', "....");
        alfabeto.put('i', "..");
        alfabeto.put('j', ".---");
        alfabeto.put('k', "-.-");
        alfabeto.put('l', ".-..");
        alfabeto.put('m', "--");
        alfabeto.put('n', "-.");
        alfabeto.put('o', "---");
        alfabeto.put('p', ".--.");
        alfabeto.put('q', "--.-");
        alfabeto.put('r', ".-.");
        alfabeto.put('s', "...");
        alfabeto.put('t', "-");
        alfabeto.put('u', "..-");
        alfabeto.put('v', "...-");
        alfabeto.put('w', ".--");
        alfabeto.put('x', "-..-");
        alfabeto.put('y', "-.--");
        alfabeto.put('z', "--..");
        alfabeto.put('1', ".----");
        alfabeto.put('2', "..---");
        alfabeto.put('3', "...--");
        alfabeto.put('4', "....-");
        alfabeto.put('5', ".....");
        alfabeto.put('6', "-....");
        alfabeto.put('7', "--...");
        alfabeto.put('8', "---..");
        alfabeto.put('9', "----.");
        alfabeto.put('0', "-----");
        alfabeto.put('.', "·-·-·-");
        alfabeto.put(',', "--··--");
        alfabeto.put('?', "··--··");
        alfabeto.put('!', "-·-·--");
        alfabeto.put('/', "-··-·");
        alfabeto.put('(', "-·--·");
        alfabeto.put(')', "-·--·-");
        alfabeto.put('&', "·-···");
        alfabeto.put(':', "---···");
        alfabeto.put(';', "-·-·-·");
        alfabeto.put('=', "-···-");
        alfabeto.put('-', "-····-");
        alfabeto.put('_', "··--·-");
        alfabeto.put('"', "·-··-·");
        alfabeto.put('$', "···-··-");
        alfabeto.put('@', "·--·-·");
        alfabeto.put('\'', "·----·");

        String[] palavras = enc.split(" ");

        for(String pal : palavras){
            for (char i : pal.toCharArray()) {
                if(i == 'à' || i == 'á' || i == 'ã' || i == 'â'){
                    String val = alfabeto.get(Character.toLowerCase('a'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(i == 'ç'){
                    String val = alfabeto.get(Character.toLowerCase('c'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(i == 'é' || i == 'ê'){
                    String val = alfabeto.get(Character.toLowerCase('e'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(i == 'í'){
                    String val = alfabeto.get(Character.toLowerCase('i'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(i == 'ó' || i == 'ô' || i == 'õ'){
                    String val = alfabeto.get(Character.toLowerCase('o'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else if(i == 'ú'){
                    String val = alfabeto.get(Character.toLowerCase('u'));
                    encoded.append(val);
                    encoded.append(" ");
                }
                else{
                    String val = alfabeto.get(Character.toLowerCase(i));
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
        alfabeto.put(".-", 'a');
        alfabeto.put("-...", 'b');
        alfabeto.put("-.-.", 'c');
        alfabeto.put("-..", 'd');
        alfabeto.put(".", 'e');
        alfabeto.put("..-.", 'f');
        alfabeto.put("--.", 'g');
        alfabeto.put("....", 'h');
        alfabeto.put("..", 'i');
        alfabeto.put(".---", 'j');
        alfabeto.put("-.-", 'k');
        alfabeto.put("._..", 'l');
        alfabeto.put("__", 'm');
        alfabeto.put("-.", 'n');
        alfabeto.put("---", 'o');
        alfabeto.put(".--.", 'p');
        alfabeto.put("--.-", 'q');
        alfabeto.put(".-.", 'r');
        alfabeto.put("...", 's');
        alfabeto.put("-", 't');
        alfabeto.put("..-", 'u');
        alfabeto.put("...-", 'v');
        alfabeto.put(".--", 'w');
        alfabeto.put("_.._", 'x');
        alfabeto.put("-.--", 'y');
        alfabeto.put("--..", 'z');
        alfabeto.put(".----", '1');
        alfabeto.put("..---", '2');
        alfabeto.put("...--", '3');
        alfabeto.put("....-", '4');
        alfabeto.put(".....", '5');
        alfabeto.put("-....", '6');
        alfabeto.put("--...", '7');
        alfabeto.put("---..", '8');
        alfabeto.put("----.", '9');
        alfabeto.put("-----", '0');
        alfabeto.put("·-·-·-", '.');
        alfabeto.put("--··--", ',');
        alfabeto.put("··--··", '?');
        alfabeto.put("-·-·--", '!');
        alfabeto.put("-··-·", '/');
        alfabeto.put("-·--·", '(');
        alfabeto.put("-·--·-", ')');
        alfabeto.put("·-···", '&');
        alfabeto.put("---···", ':');
        alfabeto.put("-·-·-·", ';');
        alfabeto.put("-···-", '=');
        alfabeto.put("-····-", '-');
        alfabeto.put("··--·-", '_');
        alfabeto.put("·-··-·", '"');
        alfabeto.put("···-··-", '$');
        alfabeto.put("·--·-·", '@');
        alfabeto.put("·----·", '\'');

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
