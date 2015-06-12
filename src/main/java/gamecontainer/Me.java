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

    public void setCoord(int row, int column) {
        setRow(row);
        setColumn(column);
    }

    @Override
    public Coord getCoord() {
        return new Coord(row, column);
    }

    @Override
    public Markers getMarker() {
        return marker;
    }
}
