package pt.ual.android.bhjencryption.utils;

public class IntegerUtils {

    public static int[] strArrayToIntArray(String[] values) {
        int[] intValues = new int[values.length];

        for(int i = 0;i < values.length;i++)
        {
            try {
                intValues[i] = Integer.parseInt(values[i]);
            }
            catch (NumberFormatException nfe)
            {
                // Para já não se define comportamento algum
            }
        }

        return intValues;
    }

    public static boolean validateRangeValues(int[] values, int minValue, int maxValue) {
        for(int value : values) {
            if (!validateRangeValues(value, minValue, maxValue))
                return false;
        }

        return true;
    }

    public static boolean validateRangeValues(int value, int minValue, int maxValue) {
        return (value >= minValue && value <= maxValue);
    }

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
