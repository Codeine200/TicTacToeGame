package gamecontainer.strategy;

import gamecontainer.Coord;
import gamecontainer.Markers;
import gamecontainer.Move;

/**
 * Created by Василий on 15.06.2015.
 */
public class checkProtectStrategy extends StrategyPlayer {
    private int countEmptySquare = 0;
    private int emptySquareRow = -1;
    private int emptySquareColumn = -1;

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
        Coord coord = null;
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                if(!isEmpty(i, j) && board[i][j] != marker) {
                    for(Move move : Move.values()) {
                        coord = protectPath(marker, move, i, j);
                        if(coord != null)  return coord;
                    }
                }
            }
        }
        return null;
    }

    private Coord protectPath(Markers marker, Move value, int row, int column) {
        int SIZE = getSizeBoard();
        int curr_row = row;
        int curr_column = column;
        clearEmptySquareData();
        switch (value) {
            case NORTH: {
                if(row - SIZE + 1 < 0)
                    return null;

                for(int j=0; j<SIZE - 1; j++) {
                    curr_row = row - 1 - j;
                    if(!setEmptySquareData(marker, curr_row, curr_column))
                        return null;
                }
                break;
            }
            case SOUTH: {
                if(row + SIZE - 1 >= SIZE)
                    return null;

                for(int j=0; j<SIZE - 1; j++) {
                    curr_row = row + 1 + j;
                    if (!setEmptySquareData(marker, curr_row, curr_column))
                        return null;
                }
                break;
            }
            case WEST: {
                if(column - SIZE + 1 < 0)
                    return null;

                for(int i=0; i<SIZE - 1; i++) {
                    curr_column = column - 1 - i;
                    if (!setEmptySquareData(marker, curr_row, curr_column))
                        return null;
                }
                break;
            }
            case EAST: {
                if(column + SIZE - 1 >= SIZE)
                    return null;

                for(int i=0; i<SIZE - 1; i++) {
                    curr_column = column + 1 + i;
                    if (!setEmptySquareData(marker, curr_row, curr_column))
                        return null;
                }
                break;
            }
            case NORTH_WEST: {
                if(row - SIZE + 1 < 0 || column - SIZE + 1 < 0)
                    return null;

                for(int k=0; k<SIZE - 1; k++) {
                    curr_row = row - 1 - k;
                    curr_column = column - 1 - k;
                    if (!setEmptySquareData(marker, curr_row, curr_column))
                        return null;
                }
                break;
            }
            case NORTH_EAST: {
                if(row - SIZE + 1 < 0 || column + SIZE - 1 >= SIZE)
                    return null;

                for(int k=0; k<SIZE - 1; k++) {
                    curr_row = row - 1 - k;
                    curr_column = column + 1 + k;
                    if (!setEmptySquareData(marker, curr_row, curr_column))
                        return null;
                }
                break;
            }
            case SOUTH_WEST: {
                if(row + SIZE - 1 >= SIZE  || column - SIZE + 1 < 0)
                    return null;

                for(int k=0; k<SIZE - 1; k++) {
                    curr_row = row + 1 + k;
                    curr_column = column - 1 - k;
                    if (!setEmptySquareData(marker, curr_row, curr_column))
                        return null;
                }
                break;
            }
            case SOUTH_EAST: {
                if(row + SIZE - 1 >= SIZE  || column + SIZE - 1 >= SIZE)
                    return null;

                for(int k=0; k<SIZE - 1; k++) {
                    curr_row = row + 1 + k;
                    curr_column = column + 1 + k;
                    if (!setEmptySquareData(marker, curr_row, curr_column))
                        return null;
                };
                break;
            }
        }

        return (countEmptySquare == SIZE - 2) ? new Coord(emptySquareRow, emptySquareColumn) : null;
    }

    private boolean setEmptySquareData(Markers marker, int curr_row, int curr_column) {
        if(board[curr_row][curr_column] != marker) {
            if(isEmpty(curr_row, curr_column)) {
                countEmptySquare++;
                emptySquareRow = curr_row;
                emptySquareColumn = curr_column;
            }
            return true;
        }
        return false;
    }

    private void clearEmptySquareData() {
        countEmptySquare = 0;
        emptySquareRow = -1;
        emptySquareColumn = -1;
    }
}
