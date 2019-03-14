package chess;

public class Rook extends ChessPiece {

    /******************************************************************
     * A constructor that calls the same method in the super class.
     * @param player the current player owner of the rook
     *****************************************************************/
    public Rook(Player player) {

        super(player);

    }

    /******************************************************************
     * A toString method that returns the piece name.
     * @return rook for the piece type
     *****************************************************************/
    public String type() {
        return "Rook";
    }

    // determines if the move is valid for a rook piece fixme: check if there's a piece in the way
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean valid = false;
        //checks if the rook is moving within the same row or column
        if (super.isValidMove(move, board)) {
            if (move.fromRow == move.toRow)
                if (move.fromColumn != move.toColumn)
                    valid = true;
            if (move.fromColumn == move.toColumn)
                if (move.fromRow != move.toRow)
                    valid = true;
        }

        //checks if there is a piece in the way
        //fixme: write code
        if(there is a piece in the way){
            valid = false;
        }

        return valid;

    }

}
