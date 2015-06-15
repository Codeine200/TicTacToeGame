package gamecontainer;

/**
 * Created by Василий on 12.06.2015.
 */
public enum Markers {
    CROSS('x'),
    ZERO('o'),
    EMPTY(' ');

    private char s;
    Markers(char s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return String.valueOf(s);
    }
}
