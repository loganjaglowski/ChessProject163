package chess;

public class Knight extends ChessPiece {

    /******************************************************************
     * A constructor that calls the super class method.
     * @param player the player being used
     *****************************************************************/
    public Knight(Player player) {
        super(player);
    }

    /******************************************************************
     * A method that returns the piece type as a string.
     * @return the piece name
     *****************************************************************/
    public String type() {
        return "Knight";
    }

    /******************************************************************
     * A method that determines if an attempted move is valid.
     * @param move  a {@link chess.Move} object describing the move to
     * be made.
     * @param board the {@link chess.IChessPiece} in which this piece
     * resides.
     * @return true if it's valid and false if it's not.
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){

        boolean valid = false;

        //checks the super class method
        if (super.isValidMove(move, board)) {
            //checks first direction
            if (move.fromRow == move.toRow - 2)
                if (move.fromColumn == move.toColumn - 1) {
                    //checks for the white player
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                    //checks for the black player
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                }
            //checks second direction
            if (move.fromRow == move.toRow + 2)
                if (move.fromColumn == move.toColumn - 1) {
                    //checks for white player
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                    //checks for black player
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                }
            //checks third direction
            if (move.fromRow == move.toRow + 2)
                if (move.fromColumn == move.toColumn + 1) {
                    //checks for white player
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                    //checks for black player
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                }
            //checks fourth direction
            if (move.fromRow == move.toRow - 2)
                if (move.fromColumn == move.toColumn + 1) {
                    //checks white
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                    //checks black
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow - 1)
                if (move.fromColumn == move.toColumn - 2) {
                    //checks white
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }//checks black
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow - 1)
                if (move.fromColumn == move.toColumn + 2) {
                    //checks white
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        //checks black
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow + 1)
                if (move.fromColumn == move.toColumn - 2) {
                    if (super.player() == Player.WHITE) {
                        //checks white
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }//checks black
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow + 1)
                if (move.fromColumn == move.toColumn + 2) {
                    //checks white
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }//checks black
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player()
                                == Player.BLACK)
                            valid = true;
                    }
                }
        }
        return valid;
    }
}

//end of class