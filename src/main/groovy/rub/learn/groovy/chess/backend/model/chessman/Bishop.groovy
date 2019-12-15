package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.backend.model.geo.Position

class Bishop extends AbstractChessman {

    Bishop(Board board, ChessmanType type) {
        super(board, type)
    }

    Bishop(Position initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type)
    }

    @Override
    List<Position> getNextPossiblePositions() {
        List<Position> result = new ArrayList<>()

        Position dir = new Position(1, 1);
        addAllInDirection(dir, result)
        dir.setColumn(-1);
        addAllInDirection(dir, result)
        dir.setRow(-1)
        addAllInDirection(dir, result)
        dir.setColumn(1)
        addAllInDirection(dir, result)

        return result;
    }
}
