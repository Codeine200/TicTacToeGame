package gamecontainer;

/**
 * Created by Василий on 12.06.2015.
 */
public class Enemy implements Player {
    private Markers marker;
    private Markers[][] area;
    private int protectRow;
    private int protectColumn;

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
        Markers marker = area[row][column];
        int SIZE = getSize();
        switch (value) {
            case NORTH: {
                if(row - SIZE + 1 < 0)
                    return false;

                for(int j=0; j<SIZE - 2; j++)
                    if(area[row - 1 - j][column] != marker)
                        return false;

                protectRow = row - SIZE + 1;
                protectColumn  = column;
                break;
            }
            case SOUTH: {
                if(row + SIZE - 1 >= SIZE)
                    return false;

                for(int j=0; j<SIZE - 2; j++)
                    if(area[row + 1 + j][column] != marker)
                        return false;

                protectRow = row + SIZE - 1;
                protectColumn  = column;
                break;
            }
            case WEST: {
                if(column - SIZE + 1 < 0)
                    return false;

                for(int i=0; i<SIZE - 2; i++)
                    if(area[row][column - 1 - i] != marker)
                        return false;

                protectRow = row;
                protectColumn  = column - SIZE + 1;
                break;
            }
            case EAST: {
                if(column + SIZE - 1 >= SIZE)
                    return false;

                for(int i=0; i<SIZE - 2; i++)
                    if(area[row][column + 1 + i] != marker)
                        return false;

                protectRow = row;
                protectColumn  = column + SIZE - 1;
                break;
            }
            case NORTH_WEST: {
                if(row - SIZE + 1 < 0 || column - SIZE + 1 < 0)
                    return false;

                for(int k=0; k<SIZE - 2; k++)
                    if(area[row - 1 - k][column - 1 - k] != marker)
                        return false;

                protectRow = row - SIZE + 1;
                protectColumn  = column - SIZE + 1;
                break;
            }
            case NORTH_EAST: {
                if(row - SIZE + 1 < 0 || column + SIZE - 1 >= SIZE)
                    return false;

                for(int k=0; k<SIZE - 2; k++)
                    if(area[row - 1 - k][column + 1 + k] != marker)
                        return false;

                protectRow = row - SIZE + 1;
                protectColumn  = column + SIZE - 1;
                break;
            }
            case SOUTH_WEST: {
                if(row + SIZE - 1 >= SIZE  || column - SIZE + 1 < 0)
                    return false;

                for(int k=0; k<SIZE - 2; k++)
                    if(area[row + 1 + k][column - 1 - k] != marker)
                        return false;

                protectRow = row + SIZE - 1;
                protectColumn  = column - SIZE + 1;
                break;
            }
            case SOUTH_EAST: {
                if(row + SIZE - 1 >= SIZE  || column + SIZE - 1 >= SIZE)
                    return false;

                for(int k=0; k<SIZE - 2; k++)
                    if(area[row + 1 + k][column + 1 + k] != marker)
                        return false;

                protectRow = row + SIZE - 1;
                protectColumn  = column + SIZE - 1;
                break;
            }
        }

        return isEmpty(protectRow, protectColumn);
    }

    private boolean isEmpty(int row, int column) {
        return (area[row][column] == Markers.EMPTY);
    }

    @Override
    public int getRow() {
        if(isProtect()) {
            System.out.println("Comp protected");
            return  protectRow;
        }

        return 0;
    }

    @Override
    public int getColumn() {
        if(isProtect()) {
            System.out.println("Comp protected");
            return  protectColumn;
        }
        return 0;
    }

    @Override
    public Markers getMarker() {
        return marker;
    }
}
