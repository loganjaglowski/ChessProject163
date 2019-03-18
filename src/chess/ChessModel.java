package chess;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;

    // declare other instance variables as needed

    public ChessModel() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;

        /** Set white pieces */
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
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
    public boolean isComplete() { //fixme: finish logic
        boolean valid = false;
        return valid;
    }

    public boolean isValidMove(Move move) {
        boolean valid = false;

        if (board[move.fromRow][move.fromColumn] != null) //ensures user didn't click empty square
            if (board[move.fromRow][move.fromColumn].isValidMove(move, board))
                if((board[move.fromRow][move.fromColumn]).player() != currentPlayer().next())
                    valid = true;

        return valid;
    }

    public void move(Move move) {
        board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }

    public boolean inCheck(Player p) {
        boolean valid = false;
        int kingX = 0;
        int kingY = 0;
        for (int x = 0; x < numRows(); x++) {
            for (int y = 0; y < numColumns(); y++) {
                if (board[x][y] != null && board[x][y].type().equals("King") && board[x][y].player() == p) {
                    kingX = x;
                    kingY = y;
                    break;
                }
            }
        }
        for (int x = 0; x < numRows(); x++) {
            for (int y = 0; y < numColumns(); y++) {
                if (board[x][y] != null && board[x][y].player() != board[kingX][kingY].player()) {
                    Move m = new Move (x, y, kingX, kingY);
                    if (board[x][y].isValidMove(m ,board))
                        valid = true;
                }
            }
        }
        return valid;
    }


    public Player currentPlayer() {
        return player;
    }

    public int numRows() {
        return 8;
    }

    public int numColumns() {
        return 8;
    }

    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    public void setNextPlayer() {
        player = player.next();
    }

    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    public void AI() {
        /*
         * Write a simple AI set of rules in the following order.
         * a. Check to see if you are in check.
         * 		i. If so, get out of check by moving the king or placing a piece to block the check
         *
         * b. Attempt to put opponent into check (or checkmate).
         * 		i. Attempt to put opponent into check without losing your piece
         *		ii. Perhaps you have won the game.
         *
         *c. Determine if any of your pieces are in danger,
         *		i. Move them if you can.
         *		ii. Attempt to protect that piece.
         *
         *d. Move a piece (pawns first) forward toward opponent king
         *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
         */

    }
}
