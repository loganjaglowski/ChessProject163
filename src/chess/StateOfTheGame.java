package chess;

import java.util.ArrayList;

public class StateOfTheGame {
    private ArrayList<ChessModel> state;
    private int currentState = 0;
    private int totalStates = 0;
    private ChessModel beginningModel;


    public StateOfTheGame (ChessModel model) {
        state = new ArrayList<>();
        beginningModel = new ChessModel();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                IChessPiece piece = model.pieceAt(row,col);
                beginningModel.setPiece(row, col, piece);
            }
        }
        beginningModel.setPlayer(model.currentPlayer());
        state.add(0, beginningModel);
        totalStates++;
    }

    public void saveState (ChessModel model) {
        if (currentState > 0) {
            for (int delete = 0; delete < currentState; delete++) {
                state.remove(0);
            }
        }
        ChessModel oldModel = new ChessModel();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                IChessPiece piece = model.pieceAt(row,col);
                oldModel.setPiece(row, col, piece);
            }
        }
        oldModel.setPlayer(model.currentPlayer());
        state.add(0, oldModel);
        currentState = 0;
        totalStates++;
    }

    public ChessModel loadState () {
        if(currentState >= state.size() - 1)
            return state.get(state.size() - 1);
        return state.get(currentState);
    }

    public boolean checkIfBeginningModel() {
        if (currentState - state.size() == 0)
            return true;
        return false;
    }
    public void incrementState() {
        currentState++;
    }

}
