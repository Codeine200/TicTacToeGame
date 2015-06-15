package gamecontainer;

import java.util.Arrays;

/**
 * Created by Василий on 12.06.2015.
 */
public class GameModel {
    private Player currPlayer;
    private Markers[][] area;
    private boolean endGame = false;

    public GameModel(int size) {
        area = new Markers[size][size];
        for(int i=0; i<area.length; i++)
            Arrays.fill(area[i], Markers.EMPTY);
    }

    public void setSize(int n) {
        clear();
        area = new Markers[n][n];
        for(int i=0; i<area.length; i++)
            Arrays.fill(area[i], Markers.EMPTY);
    }

    public Markers[][] getArea() {
        return Arrays.copyOf(area, area.length);
    }

    public void clear() {
        endGame = false;
        currPlayer = null;
        for(int i=0; i<area.length; i++)
            Arrays.fill(area[i], Markers.EMPTY);
    }

    private boolean isFillAllSquares() {
        for(int i=0; i< area.length; i++) {
            for (int j = 0; j < area.length; j++) {
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

    public boolean isWinner(Markers marker) {
        int count = 0;
        // horizontally
        for(int i=0; i<area.length; i++) {
            for(int j=0; j<area.length; j++) {
                if(area[i][j] == marker) count++;
            }
            if(count == area.length) return true;
            count = 0;
        }

        count = 0;
        // vertical
        for(int j=0; j<area.length; j++) {
            for(int i=0; i<area.length; i++) {
                if(area[i][j] == marker) count++;
            }
            if(count == area.length) return true;
            count = 0;
        }

        // diagonal
        count = 0;
        for(int i=0; i<area.length; i++) {
            if(area[i][i] == marker) count++;
        }

        if(count == area.length) return true;

        // anti diagonal
        count = 0;
        for(int i=0; i<area.length; i++) {
            if(area[i][area.length-1-i] == marker) count++;
        }

        if(count == area.length) return  true;
        return false;
    }
}
