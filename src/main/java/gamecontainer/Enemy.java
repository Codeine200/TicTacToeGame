package gamecontainer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Василий on 12.06.2015.
 */
public class Enemy implements Player {
    private Markers marker;
    private Markers[][] area;

    private int countEmptySquare = 0;
    private int emptySquareRow = -1;
    private int emptySquareColumn = -1;

    private Coord coord;

    public Enemy(Markers value) {
        this.marker = value;
    }

    public void setArea(Markers[][] area) {
        this.area = area;
    }

    private int getSizeArea() {
        return area.length;
    }

    private Coord checkSquareForProtect(Markers marker) {
        int SIZE = getSizeArea();
        Coord coord = null;
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                if(!isEmpty(i, j) && area[i][j] != marker) {
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
        int SIZE = getSizeArea();
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

    private boolean isEmpty(int row, int column) {
        return (area[row][column] == Markers.EMPTY);
    }

    private boolean setEmptySquareData(Markers marker, int curr_row, int curr_column) {
        if(area[curr_row][curr_column] != marker) {
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

    @Override
    public Coord getCoord() {
        return  new Coord(coord.getRow(), coord.getColumn());
    }

    public void move() {
        coord = checkWinPathStrategy();
        if(coord != null) {
            System.out.println("Comp has made the winning move");
            coord = new Coord(coord.getRow(), coord.getColumn());
            return;
        }

        coord = checkProtectStrategy();
        if(coord != null) {
            System.out.println("Comp protected");
            coord = new Coord(coord.getRow(), coord.getColumn());
            return;
        }

        coord = centerSquareStrategy();
        if(coord != null) {
            System.out.println("Comp chosen center");
            coord = new Coord(coord.getRow(), coord.getColumn());
            return;
        }

        coord = angleSquareStrategy();
        if(coord != null) {
            System.out.println("Comp chosen angle");
            coord = new Coord(coord.getRow(), coord.getColumn());
            return;
        }
    }

    private Coord checkWinPathStrategy() {
        Markers tempMarker = (marker == Markers.TOE) ? Markers.CROSS : Markers.TOE;
        Coord coord = checkSquareForProtect(tempMarker);
        return (coord != null) ? new Coord(coord.getRow(), coord.getColumn()) : null;
    }

    private Coord checkProtectStrategy() {
        Coord coord = checkSquareForProtect(marker);
        return (coord != null) ? new Coord(coord.getRow(), coord.getColumn()) : null;
    }

    private Coord centerSquareStrategy() {
        return (isEmpty(1, 1)) ? new Coord(1, 1) : null;
    }

    private Coord angleSquareStrategy() {
        ArrayList<Coord> list = new ArrayList<Coord>();
        if(isEmpty(0, 0)) list.add(new Coord(0, 0));
        if(isEmpty(getSizeArea() - 1, 0)) list.add(new Coord(getSizeArea() - 1, 0));
        if(isEmpty(0, getSizeArea() - 1)) list.add(new Coord(0, getSizeArea() - 1));
        if(isEmpty(getSizeArea() - 1, getSizeArea() - 1)) list.add(new Coord(getSizeArea() - 1, getSizeArea() - 1));

        if(list.size() > 0) {
            return  list.get((new Random()).nextInt(list.size()));
        }

        return null;
    }

    @Override
    public Markers getMarker() {
        return marker;
    }
}
