package gamecontainer;

/**
 * Created by Василий on 12.06.2015.
 */
public class Enemy implements Player {
    private Markers marker;
    private Markers[][] area;
    private int protectRow;
    private int protectColumn;

    private int countEmptySquare = 0;
    private int emptySquareRow = -1;
    private int emptySquareColumn = -1;

    public Enemy(Markers value) {
        this.marker = value;
    }

    public void setArea(Markers[][] area) {
        this.area = area;
    }

    private int getSize() {
        return area.length;
    }

    private boolean isProtect() {
        int SIZE = getSize();
        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                if(!isEmpty(i, j) && area[i][j] != marker) {
                    for(Move move : Move.values()) {
                        if(isProtectPath(move, i, j))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isProtectPath(Move value, int row, int column) {
        int SIZE = getSize();
        int curr_row = row;
        int curr_column = column;
        clearEmptySquareData();
        switch (value) {
            case NORTH: {
                if(row - SIZE + 1 < 0)
                    return false;

                for(int j=0; j<SIZE - 1; j++) {
                    curr_row = row - 1 - j;
                    if(!setEmptySquareData(curr_row, curr_column))
                        return false;
                }
                break;
            }
            case SOUTH: {
                if(row + SIZE - 1 >= SIZE)
                    return false;

                for(int j=0; j<SIZE - 1; j++) {
                    curr_row = row + 1 + j;
                    if (!setEmptySquareData(curr_row, curr_column))
                        return false;
                }
                break;
            }
            case WEST: {
                if(column - SIZE + 1 < 0)
                    return false;

                for(int i=0; i<SIZE - 2; i++) {
                    curr_column = column - 1 - i;
                    if (!setEmptySquareData(curr_row, curr_column))
                        return false;
                }
                break;
            }
            case EAST: {
                if(column + SIZE - 1 >= SIZE)
                    return false;

                for(int i=0; i<SIZE - 1; i++) {
                    curr_column = column + 1 + i;
                    if (!setEmptySquareData(curr_row, curr_column))
                        return false;
                }
                break;
            }
            case NORTH_WEST: {
                if(row - SIZE + 1 < 0 || column - SIZE + 1 < 0)
                    return false;

                for(int k=0; k<SIZE - 1; k++) {
                    curr_row = row - 1 - k;
                    curr_column = column - 1 - k;
                    if (!setEmptySquareData(curr_row, curr_column))
                        return false;
                }
                break;
            }
            case NORTH_EAST: {
                if(row - SIZE + 1 < 0 || column + SIZE - 1 >= SIZE)
                    return false;

                for(int k=0; k<SIZE - 1; k++) {
                    curr_row = row - 1 - k;
                    curr_column = column + 1 + k;
                    if (!setEmptySquareData(curr_row, curr_column))
                        return false;
                }
                break;
            }
            case SOUTH_WEST: {
                if(row + SIZE - 1 >= SIZE  || column - SIZE + 1 < 0)
                    return false;

                for(int k=0; k<SIZE - 1; k++) {
                    curr_row = row + 1 + k;
                    curr_column = column - 1 - k;
                    if (!setEmptySquareData(curr_row, curr_column))
                        return false;
                }
                break;
            }
            case SOUTH_EAST: {
                if(row + SIZE - 1 >= SIZE  || column + SIZE - 1 >= SIZE)
                    return false;

                for(int k=0; k<SIZE - 1; k++) {
                    curr_row = row + 1 + k;
                    curr_column = column + 1 + k;
                    if (!setEmptySquareData(curr_row, curr_column))
                        return false;
                };
                break;
            }
        }

        if(countEmptySquare == SIZE - 2) {
            protectRow = emptySquareRow;
            protectColumn = emptySquareColumn;
            return true;
        }

        return false;
    }

    private boolean isEmpty(int row, int column) {
        return (area[row][column] == Markers.EMPTY);
    }

    private boolean setEmptySquareData(int curr_row, int curr_column) {
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

    private boolean isCenterSquare() {
        return (isEmpty(1, 1));
    }

    @Override
    public Coord getCoord() {
        if(isProtect()) {
            System.out.println("Comp protected");
            return new Coord(protectRow, protectColumn);
        }

        if(isCenterSquare()) return new Coord(1, 1);

        return null;
    }

    @Override
    public Markers getMarker() {
        return marker;
    }
}
