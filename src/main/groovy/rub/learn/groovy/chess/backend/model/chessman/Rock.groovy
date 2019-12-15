package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

class Rock extends AbstractChessman {

    Rock(Board board, ChessmanType type) {
        super(board, type)
    }

    Rock(Position initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type)
    }

    @Override
    List<Position> getNextPossiblePositions() {
        List<Position> result = new ArrayList<>()

        for(r in [-1, 1]) {
            addAllInDirection(new Position(r, 0), result)
            addAllInDirection(new Position(0, r), result)
        }

        return result;
    }
}
