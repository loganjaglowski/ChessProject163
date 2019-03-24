package chess;

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

    /*****************************************************************
     * A method that creates an AI for the human player to fight.
     *****************************************************************/
    public void AI() {
        // ai is default black

        System.out.println("Ai is active");

        if (firstTurn == true){
            this.move(new Move(1, 1, 3, 1 ));
            firstTurn = false;
        }else {

            //find King
            int rk = 0;
            int ck = 0;
            while (rk < 8) {
                while (ck < 8) {
                    if (board[rk][ck] != null) {
                        if (board[rk][ck].player() == Player.BLACK) {
                            if (board[rk][ck].type().equals("King")) {
                                break; //this only breaks out of the second loop but thats okay, the first only increments it
                            }
                        }
                    }
                    ck++;
                }
                rk++;
            }



            //get out of check
            if (this.inCheck(Player.BLACK)) {

                //move king out of danger
                if (this.isDangerous(rk, ck)) {//if the piece (rb, cb) is in danger
                    //System.out.println(board[rb][cb].type() + "is in danger, first check");
                    for (int r = 0; r < 8; r++) {
                        for (int c = 0; c < 8; c++) {
                            if (board[r][c] == null || board[r][c].player() == Player.WHITE) {
                                //          System.out.println("the spot that it is checking is available");
                                Move m = new Move(rk, ck, r, c);
                                //System.out.println(board[rb][cb].type() + " is attempting to move from: " + rb + ", " + cb + " to: " + r + ", " + c);
                                if (board[rk][ck].isValidMove(m, board)) {
                                    //System.out.println("it can move to that spot");
                                    if (this.isDangerous(r, c) == false) {  //^ searches board and checks for tile which is not dangerous
                                        //  System.out.println("from: " + rb + ", " + cb + "to: " + r + ", " + c);
                                        //System.out.println("piece: " + board[rb][cb].type());
                                        this.move(m);
                                        r = 7;
                                        c = 7;

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
                                    if (!board[rb][cb].type().equals("King")) { //finds any black piece that isnt a king
                                        for (int r = 0; r < 8; r++) {
                                            for (int c = 0; c < 8; c++) {
                                                Move move = new Move(rb, cb, r, c);
                                                if (board[rb][cb].isValidMove(move, board)) {
                                                    this.move(move);
                                                    if (this.isDangerous(rk, ck) == true) { //checks to see if king is still in danger, moves piece back if it is
                                                        this.move(new Move(r, c, rb, cb));
                                                    } else {// breaks out of loops if king has been saved
                                                        c = 7;
                                                        r = 7;
                                                        cb = 7;
                                                        rb = 7;
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
                boolean moved = false;
                while (moved == true) {
                    //attempts to remove piece from danger
                    //TODO: priority system to save more important pieces first (if i get everything else done, which probably will not happen)
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
                                                            moved = true;


                                                            //break out of loops
                                                        }
                                                    }
                                                }
                                            }
                                        }



                                    }else{

                                    }
                                }
                            }
                        }
                    }

                    //attempt to capture white king (needs info from before the .incheck if)
                    Move move = new Move(rb, cb, rk, ck);
                    if (board[rb][cb].isValidMove(move, board)) {
                        this.move(move);
                        moved = true;
                    }

                }






                //advance pieces and maybe try to take pieces
                //find lowest priority piece
                boolean foundLowestPriorityPiece = false;
                int rowBlack = 0;
                int colBlack = 0;
                while (foundLowestPriorityPiece == false) {
                    for (int rb = 0; rb < 8; rb++) {
                        for (int cb = 0; cb < 8; cb++) {
                            if (board[rb][cb] != null) {
                                if (board[rb][cb].player() == Player.BLACK) {
                                    if (board[rb][cb].type().equals("Pawn")) {
                                        foundLowestPriorityPiece = true;
                                        rowBlack = rb;
                                        colBlack = cb;
                                    } else if (board[rb][cb].type().equals("Knight") || board[rb][cb].type().equals("Rook") || board[rb][cb].type().equals("Bishop")) {
                                        foundLowestPriorityPiece = true;
                                        rowBlack = rb;
                                        colBlack = cb;
                                    } else if (board[rb][cb].type().equals("Queen")) {
                                        foundLowestPriorityPiece = true;
                                        rowBlack = rb;
                                        colBlack = cb;
                                    } else {//use king as last resort
                                        foundLowestPriorityPiece = true;
                                        rowBlack = rb;
                                        colBlack = cb;
                                    }


                                }
                            }
                        }
                    }

                }

            }


            /*
             * Write a simple AI set of rules in the following order.
             * a. Check to see if you are in check.
             * 		i. If so, get out of check by moving the king or placing
             * 		a piece to block the check
             *
             * b. Attempt to put opponent into check (or checkmate).
             * 		i. Attempt to put opponent into check without losing
             * 		your piece
             *		ii. Perhaps you have won the game.
             *
             *c. Determine if any of your pieces are in danger,
             *		i. Move them if you can.
             *		ii. Attempt to protect that piece.
             *
             *d. Move a piece (pawns first) forward toward opponent king
             *		i. check to see if that piece is in danger of being
             *		removed, if so, move a different piece.
             */

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

    public int[] findHighPiorityPiece(Player p){
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null) {
                    if (board[r][c].player() == Player.BLACK) {


                    }
                }
            }
        }
    }


}

//end of class
