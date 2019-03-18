package chess;

public class King extends ChessPiece {

    public King(Player player) {
        super(player);
    }

    public String type() {
        return "King";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        //checks if attempted move is within one square of king
        if(super.isValidMove(move, board)){
                //checks upper left square
                if (move.toRow == move.fromRow - 1 &&
                        move.toColumn == move.fromColumn - 1) {
                    valid = true;
                    //checks upper mid square, directly above current spot
                } else if (move.toRow == move.fromRow - 1 &&
                        move.toColumn == move.fromColumn) {
                    valid = true;
                    //checks upper right square
                } else if (move.toRow == move.fromRow - 1 &&
                        move.toColumn == move.fromColumn + 1) {
                    valid = true;
                    //checks left square
                } else if (move.toRow == move.fromRow && move.toColumn
                        == move.fromColumn - 1) {
                    valid = true;
                    //checks right square
                } else if (move.toRow == move.fromRow &&
                        move.toColumn == move.fromColumn + 1) {
                    valid = true;
                    //checks lower left square
                } else if (move.toRow == move.fromRow + 1 &&
                        move.toColumn == move.fromColumn - 1) {
                    valid = true;
                    //checks lower mid square
                } else if (move.toRow == move.fromRow + 1 &&
                        move.toColumn == move.fromColumn) {
                    valid = true;
                    //checks lower right square
                } else if (move.toRow == move.fromRow + 1 &&
                        move.toColumn == move.fromColumn + 1) {
                    valid = true;
                }
            }
        return valid;
    }
}
