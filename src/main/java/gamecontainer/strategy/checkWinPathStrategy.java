package gamecontainer.strategy;

import gamecontainer.Coord;
import gamecontainer.Markers;

/**
 * Created by Василий on 15.06.2015.
 */
public class checkWinPathStrategy extends checkProtectStrategy {

    public checkWinPathStrategy(Markers[][] board, Markers selfMarker) {
        super(board, selfMarker);
    }
    @Override
    public Coord getPosition() {
        Markers tempMarker = (marker == Markers.TOE) ? Markers.CROSS : Markers.TOE;
        Coord coord = checkSquareForProtect(tempMarker);
        return (coord != null) ? new Coord(coord.getRow(), coord.getColumn()) : null;
    }


}
