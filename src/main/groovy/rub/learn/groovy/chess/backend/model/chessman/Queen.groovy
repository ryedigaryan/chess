package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

class Queen extends AbstractChessman {

    Queen(Board board, ChessmanType type) {
        super(board, type, ChessmanKind.QUEEN)
    }

    Queen(Point initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type, ChessmanKind.QUEEN)
    }

    @Override
    List<Point> getPossiblePath() {
        List<Point> path = new ArrayList<>()

        Point zero = new Point(0, 0);
        Point dir = new Point(0, 0);
        for(dir.row = -1; dir.row <= 1; dir.row++) {
            for(dir.column = -1; dir.column <= 1; dir.column++) {
                if(dir != zero) {
                    addAllInDirection(dir, path)
                }
            }
        }

        return path;
    }

    @Override
    String toString() {
        return "Q"
    }
}
