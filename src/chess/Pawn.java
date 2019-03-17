package chess;

public class Pawn extends ChessPiece {

    public Pawn(Player player) {
        super(player);
    }

    public String type() {
        return "Pawn";
    }

    // determines if the move is valid for a pawn piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;

        if (super.isValidMove(move, board)){

            System.out.println(super.player() + "fromRow: " + move.fromRow + "fromCol: " + move.fromColumn );

            //TODO: enable moving diagonally when pawn is capturing another pawn en passant
            //first if control diagonal movement when capturing
            if (board[move.toRow][move.toColumn] != null) {
                if ((board[move.toRow][move.toColumn]).player() != super.player()) {
                    if (move.fromColumn - move.toColumn > 1 || move.fromColumn - move.toColumn < -1) {
                        valid = false;
                    }
                }
            }else {
                if (move.fromColumn - move.toColumn != 0) {
                    valid = false;
                }
            }
            if (super.player() == Player.BLACK) {
                if (this.isEnPassant(move)) {

                    if (move.fromRow - move.toRow < -2 || move.fromRow - move.toRow > -1) {

                        valid = false;
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

                    }
                } else {

                    if (move.fromRow - move.toRow != 1) {
                        valid = false;
                    }
                }
            }
        }else{
            valid = false;
        }




        return valid;
    }

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
