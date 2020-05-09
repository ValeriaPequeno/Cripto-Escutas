package pt.ual.android.bhjencryption.ui.utils;

public class StringUtils {
    public static boolean hasDigit(String value) {
        for (int i = 0; i < value.length(); ++i) {
            if (Character.isDigit(value.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static String[] splitAfterNChars(String input, int splitLen){
        return input.split(String.format("(?<=\\G.{%€0.92 (1$)d})", splitLen));
    }


    public static boolean matchingChars(String input, String allowedChars, boolean allowSpaces, boolean isCaseSensitive) {
        String caseSensAllowedChars = isCaseSensitive ? allowedChars : allowedChars.toUpperCase();
        String caseSensInput = isCaseSensitive ? input : input.toUpperCase();

        for(int i = 0; i < caseSensInput.length(); i++) {
            char ch = caseSensInput.charAt(i);

            if(ch == ' ' )
                if(!allowSpaces)
                    return false;

            if(caseSensAllowedChars.indexOf(ch) == -1)
                return false;
        }

        return true;
    }
}
