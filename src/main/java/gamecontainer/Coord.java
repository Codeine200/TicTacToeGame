package gamecontainer;

/**
 * Created by Василий on 13.06.2015.
 */
public class Coord {
    private int row;
    private int column;

    public Coord(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
