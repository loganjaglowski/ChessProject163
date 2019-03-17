package chess;

public class Bishop extends ChessPiece {

    public Bishop(Player player) {
        super(player);
    }

    public String type() {
        return "Bishop";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = true;

        if (super.isValidMove(move, board)){
            valid = false;
            for (int i = 1; i < 9; i++){
                if (move.fromRow + i == move.toRow && move.fromColumn + i == move.toColumn){
                    valid = true;
                    for (int j = 1; j < i; j++){
                        if (board[move.fromRow + j][move.fromColumn + j] != null){
                            valid = false;
                            break;
                        }
                    }
                    break;
                }
                if (move.fromRow - i == move.toRow && move.fromColumn + i == move.toColumn){
                    valid = true;
                    for (int j = 1; j < i; j++){
                        if (board[move.fromRow - j][move.fromColumn + j] != null){
                            valid = false;
                            break;
                        }
                    }
                    break;
                }
                if (move.fromRow - i == move.toRow && move.fromColumn - i == move.toColumn){
                    valid = true;
                    for (int j = 1; j < i; j++){
                        if (board[move.fromRow - j][move.fromColumn - j] != null){
                            valid = false;
                            break;
                        }
                    }
                    break;
                }
                if (move.fromRow + i == move.toRow && move.fromColumn - i == move.toColumn){
                    valid = true;
                    for (int j = 1; j < i; j++){
                        if (board[move.fromRow + j][move.fromColumn - j] != null){
                            valid = false;
                            break;
                        }
                    }
                    break;
                }
            }
        }else{
            valid = false;
        }


        return valid;
    }
}
