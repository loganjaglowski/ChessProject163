package chess;

public class Rook extends ChessPiece {

    public Rook(Player player) {

        super(player);

    }

    public String type() {

        return "Rook";

    }

    // determines if the move is valid for a rook piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean valid = false;
        if (super.isValidMove(move, board)) {
            if (move.fromRow == move.toRow)
                if (move.fromColumn != move.toColumn)
                    valid = true;
            if (move.fromColumn == move.toColumn)
                if (move.fromRow != move.toRow)
                    valid = true;
        }
        return valid;

    }

}
