package pt.ual.android.bhjencryption.utils.cipher;

import androidx.annotation.NonNull;

public class TheRightBattleshipCipherCoord {
    public static final String BATTLESHIP_MAP_COLUM_COORD = "ABCDE";
    public static final String BATTLESHIP_MAP_LINE_COORD = "12345";

    private int y; // line
    private int x; // column

    public TheRightBattleshipCipherCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TheRightBattleshipCipherCoord(String coord) throws Exception {
        this.setCoord(coord);
    }

    private void setCoord(String coord) throws Exception {
        if(!TheRightBattleshipCipherCoord.validateCoord(coord))
            throw new Exception("Fatal cipher mismatch: invalid coordinate at BattleShip Coord creation");

        this.x = Character.getNumericValue(coord.charAt(0));
        this.y = Character.getNumericValue(coord.charAt(1));
    }

    public static boolean validateCoords(String[] coords) {
        for(int i = 0; i < coords.length; i++) {
            validateCoord(coords[i]);
        }

        return true;
    }

    public static boolean validateCoord(String coord) {
        if(coord != null)
            if(coord.length() == 2)
                if(TheRightBattleshipCipherCoord.BATTLESHIP_MAP_COLUM_COORD.indexOf(coord.charAt(0)) != -1)
                    if(TheRightBattleshipCipherCoord.BATTLESHIP_MAP_LINE_COORD.indexOf(coord.charAt(1)) != -1)
                        return true;

        return false;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TheRightBattleshipCipherCoord.BATTLESHIP_MAP_COLUM_COORD.charAt(x));
        sb.append(TheRightBattleshipCipherCoord.BATTLESHIP_MAP_LINE_COORD.charAt(y));

        return sb.toString();
    }
}
