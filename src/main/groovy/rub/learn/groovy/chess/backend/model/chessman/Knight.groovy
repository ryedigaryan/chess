package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

class Knight extends AbstractChessman {

    Knight(Board board, ChessmanType type) {
        super(board, type)
    }

    Knight(Position initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type)
    }

    @Override
    List<Position> getNextPossiblePositions() {
        ArrayList<Position> result = new ArrayList<>();

        Position dir = new Position(1, 1);
        addInDirection(dir, result)
        dir.setColumn(-1);
        dir.setRow(-1)
        addInDirection(dir, result)

        return result;
    }

    private void addInDirection(Position dir, List<Position> result) {
        Position[] dirs = [[2, 1], [2, -1], [1, 2], [1, -2]] as Position[];

        for(d in dirs) {
            def p = position + (dir * d)
            if (board.contains(p) && !board.isFriendAt(p, this)) {
                result.add(p)
            }
        }
    }
}
