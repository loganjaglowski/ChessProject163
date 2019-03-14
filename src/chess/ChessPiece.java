package chess;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return owner;
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        //  THIS IS A START... More coding needed

        //checks if the attempted move is to the same square
        if ((move.fromRow != move.toRow) || (move.fromColumn != move.toColumn))
            //checks if it's on the board
            if (move.fromRow >= 0 && move.fromRow < 8) {
                if(move.toRow >= 0 && move.toRow < 8)
                    if (move.fromColumn >= 0 && move.toRow < 8)
                        if (move.toColumn >= 0 && move.toColumn < 8)
                            if ((board[move.fromRow][move.fromColumn]) != null)
                                     valid = true;
            }


        return valid;
    }
}
