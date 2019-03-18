package chess;

public class Knight extends ChessPiece {

    public Knight(Player player) {
        super(player);
    }

    public String type() {
        return "Knight";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board){

        boolean valid = false;
        // More code is needed
        if (super.isValidMove(move, board)) {
            if (move.fromRow == move.toRow - 2)
                if (move.fromColumn == move.toColumn - 1) {
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow + 2)
                if (move.fromColumn == move.toColumn - 1) {
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow + 2)
                if (move.fromColumn == move.toColumn + 1) {
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow - 2)
                if (move.fromColumn == move.toColumn + 1) {
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow - 1)
                if (move.fromColumn == move.toColumn - 2) {
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow - 1)
                if (move.fromColumn == move.toColumn + 2) {
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow + 1)
                if (move.fromColumn == move.toColumn - 2) {
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                }
            if (move.fromRow == move.toRow + 1)
                if (move.fromColumn == move.toColumn + 2) {
                    if (super.player() == Player.WHITE) {
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                    if (super.player() == Player.BLACK){
                        if (board[move.toRow][move.toColumn] == null)
                            return true;
                        if (board[move.toRow][move.toColumn].player() == Player.BLACK)
                            valid = true;
                    }
                }
        }
        return valid;
    }

}
