package pt.ual.android.bhjencryption.utils.cipher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CipherUtils {
    public static final String ASCII_ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String ASCII_ALPHABET_LOWER_AND_NUMERIC = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String ALPHABET_LOWER = "aàáâãbcçdeéêfghiíjklmnoóôõpqrstuúvwxyz";
    public static final String NUMERIC = "0123456789";
    public static final String ALPHABET_LOWER_AND_NUMERIC = "aàáâãbcçdeéêfghiíjklmnoóôõpqrstuúvwxyz0123456789";

    /*ENCRIPTADORES*/

    public static String melroEnc(String enc) {
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Random random = new Random();
        char[] inputLetters = enc.toCharArray();
        String output="";

        for (int i=0; i<inputLetters.length; i++){

            int randomChartPosition = random.nextInt(alfabeto.length -1);
            output += inputLetters[i] + "" + alfabeto[randomChartPosition];
        }
        return output;
    }

    public static String melro2Enc(String enc) {
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Random random = new Random();
        char[] inputLetters = enc.toCharArray();
        String output="";

        for (int i=0; i<inputLetters.length; i++){

            int randomChartPosition = random.nextInt(alfabeto.length -1);
            output += inputLetters[i] + "" + alfabeto[randomChartPosition]+ "" + alfabeto[randomChartPosition];
        }
        return output;
    }

    public static String metadeEnc(String enc) {

        String inputLetters = enc.replaceAll(" ","");
        String output="";
        String output1="";
        for (int i = 0; i < inputLetters.length(); i += 2) {

            output += inputLetters.charAt(i);
        }

        for (int i = 1; i < inputLetters.length(); i += 2) {

            output1 += inputLetters.charAt(i);
        }

        return output +" "+ output1;
    }

    public static String pFalsaEnc(String enc) {
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Random random = new Random();
        String output="";
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {
            int randomChartPosition = random.nextInt(alfabeto.length -1);
            if (!inputLetter.isEmpty()) {
                output +=  alfabeto[randomChartPosition]+ "" + inputLetter+ " ";

            }
        }
        return output;
    }

    public static String ulFalsaEnc(String enc) {
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Random random = new Random();
        String output="";
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {
            int randomChartPosition = random.nextInt(alfabeto.length -1);
            if (!inputLetter.isEmpty()) {
                output += inputLetter + alfabeto[randomChartPosition]+ " " ;
            }
        }
        return output;
    }

    public static String caracolEnc(String enc, int size) {
        StringBuilder output = new StringBuilder();
        String word = enc.replaceAll(" ", "").toUpperCase();
        char[][] spiral = new char[size][size];
        char[] alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random random = new Random();
        int diff = (size * size) - word.length();
        int value = 0, minCol = 0, maxCol = size - 1, minRow = 0, maxRow = size - 1;

        if (word.length() < size * size) {
            for (int i = 1; i <= diff; i++) {
                int randomChartPosition = random.nextInt(alfabeto.length - 1);
                word = word + alfabeto[randomChartPosition];
            }
        }

        if (word.length() > size * size) {
            System.out.print("O nº da altura/largura da tabela é muito pequeno!");
            System.exit(0);
        }

        while (value < size * size) {
            for (int i = minRow; i <= maxRow; i++) {
                spiral[i][minCol] = word.charAt(value);
                value++;
            }

            for (int i = minCol + 1; i <= maxCol; i++) {
                spiral[maxRow][i] = word.charAt(value);
                value++;
            }

            for (int i = maxRow - 1; i >= minRow; i--) {
                spiral[i][maxCol] = word.charAt(value);
                value++;
            }

            for (int i = maxCol - 1; i >= minCol + 1; i--) {
                spiral[minRow][i] = word.charAt(value);
                value++;
            }

            minCol++;
            minRow++;
            maxCol--;
            maxRow--;

        }

        for (char[] spiral1 : spiral) {
            for (int j = 0; j < spiral.length; j++) {
                System.out.print(spiral1[j] + " ");
                output.append(spiral1[j]);
            }
            System.out.println();
        }

        return output.toString();
    }

    /*DESENCRIPTADORES*/

    public static String melroDec(String enc) {

        char[] inputLetters = enc.toCharArray();
        String output="";
        for (int i = 0; i < inputLetters.length; i += 2) {
            //char result = input.charAt(i);
            output += inputLetters[i];
        }
        return output;
    }

    public static String melro2Dec(String enc) {

        char[] inputLetters = enc.toCharArray();
        String output="";
        for (int i = 0; i < inputLetters.length; i += 3) {
            //char result = input.charAt(i);
            output += inputLetters[i];
        }
        return output;
    }

    public static String metadeDec(String enc) {
        //String exemplo = "CAAOOORSA HMRSCRIT";
        String[] saida = enc.split(" ");

        String s = "";
        String a = saida[0];
        String b = saida[1];
        int i = 0;
        while (i < a.length() && i < b.length()){
            s += a.charAt(i) +""+ b.charAt(i);
            i++;
        }
        while (i < a.length() ){
            s += a.charAt(i);
            i++;
        }
        while (i < b.length()){
            s += b.charAt(i);
            i++;
        }
        return s;
    }

    public static String pFalsaDec(String enc) {

        String output="";
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {

            if (!inputLetter.isEmpty()) {
                output += inputLetter.substring(1) + " ";
            }
        }
        return output;
    }

    public static String ulFalsaDec(String enc) {

        String output="";
        String [] inputLetters;
        inputLetters = enc.split("\\s+");
        for (String inputLetter : inputLetters) {

            if (!inputLetter.isEmpty()) {
                output += inputLetter.substring(0, inputLetter.length()-1) + " ";
            }
        }
        return output;
    }

    public static String caracolDec(String enc, int size) {
        char[][] spiral = new char[size][size];
        StringBuilder wordBuilder = new StringBuilder();

        int n = 0;
        for (char[] spiral1 : spiral) {
            for (int x = 0; x < spiral1.length; x++) {
                spiral1[x] = enc.charAt(n);
                n++;
            }
        }

        int value = 0, minCol = 0, maxCol = size - 1, minRow = 0, maxRow = size - 1;

        while (value < size * size) {
            for (int i = minRow; i <= maxRow; i++) {
                wordBuilder.append(spiral[i][minCol]);
                value++;
            }

            for (int i = minCol + 1; i <= maxCol; i++) {
                wordBuilder.append(spiral[maxRow][i]);
                value++;
            }

            for (int i = maxRow - 1; i >= minRow; i--) {
                wordBuilder.append(spiral[i][maxCol]);
                value++;
            }

            for (int i = maxCol - 1; i >= minCol + 1; i--) {
                wordBuilder.append(spiral[minRow][i]);
                value++;
            }

            minCol++;
            minRow++;
            maxCol--;
            maxRow--;

        }

        return wordBuilder.toString();
    }

}
