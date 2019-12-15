package rub.learn.groovy.chess.backend.model.chessman


import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

class King extends AbstractChessman {

    King(Board board, ChessmanType type) {
        super(board, type)
    }

    King(Position initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type)
    }

    @Override
    List<Position> getNextPossiblePositions() {
        ArrayList<Position> result = new ArrayList<>(8);

        for (r in (-1..1)) {
            for (c in (-1..1)) {
                if (r == 0 && c == 0) {
                    continue
                }
                def p = new Position(position.row - r, position.column - c)
                if (board.contains(p)) {
                    if(board.isFriendAt(p, this)) {
                        // King can't move on the place, where a friendly chessman is located
                        // hence, we return before adding the position to the result list
                        return
                    }
                    result.add(p)
                }
            }
        }
        result.removeAll(position);

        return result;
    }
}
