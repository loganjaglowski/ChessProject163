package chess;

public class Pawn extends ChessPiece {

   public boolean hasDoneEnPassant = false;
   public boolean hasCapturedEnpassant = false;
   public int capturedRow;
   public int capturedCol;

    public boolean isHasDoneEnPassant() {
        return hasDoneEnPassant;
    }

    /******************************************************************
     * A method that calls the super class method.
     * @param player the player being used
     *****************************************************************/
    public Pawn(Player player) {
        super(player);
    }

    /******************************************************************
     * A method that returns the piece type as a string.
     * @return piece type name
     *****************************************************************/
    public String type() {
        return "Pawn";
    }

    /******************************************************************
     * A method that checks if the attempted move is valid.
     * @param move  a {@link chess.Move} object describing the move to
     * be made.
     * @param board the {@link chess.IChessPiece} in which this piece
     * resides.
     * @return true if valid anf false if not.
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;

        //checks super class method
        if (super.isValidMove(move, board)){

            hasDoneEnPassant = false;

            //first if controls diagonal movement when capturing
            if (board[move.toRow][move.toColumn] != null) {
                if ((board[move.toRow][move.toColumn]).player() != super.player()) {
                    if (move.fromColumn - move.toColumn == 1 || move.fromColumn - move.toColumn == -1) {
                        valid = true;
                    }else{
                        valid = false;
                    }
                }
                if (move.fromColumn - move.toColumn == 0) {
                    valid = false;
                }
            }
            if (move.fromColumn - move.toColumn != 0 && board[move.toRow][move.toColumn] == null)
                valid = false;
            if (super.player() == Player.BLACK) {
                if (this.isEnPassant(move)) {
                    if (move.fromRow - move.toRow < -2 || move.fromRow - move.toRow > -1) {
                        valid = false;

                    }else{
                        if (board[move.toRow][move.toColumn] != null){
                            valid = false;
                        }else{
                            hasDoneEnPassant = true;
                        }
                    }
                } else {
                    if (move.fromRow - move.toRow != -1) {
                        valid = false;
                    }
                }
            }else{
                if (this.isEnPassant(move)) {
                    if (move.fromRow - move.toRow > 2 || move.fromRow - move.toRow < 1) {
                        valid = false;
                    }else{
                        if (board[move.toRow][move.toColumn] != null){
                            valid = false;
                        }else{
                            hasDoneEnPassant = true;
                        }
                    }
                } else {
                    if (move.fromRow - move.toRow != 1) {
                        valid = false;
                    }
                }
            }
            if ( move.fromColumn - move.toColumn == -1){
                if (board[move.fromRow][move.toColumn] != null){
                    if (board[move.fromRow][move.toColumn].type().equals("Pawn")){
                        Pawn temp = (Pawn) board[move.fromRow][move.toColumn];
                        if ( temp.hasDoneEnPassant == true){
                            hasCapturedEnpassant = true;
                            capturedRow = move.fromRow;
                            capturedCol = move.toColumn;
                            valid = true;
                        }
                    }

                }
            }

            if ( move.fromColumn - move.toColumn == 1) {
                if (board[move.fromRow][move.toColumn] != null){
                    if (board[move.fromRow][move.toColumn].type().equals("Pawn")){
                        Pawn temp = (Pawn) board[move.fromRow][move.toColumn];
                        if ( temp.hasDoneEnPassant == true){
                            hasCapturedEnpassant = true;
                            capturedRow = move.fromRow;
                            capturedCol = move.toColumn;
                            valid = true;
                        }
                    }
                }

            }
        }else{
            valid = false;
        }
        
        return valid;
    }

    /******************************************************************
     * A helper method that determines if a pawn does some shit. FUck it. IDk.
     * @param move the attempted move
     * @return true if en passant and false if not.
     *****************************************************************/
    private boolean isEnPassant(Move move){
        if (super.player() == Player.WHITE){
            if (move.fromRow == 6){
                return true;
            }
        }else{
            if (move.fromRow == 1){
                return true;
            }
        }
        return false;
    }
}

//end of class
