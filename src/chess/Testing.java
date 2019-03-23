package chess;

import org.junit.Test;
import static org.junit.Assert.*;

public class Testing {

    /**testing for correct piece movement */

    //tests bishop movement
    @Test
    public void blackLeftBishopTest(){
        ChessModel test = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(1, 3, 2, 3);
        test.move(movePawn);
        //move bishop
        Move moveBishop = new Move(0, 2, 5, 7);
        test.move(moveBishop);
        assertEquals(test.pieceAt(5, 7).type(), new Bishop(Player.BLACK).type());
    }

    //tests pawn movement
    @Test
    public void testPawn(){
        ChessModel test = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(1, 3, 2, 3);
        test.move(movePawn);
        assertEquals(test.pieceAt(2, 3).type(), new Pawn(Player.BLACK).type());
    }

    //tests king movement
    @Test
    public void kingTest(){
        ChessModel test = new ChessModel();
        //move pawn out of way of king
        Move movePawn = new Move(1, 3, 2, 3);
        test.move(movePawn);
        //move king
        Move kingMove = new Move(0, 4, 1, 3);
        test.move(kingMove);
        assertEquals(test.pieceAt(1, 3).type(), new King(Player.BLACK).type());
    }

    //tests queen movement
    @Test
    public void queenTest(){
        ChessModel t = new ChessModel();
        //move pawn out of way
        Move movePawn = new Move(1, 3, 2, 3);
        t.move(movePawn);
        //move queen
        Move queenMove = new Move(0, 3, 1, 3);
        t.move(queenMove);
        assertEquals(t.pieceAt(1, 3).type(), new Queen(Player.BLACK).type());
    }



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

    //todo: adapt tests for altered isValidMove() method
    //tests empty move
//    @Test
//    public void moveEmpty(){
//        ChessModel test = new ChessModel();
//        Move fail = new Move(3, 1, 4, 2);
//        assertFalse(test.isValidMove(fail));
//    }
//
//    //tests move that lands on own piece
//    @Test
//    public void moveToSelf(){ //fixme: doesn't actually test target code
//        ChessModel test = new ChessModel();
//        Move self = new Move(0, 0, 1, 1);
//        assertFalse(test.isValidMove(self));
//    }


    //tests isComplete() when game is incomplete
    @Test
    public void isCompleteTest(){
        ChessModel test = new ChessModel();
        assertFalse(test.isComplete());
    }

    //tests isComplete() when king is in checkmate
    @Test
    public void isCompTrue(){
        ChessModel test = new ChessModel();
        //todo: finish test
    }


}
