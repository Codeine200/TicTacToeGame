package gamecontainer;

/**
 * Created by Василий on 12.06.2015.
 */
public class Me implements Player {
    private Markers marker;
    private int row;
    private int column;
    public Me(Markers value) {
        marker = value;
    }

    public void setRow(int value) {
        row = value;
    }

    public void setColumn(int value) {
        column = value;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public Markers getMarker() {
        return marker;
    }
}
