package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class Testing {

    //tests constructor and currentPlayer()
    @Test
    public void constructorTest(){
        ChessModel test = new ChessModel();
        assertEquals(test.currentPlayer(), Player.WHITE);
    }

    //tests pieceAt() for black pawn
    @Test
    public void pieceAtKingTest(){
        ChessModel test = new ChessModel();
        assertTrue(test.pieceAt(1, 1).type() == new Pawn(Player.BLACK).type() );
    }

    //todo: write tests for all other pieces

    //tests setNextPlayer() on white player
    @Test
    public void testNextPlayerWhite(){
        ChessModel test = new ChessModel();
        test.setNextPlayer();
        assertEquals(test.currentPlayer(), Player.BLACK);
    }

    //tests setNextPlayer() on black player
    @Test
    public void testNextPlayerBlack(){
        ChessModel test = new ChessModel();
        test.setNextPlayer();
        test.setNextPlayer();
        assertEquals(test.currentPlayer(), Player.WHITE);
    }

    //tests setPiece on occupied square (board [1][1])
    @Test
    public void testSetPieceKing(){
        ChessModel test = new ChessModel();
        test.setPiece(1, 1, new King(Player.BLACK));
        assertEquals(test.pieceAt(1, 1).type(), new King(Player.BLACK).type());
    }
    //todo: test more pieces and board indexes

    //tests normal move
    @Test
    public void moveTest(){
        ChessModel test = new ChessModel();
        Move m = new Move(1, 1, 2, 1);
        test.move(m);
        assertEquals(test.pieceAt(2, 1).type(), new Pawn(Player.BLACK).type());
    }

    //tests empty move
    @Test
    public void moveEmpty(){
        ChessModel test = new ChessModel();
        Move fail = new Move(3, 1, 4, 2);
        assertFalse(test.isValidMove(fail));
    }


    //tests isComplete() when game is incomplete
    @Test
    public void isCompleteTest(){
        ChessModel test = new ChessModel();
        assertFalse(test.isComplete());
    }


}
