package gamecontainer.strategy;

import gamecontainer.Coord;
import gamecontainer.Markers;

/**
 * Created by Василий on 14.06.2015.
 */
public abstract class StrategyPlayer {
    Markers[][] board;
    Markers marker;

    StrategyPlayer(Markers[][] board, Markers selfMarker) {
        this.board = board;
        this.marker = selfMarker;
    }

    void setBoard(Markers[][] board) {
        this.board = board;
    }

    protected boolean isEmpty(int row, int column) {
        return (board != null && board[row][column] == Markers.EMPTY);
    }

    protected int getSizeBoard() {
        return (board != null) ? board.length : 0;
    }

    public abstract Coord getPosition();
}
