package chess;

/**********************************************************************
 * A class that stores conditions about game, changed when pieces move.
 * Changes in booleans should result in JOptionPanes being triggered.
 *********************************************************************/
public class GameStatus {
    private boolean isComplete;
    private boolean inCheck;
    private boolean movedIntoCheck;
    private boolean moveSuccessful;

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isMoveSuccessful() {
        return moveSuccessful;
    }

    public void setMoveSuccessful(boolean moveSuccessful) {
        this.moveSuccessful = moveSuccessful;
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    public boolean isMovedIntoCheck() {
        return movedIntoCheck;
    }

    public void setMovedIntoCheck(boolean movedIntoCheck) {
        this.movedIntoCheck = movedIntoCheck;
    }

    public GameStatus(){
        isComplete = false;
        moveSuccessful = false;
        movedIntoCheck = false;
        inCheck = false;
    }
}
