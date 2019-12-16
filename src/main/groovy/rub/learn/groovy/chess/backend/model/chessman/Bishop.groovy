package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

class Bishop extends AbstractChessman {

    Bishop(Board board, ChessmanType type) {
        super(board, type, ChessmanKind.BISHOP)
    }

    Bishop(Point initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type, ChessmanKind.BISHOP)
    }

    @Override
    List<Point> getPossiblePath() {
        List<Point> path = new ArrayList<>()

        Point dir = new Point(1, 1);
        addAllInDirection(dir, path)
        dir.rotateClockwise()
        addAllInDirection(dir, path)
        dir.rotateClockwise()
        addAllInDirection(dir, path)
        dir.rotateClockwise()
        addAllInDirection(dir, path)

        return path;
    }

    @Override
    String toString() {
        return "b"
    }
}
