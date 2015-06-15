package gamecontainer.strategy;

import gamecontainer.Coord;
import gamecontainer.Markers;

/**
 * Created by Василий on 15.06.2015.
 */
public class checkProtectStrategy extends StrategyPlayer {

    public checkProtectStrategy(Markers[][] board, Markers selfMarker) {
        super(board, selfMarker);
    }
    @Override
    public Coord getPosition() {
        Coord coord = checkSquareForProtect(marker);
        return (coord != null) ? new Coord(coord.getRow(), coord.getColumn()) : null;
    }

    protected Coord checkSquareForProtect(Markers marker) {
        int SIZE = getSizeBoard();
        int count = 0;
        // horizontally
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                if(!isEmpty(i, j) && board[i][j] != marker) count++;
            }
            if(count == SIZE - 1) {
                // find square for protect
                for(int k=0; k<SIZE; k++)
                    if(isEmpty(i, k)) {
                        return new Coord(i, k);
                    }
            }
            count = 0;
        }

        count = 0;
        // vertical
        for(int j=0; j<SIZE; j++) {
            for(int i=0; i<SIZE; i++) {
                if(!isEmpty(i, j) && board[i][j] != marker) count++;
            }
            if(count == SIZE - 1) {
                // find square for protect
                for(int k=0; k<SIZE; k++)
                    if(isEmpty(k, j)) {
                        return new Coord(k, j);
                    }
            }
            count = 0;
        }

        // diagonal
        count = 0;
        for(int i=0; i<SIZE; i++) {
            if(!isEmpty(i, i) && board[i][i] != marker) count++;
        }

        if(count == SIZE - 1) {
            // find square for protect
            for(int k=0; k<SIZE; k++)
                if(isEmpty(k, k)) {
                    return new Coord(k, k);
                }
        }

        // anti diagonal
        count = 0;
        for(int i=0; i<SIZE; i++) {
            if(!isEmpty(i, SIZE-1-i) && board[i][SIZE-1-i] != marker) count++;
        }

        if(count == SIZE - 1) {
            // find square for protect
            for(int k=0; k<SIZE; k++)
                if(isEmpty(k, SIZE - 1 - k)) {
                    return new Coord(k, SIZE - 1 - k);
                }
        }

        return null;
    }
}
