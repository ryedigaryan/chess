package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

class Queen extends AbstractChessman {

    Queen(Board board, ChessmanType type) {
        super(board, type)
    }

    Queen(Position initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type)
    }

    @Override
    List<Position> getNextPossiblePositions() {
        List<Position> result = new ArrayList<>()

        Position zero = new Position(0, 0);
        Position dir = new Position(0, 0);
        for(dir.row = -1; dir.row <= 1; dir.row++) {
            for(dir.column = -1; dir.column <= 1; dir.column++) {
                if(dir != zero) {
                    addAllInDirection(dir, result)
                }
            }
        }

        return result;
    }
}
