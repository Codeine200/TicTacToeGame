package gamecontainer;

import java.util.Arrays;

/**
 * Created by Василий on 12.06.2015.
 */
public class GameModel {
    private int size = 0;
    private Player currPlayer;
    private Markers[][] area;
    private boolean endGame = false;

    public GameModel(int size) {
        this.size = size;
        area = new Markers[size][size];
        clear();
    }

    public Markers[][] getArea() {
        return Arrays.copyOf(area, size);
    }

    public void clear() {
        endGame = false;
        currPlayer = null;
        for(int i=0; i<size; i++)
            Arrays.fill(area[i], Markers.EMPTY);
    }

    private boolean isFillAllSquares() {
        for(int i=0; i< size; i++) {
            for (int j = 0; j < size; j++) {
                if (isEmpty(i, j))
                    return false;
            }
        }

        return true;
    }

    public GameMessage move(Player player, int row, int column) {
        if(!endGame) {
            if(currPlayer == null || currPlayer.getMarker() != player.getMarker()) {
                if(currPlayer == null) currPlayer = player; // first move
                System.out.println(row + ":" + column);

                if(!isEmpty(row, column)) return GameMessage.ERROR_NOT_EMPTY_FIELD;
                if(isFillAllSquares()) {
                    endGame = true;
                    return GameMessage.ERROR_FILL_ALL_FIELD;
                }

                currPlayer = player;
                setValue(currPlayer.getMarker(), row, column);
                if(isWinner(currPlayer.getMarker())) {
                    endGame = true;
                    return  GameMessage.INFO_WINNER;
                }

                return GameMessage.INFO_SUCCESSFUL_MOVE;
            }
            return GameMessage.ERROR_ALREADY_MOVE;
        }
        return GameMessage.INFO_END_GAME;
    }

    public boolean setValue(Markers value, int row, int column) {
        if(isEmpty(row, column)) {
            area[row][column] = value;
            return true;
        }
        return false;
    }

    public boolean isEmpty(int row, int column) {
        return (area[row][column] == Markers.EMPTY);
    }

    public boolean isWinner(Markers value) {
        for(int i=0; i< size; i++) {
            for(int j=0; j< size; j++) {
                if(area[i][j] == value) {
                    for(Move move : Move.values()) {
                        if(isWinnerPath(move, i, j))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isWinnerPath(Move value, int row, int column) {
        Markers marker = area[row][column];
        switch (value) {
            case NORTH: {
                if(row - size + 1 < 0)
                    return false;

                for(int j=0; j< size - 1; j++)
                    if(area[row - 1 - j][column] != marker)
                        return false;
                break;
            }
            case SOUTH: {
                if(row + size - 1 >= size)
                    return false;

                for(int j=0; j< size - 1; j++)
                    if(area[row + 1 + j][column] != marker)
                        return false;
                break;
            }
            case WEST: {
                if(column - size + 1 < 0)
                    return false;

                for(int i=0; i< size - 1; i++)
                    if(area[row][column - 1 - i] != marker)
                        return false;
                break;
            }
            case EAST: {
                if(column + size - 1 >= size)
                    return false;

                for(int i=0; i< size - 1; i++)
                    if(area[row][column + 1 + i] != marker)
                        return false;
                break;
            }
            case NORTH_WEST: {
                if(row - size + 1 < 0 || column - size + 1 < 0)
                    return false;

                for(int k=0; k< size - 1; k++)
                    if(area[row - 1 - k][column - 1 - k] != marker)
                        return false;

                break;
            }
            case NORTH_EAST: {
                if(row - size + 1 < 0 || column + size - 1 >= size)
                    return false;

                for(int k=0; k< size - 1; k++)
                    if(area[row - 1 - k][column + 1 + k] != marker)
                        return false;

                break;
            }
            case SOUTH_WEST: {
                if(row + size - 1 >= size || column - size + 1 < 0)
                    return false;

                for(int k=0; k< size - 1; k++)
                    if(area[row + 1 + k][column - 1 - k] != marker)
                        return false;

                break;
            }
            case SOUTH_EAST: {
                if(row + size - 1 >= size || column + size - 1 >= size)
                    return false;

                for(int k=0; k< size - 1; k++)
                    if(area[row + 1 + k][column + 1 + k] != marker)
                        return false;

                break;
            }
        }
        return true;
    }

}
