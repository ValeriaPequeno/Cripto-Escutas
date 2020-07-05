package pt.ual.android.bhjencryption.cipher;

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
        if(!TheRightBattleshipCipherCoord.validateCoord(coord, false))
            throw new Exception("Fatal cipher mismatch: invalid coordinate at BattleShip Coord creation. Contact the App support team.");

        this.x = TheRightBattleshipCipherCoord.BATTLESHIP_MAP_COLUM_COORD.indexOf(coord.toUpperCase().charAt(0));
        this.y = TheRightBattleshipCipherCoord.BATTLESHIP_MAP_LINE_COORD.indexOf(coord.toUpperCase().charAt(1));
    }

    public static boolean validateCoords(String[] coords, boolean isCaseSensitive) {
        for(int i = 0; i < coords.length; i++) {
            if(!validateCoord(coords[i], isCaseSensitive))
                return false;
        }

        return true;
    }

    public static boolean validateCoord(String coord, boolean isCaseSensitive) {
        String caseSensitiveCoord = isCaseSensitive ? coord : coord.toUpperCase();

        if(caseSensitiveCoord != null) {
            if(caseSensitiveCoord.length() == 1 && caseSensitiveCoord.compareTo(".") == 0)
                return true;

            if(caseSensitiveCoord.length() == 2)
                if(TheRightBattleshipCipherCoord.BATTLESHIP_MAP_COLUM_COORD.indexOf(caseSensitiveCoord.charAt(0)) != -1)
                    if(TheRightBattleshipCipherCoord.BATTLESHIP_MAP_LINE_COORD.indexOf(caseSensitiveCoord.charAt(1)) != -1)
                        return true;
        }

        return false;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TheRightBattleshipCipherCoord.BATTLESHIP_MAP_COLUM_COORD.charAt(this.x));
        sb.append(TheRightBattleshipCipherCoord.BATTLESHIP_MAP_LINE_COORD.charAt(this.y));

        return sb.toString();
    }
}
