package chess;

import javax.swing.JOptionPane;

//ai: look for a white piece, look for a black piece, see if in danger. (attemptToRemoveFromDanger)

public class ChessModel implements IChessModel {
    /** The board */
    private IChessPiece[][] board;

    /** The current player */
    private Player player;

    private boolean firstTurn = true;

    /*****************************************************************
     * A constructor that creates the chess model.
     *****************************************************************/
    public ChessModel() {
        //creates the board and sets the first player to white
        board = new IChessPiece[8][8];
        player = Player.WHITE;

        /** Set white pieces */
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight(Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);
        board[6][0] = new Pawn(Player.WHITE);
        board[6][1] = new Pawn(Player.WHITE);
        board[6][2] = new Pawn(Player.WHITE);
        board[6][3] = new Pawn(Player.WHITE);
        board[6][4] = new Pawn(Player.WHITE);
        board[6][5] = new Pawn(Player.WHITE);
        board[6][6] = new Pawn(Player.WHITE);
        board[6][7] = new Pawn(Player.WHITE);

        /** Set black pieces */
        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight(Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);
        board[1][0] = new Pawn(Player.BLACK);
        board[1][1] = new Pawn(Player.BLACK);
        board[1][2] = new Pawn(Player.BLACK);
        board[1][3] = new Pawn(Player.BLACK);
        board[1][4] = new Pawn(Player.BLACK);
        board[1][5] = new Pawn(Player.BLACK);
        board[1][6] = new Pawn(Player.BLACK);
        board[1][7] = new Pawn(Player.BLACK);

    }

    /******************************************************************
     * A method that returns whether the game is complete or not.
     * @return true if complete, false if incomplete.
     *****************************************************************/
    public boolean isComplete() {
        boolean valid = false;
        IChessPiece oldPiece;
        if (inCheck(currentPlayer())) {
            valid = true;
            for (int x = 0; x < numRows(); x++) {
                for (int y = 0; y < numColumns(); y++) {
                    if (board[x][y] != null && board[x][y].player() == currentPlayer()) {
                        for (int r = 0; r < numRows(); r++) {
                            for (int c = 0; c < numColumns(); c++) {
                                Move m = new Move(x, y, r, c);
                                if (board[x][y].isValidMove(m, board)) {
                                    oldPiece = board[m.toRow][m.toColumn];
                                    board[m.toRow][m.toColumn] = board[m.fromRow][m.fromColumn];
                                    board[m.fromRow][m.fromColumn] = null;
                                    if(!inCheck(currentPlayer())) {
                                        board[m.fromRow][m.fromColumn] = board[m.toRow][m.toColumn];
                                        board[m.toRow][m.toColumn] = oldPiece;
                                        return false;
                                    }
                                    board[m.fromRow][m.fromColumn] = board[m.toRow][m.toColumn];
                                    board[m.toRow][m.toColumn] = oldPiece;
                                }
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }

    /*****************************************************************
     * A method that decides whether the given move is valid.
     * @param move a {@link chess.Move} object describing the move to
     * be made.
     * @return true if valid and false if not.
     *****************************************************************/
    public boolean isValidMove(Move move) {
        boolean valid = false;

        //ensures user didn't click empty square
        if (board[move.fromRow][move.fromColumn] != null)
            if (board[move.fromRow][move.fromColumn].isValidMove(move,
                    board))
                //ensures user didn't land on own piece
                if ((board[move.fromRow][move.fromColumn]).player() !=
                        currentPlayer().next())
                    valid = true;

        return valid;
    }

    /******************************************************************
     * A method that creates the move for a piece.
     * @param move a {@link chess.Move} object describing the move to
     * be made.
     *****************************************************************/
    public void move(Move move) {
        board[move.toRow][move.toColumn] = board[move.fromRow][move.
                fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }

    public void setPlayer(Player p){
        player = p;
    }
    
    /*****************************************************************
     * A method that determines whether either king is in check.
     * @param  p {@link chess.Move} the Player being checked
     * @return true if player is in check, false if not
     *****************************************************************/
    public boolean inCheck(Player p) {
        boolean valid = false;
        int kingX = 0;
        int kingY = 0;
        //finds the position of the king
        for (int x = 0; x < numRows(); x++) {
            for (int y = 0; y < numColumns(); y++) {
                if (board[x][y] != null && board[x][y].type().equals("King")
                        && board[x][y].player() == p) {
                    kingX = x;
                    kingY = y;
                    x = numColumns() - 1;
                    y = numRows() - 1;
                }
            }
        }

        //checks if the king is in check
        for (int x = 0; x < numRows(); x++) {
            for (int y = 0; y < numColumns(); y++) {
                if (board[x][y] != null && board[x][y].player() !=
                        board[kingX][kingY].player()) {
                    Move m = new Move(x, y, kingX, kingY);
                    if (board[x][y].isValidMove(m, board))
                        valid = true;
                }
            }
        }
        return valid;
    }

    /******************************************************************
     * A method that returns the current player.
     * @return the player.
     *****************************************************************/
    public Player currentPlayer() {
        return player;
    }

    /*****************************************************************
     * A method that returns the number of rows on the board.
     * @return the number of rows.
     *****************************************************************/
    public int numRows() {
        return 8;
    }

    /*****************************************************************
     * A method that returns the number of columns on the board.
     * @return the number of columns.
     *****************************************************************/
    public int numColumns() {
        return 8;
    }

    /*****************************************************************
     * A method that returns the type of piece at board[row][col].
     * @return the piece there.
     *****************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /*****************************************************************
     * A method that switches the player (turn).
     *****************************************************************/
    public void setNextPlayer() {
        player = player.next();
    }

    /******************************************************************
     * A method that sets the square to the specified piece.
     * @param row square row coordinate
     * @param column square column coordinate
     * @param piece type of piece to set square to
     *****************************************************************/
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

     public void pawnPromoted(Move move) {
        if (board[move.toRow][move.toColumn].type().equals("Pawn") &&
                (move.toRow == 0 || move.toRow == 7)){
            String[] promotion = {"Queen", "Knight", "Rook", "Bishop"};
            int pick = JOptionPane.showOptionDialog(null, "Pick which"
                            + " piece you would like to promote to: ",
                    "", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, promotion,
                    promotion[0]);
            if (pick == 0) {
                board[move.toRow][move.toColumn] = new Queen(player);
            }
            if (pick == 1) {
                board[move.toRow][move.toColumn] = new Knight(player);
            }
            if (pick == 2) {
                board[move.toRow][move.toColumn] = new Rook(player);
            }
            if (pick == 3) {
                board[move.toRow][move.toColumn] = new Bishop(player);
            }

        }
    }
    
    public void rookCastling (Move move){
        if (board[move.toRow][move.toColumn].type().equals("King") &&
                Math.abs(move.fromColumn - move.toColumn) == 2){
            if (move.toColumn < move.fromColumn){
                board[move.fromRow][move.fromColumn - 1] =
                        board[move.fromRow][move.fromColumn - 4];
                board[move.fromRow][move.fromColumn - 4] = null;
            } else {
                board[move.fromRow][move.fromColumn + 1] =
                        board[move.fromRow][move.fromColumn + 3];
                board[move.fromRow][move.fromColumn + 3] = null;
            }
        }
    }
    
    /*****************************************************************
     * A method that creates an AI for the human player to fight.
     *****************************************************************/
    public void AI() {
        // ai is default black

        // while loop covers entire method, is broken when a move is made. actual moved boolean is not changed
        boolean moved = false;
        moved:
        while (moved == false) {

        if (firstTurn == true) {
            this.move(new Move(1, 1, 3, 1));
            firstTurn = false;
            break moved;
        } else {
            //get out of check
            if (this.inCheck(Player.BLACK)) {
                //find  Black King
                int rk = 0;
                int ck = 0;
                while (rk < 8) {
                    while (ck < 8) {
                        if (board[rk][ck] != null) {
                            if (board[rk][ck].player() == Player.BLACK) {
                                if (board[rk][ck].type().equals("King")) {
                                    break; //this only breaks out of the second loop but that's okay, the first only increments it
                                }
                            }
                        }
                        ck++;
                    }
                    rk++;
                }
                //move king out of danger
                if (this.isDangerous(rk, ck)) {//if the piece (rb, cb) is in danger
                    for (int r = 0; r < 8; r++) {
                        for (int c = 0; c < 8; c++) {
                            if (board[r][c] == null || board[r][c].player() == Player.WHITE) {
                                Move m = new Move(rk, ck, r, c);
                                if (board[rk][ck].isValidMove(m, board)) {
                                    if (this.isDangerous(r, c) == false) {  //^ searches board and checks for tile which is not dangerous
                                        this.move(m);
                                        break moved;
                                    }
                                }
                            }
                        }
                    }
                }
                //if there the king cannot move out of danger, move another piece
                if (board[rk][ck] != null) {
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {
                                    if (!board[rb][cb].type().equals("King")) { //finds any black piece that isn't a king
                                        for (int r = 0; r < 8; r++) {
                                            for (int c = 0; c < 8; c++) {
                                                Move move = new Move(rb, cb, r, c);
                                                if (board[rb][cb].isValidMove(move, board)) {
                                                    this.move(move);
                                                    if (this.isDangerous(rk, ck) == true) { //checks to see if king is still in danger, moves piece back if it is
                                                        this.move(new Move(r, c, rb, cb));
                                                    } else {// breaks out of loops if king has been saved, move isn't changed
                                                        break moved;

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                    //attempts to remove piece from danger
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {  //^ searches board for any piece controlled by the ai
                                    if (this.isDangerous(rb, cb)) {//if the piece (rb, cb) is in danger
                                        //find a place on the board which is not dangerous
                                        for (int r = 0; r < 8; r++) {
                                            for (int c = 0; c < 8; c++) {
                                                if (board[r][c] == null || board[r][c].player() == Player.WHITE) {
                                                    Move m = new Move(rb, cb, r, c);
                                                    if (board[rb][cb].isValidMove(m, board)) {
                                                        if (this.isDangerous(r, c) == false) {
                                                            this.move(m);
                                                            break moved;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

     

                    //find white King
                    int rwk = 0;
                    int cwk = 0;
                    while (rwk < 8) {
                        while (cwk < 8) {
                            if (board[rwk][cwk] != null) {
                                if (board[rwk][cwk].player() == Player.BLACK) {
                                    if (board[rwk][cwk].type().equals("King")) {
                                        break; //this only breaks out of the second loop but thats okay, the first only increments it
                                    }
                                }
                            }
                            cwk++;
                        }
                        rwk++;
                    }

                    //attempt to capture the white king
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {  //^ searches board for any piece controlled by the ai
                                    //attempt to capture white king (needs info from before the .incheck if)
                                    Move move = new Move(rb, cb, rwk, cwk);
                                    if (board[rb][cb].isValidMove(move, board)) {
                                        this.move(move);
                                        break moved;

                                    }
                                }
                            }
                        }
                    }

                    //try to take pieces
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {
                                    if (board[rb][cb].type().equals("Pawn")) {
                                        for (int rw = 0; rw < 8; rw++) {
                                            for (int cw = 0; cw < 8; cw++) {
                                                if (board[rw][cw] != null) {
                                                    if (board[rw][cw].player() == Player.WHITE) {
                                                        Move move = new Move(rb, cb, rw, cw);
                                                        if (board[rb][cb].isValidMove(move, board)) {
                                                            if (this.isDangerous(cw, rw) == false) {
                                                                this.move(move);
                                                                break moved;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    } else if (board[rb][cb].type().equals("Knight") || board[rb][cb].type().equals("Rook") || board[rb][cb].type().equals("Bishop")) {
                                        for (int rw = 0; rw < 8; rw++) {
                                            for (int cw = 0; cw < 8; cw++) {
                                                if (board[rw][cw] != null) {
                                                    if (board[rw][cw].player() == Player.WHITE) {
                                                        Move move = new Move(rb, cb, rw, cw);
                                                        if (board[rb][cb].isValidMove(move, board)) {
                                                            if (this.isDangerous(cw, rw) == false) {
                                                                this.move(move);
                                                                break moved;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    } else if (board[rb][cb].type().equals("Queen")) {
                                        for (int rw = 0; rw < 8; rw++) {
                                            for (int cw = 0; cw < 8; cw++) {
                                                if (board[rw][cw] != null) {
                                                    if (board[rw][cw].player() == Player.WHITE) {
                                                        Move move = new Move(rb, cb, rw, cw);
                                                        if (board[rb][cb].isValidMove(move, board)) {
                                                            if (this.isDangerous(cw, rw) == false) {
                                                                this.move(move);
                                                                break moved;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {//use king as last resort
                                        for (int rw = 0; rw < 8; rw++) {
                                            for (int cw = 0; cw < 8; cw++) {
                                                if (board[rw][cw] != null) {
                                                    if (board[rw][cw].player() == Player.WHITE) {
                                                        Move move = new Move(rb, cb, rw, cw);
                                                        if (board[rb][cb].isValidMove(move, board)) {
                                                            if (this.isDangerous(cw, rw) == false) {
                                                                this.move(move);
                                                                break moved;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //move a piece forwards
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {
                                    if (board[rb][cb] instanceof Pawn) {
                                        if (rb != 7) {
                                            this.move(new Move(rb, cb, rb + 1, cb));
                                            break moved;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //use knights, rooks, and bishops after pawns
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {
                                    if (board[rb][cb].type().equals("Knight") || board[rb][cb].type().equals("Rook") || board[rb][cb].type().equals("Bishop")) {
                                        for (int r = 7; r > -1; r--) {
                                            for (int c = 4; c < 8; c++) {
                                                Move move = new Move(rb, cb, r, c);
                                                if (board[rb][cb].isValidMove(move, board)) {
                                                    this.move(move);
                                                    break moved;
                                                }
                                            }
                                            for (int c = 3; c > -1; c--) {
                                                Move move = new Move(rb, cb, r, c);
                                                if (board[rb][cb].isValidMove(move, board)) {
                                                    this.move(move);
                                                    break moved;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //use the queen after knights, rooks and bishops
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {
                                    if (board[rb][cb].type().equals("Queen")) {
                                        for (int r = 7; r > -1; r--) {
                                            for (int c = 4; c < 8; c++) {
                                                Move move = new Move(rb, cb, r, c);
                                                if (board[rb][cb].isValidMove(move, board)) {
                                                    this.move(move);
                                                    break moved;
                                                }
                                            }
                                            for (int c = 3; c > -1; c--) {
                                                Move move = new Move(rb, cb, r, c);
                                                if (board[rb][cb].isValidMove(move, board)) {
                                                    this.move(move);
                                                    break moved;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //use king as a last resort
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {
                                    for (int r = 7; r > -1; r--) {
                                        for (int c = 4; c < 8; c++) {
                                            Move move = new Move(rb, cb, r, c);
                                            if (board[rb][cb].isValidMove(move, board)) {
                                                this.move(move);
                                                break moved;
                                            }
                                        }
                                        for (int c = 3; c > -1; c--) {
                                            Move move = new Move(rb, cb, r, c);
                                            if (board[rb][cb].isValidMove(move, board)) {
                                                this.move(move);
                                                break moved;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    //returns boolean value. true if the spot (row, col) can be taken by any white piece
    public boolean isDangerous(int row, int col){
        for (int r = 0; r < numRows(); r++){
            for (int c = 0; c < numColumns(); c++){
                if (board[r][c] != null){
                    if (board[r][c].player() == Player.WHITE){
                        Move m = new Move(r, c, row, col);
                        if (this.isValidMove(m)){
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }





}

//end of class
