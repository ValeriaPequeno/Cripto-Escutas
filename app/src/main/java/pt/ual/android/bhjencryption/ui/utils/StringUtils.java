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
}
