package pt.ual.android.bhjencryption.ui.resources;

import java.lang.reflect.Field;

public class ResourcesUtils {

    /**
     * Método mais eficiente de fazer retrieve de resources
     * @param variableName - name of drawable, e.g R.drawable.<b>image</b>
     * @param с - class of resource, e.g R.drawable.class or R.raw.class
     * @return integer id of resource
     */
    public static int getResId(String variableName, Class<?> с) {
        Field field = null;
        int resId = 0;
        try {
            field = с.getField(variableName);
            try {
                resId = field.getInt(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resId;
    }
}
