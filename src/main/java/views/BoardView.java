package views;

import controllers.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EventListener;

/**
 * Created by Василий on 13.06.2015.
 */
public class BoardView extends JPanel {

    private JButton[][] squares;
    ActionListener listener;

    public BoardView(int size) {
        createBoard(size);
    }

    public void createBoard(int size) {
        removeBoard();
        setLayout(new GridLayout(0, size));
        squares = new JButton[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                add(createSquare(i, j));
        addSquareActionListener(listener);
    }

    private JComponent createSquare(int row, int column) {
        squares[row][column] = new JButton();
        squares[row][column].setName(row + ":" + column);
        squares[row][column].setFocusPainted(false);
        squares[row][column].setOpaque(false);
        return squares[row][column];
    }

    public void addSquareActionListener(ActionListener listener) {
        this.listener = listener;
        for(int i=0; i<squares.length; i++)
            for(int j=0; j<squares.length; j++) {
                squares[i][j].addActionListener(listener);
            }
    }

    public void setText(String text, int row, int column) {
        if(squares != null && row >= 0 && row < squares.length && column >= 0 && column < squares.length) {
            squares[row][column].setText(text);
        }
    }

    public void clear() {
        if(squares != null) {
            for (int i = 0; i < squares.length; i++)
                for (int j = 0; j < squares.length; j++) {
                    squares[i][j].setText(" ");
                }
        }
    }

    private void removeBoard() {
        if(squares != null) {
            for (int i = 0; i < squares.length; i++)
                for (int j = 0; j < squares.length; j++) {
                    remove(squares[i][j]);
                }
        }
    }


}