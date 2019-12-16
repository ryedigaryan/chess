package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

class Rook extends AbstractChessman {

    Rook(Board board, ChessmanType type) {
        super(board, type, ChessmanKind.ROOK)
    }

    Rook(Point initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type, ChessmanKind.ROOK)
    }

    @Override
    List<Point> getPossiblePath() {
        List<Point> path = new ArrayList<>()

        for(r in [-1, 1]) {
            addAllInDirection(new Point(r, 0), path)
            addAllInDirection(new Point(0, r), path)
        }

        return path;
    }

    @Override
    String toString() {
        return "r"
    }
}
