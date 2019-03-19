package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {

    /** A board of buttons */
    private JButton[][] board;

    /** The model for the game */
    private ChessModel model;

    /** The images to represent the white pieces */
    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    /** The images to represent the black pieces */
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    /** Keeps track of first click on a piece */
    private boolean firstTurnFlag;

    /** The row being moved from */
    private int fromRow;

    /** The row being moved to */
    private int toRow;

    /** The column being moved from */
    private int fromCol;

    /** The column being moved to */
    private int toCol;

    /** The fromColumn saved for undo */
    private int undoSaveFromCol;

    /** The toColumn saved for undo */
    private int undoSaveToCol;

    /** A label that shows whose turn it is */
    private JLabel currentPlayerLabel;

    /** The listener for the action listeners */
    private listener listener;

    /******************************************************************
     * A constructor that sets up the panel.
     *****************************************************************/
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.
                numColumns(), 1, 1));

        //goes through the board and sets action listeners, pieces
        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if(model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                  else if(model.pieceAt(r, c).player() == Player.BLACK)
                    placeBlackPieces(r, c);

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }

        currentPlayerLabel = new JLabel("White");
        buttonpanel.add(currentPlayerLabel);

        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }

    /******************************************************************
     * A method that uses the parameters to create the checkered chess
     * board appearance.
     * @param r number of rows
     * @param c number of columns
     *****************************************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2
                == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /******************************************************************
     * A method that places the images on squares designated as white
     * pieces.
     * @param r The current row
     * @param c The current column
     *****************************************************************/
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * A method that places the images on squares designated as black
     * pieces.
     * @param r The current row
     * @param c The current column
     *****************************************************************/
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * A method that creates the images and adds them to the icons.
     *****************************************************************/
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/chess/wRook.png");
        wBishop = new ImageIcon("./src/chess/wBishop.png");
        wQueen = new ImageIcon("./src/chess/wQueen.png");
        wKing = new ImageIcon("./src/chess/wKing.png");
        wPawn = new ImageIcon("./src/chess/wPawn.png");
        wKnight = new ImageIcon("./src/chess/wKnight.png");
        //Sets the Image for black player pieces
        bRook = new ImageIcon("./src/chess/bRook.png");
        bBishop = new ImageIcon("./src/chess/bBishop.png");
        bQueen = new ImageIcon("./src/chess/bQueen.png");
        bKing = new ImageIcon("./src/chess/bKing.png");
        bPawn = new ImageIcon("./src/chess/bPawn.png");
        bKnight = new ImageIcon("./src/chess/bKnight.png");
    }

    /******************************************************************
     * A method that updates the board.
     *****************************************************************/
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else{
                if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                }
                if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);
                }
            }
        }

        currentPlayerLabel.setText("Current player: " + model.
                currentPlayer());

        repaint();
    }

    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())
                        if (firstTurnFlag) {
                            fromRow = r;
                            fromCol = c;

                            firstTurnFlag = false;
                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow,
                                    toCol);
                            if ((model.isValidMove(m))) {
                                model.move(m);
                                model.setNextPlayer();
                                displayBoard();
                            }
                        }
        }
    }
}

//end of class