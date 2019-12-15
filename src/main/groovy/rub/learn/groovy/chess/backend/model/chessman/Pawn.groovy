package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

class Pawn extends AbstractChessman {

    private int maxStep = 2;

    Pawn(Board board, ChessmanType type) {
        super(board, type)
    }

    Pawn(Position initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type)
    }

    @Override
    protected void notifyMoved(Position p) {
        if(maxStep == 2)
            maxStep = 1 // pawn can move 2 blocks only first time

        super.notifyMoved(p)
    }

    @Override
    List<Position> getNextPossiblePositions() {
        ArrayList<Position> result = new ArrayList<>(maxStep);

        Position diff = new Position(0, 0);
        for (diff.row = 1; diff.row <= maxStep; diff.row++) {
            Position p = position + diff
            if(board.contains(p) && !board.isFriendAt(p, this)) {
                result.add(p);
            }
        }

        return result;
    }
}
