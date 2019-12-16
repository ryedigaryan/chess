package rub.learn.groovy.chess.backend.model.chessman


import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

class King extends AbstractChessman {

    King(Board board, ChessmanType type) {
        super(board, type, ChessmanKind.KING)
    }

    King(Point initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type, ChessmanKind.KING)
    }

    @Override
    List<Point> getPossiblePath() {
        ArrayList<Point> path = new ArrayList<>(8);

        for (r in (-1..1)) {
            for (c in (-1..1)) {
                if (r == 0 && c == 0) {
                    continue
                }
                def p = new Point(position.row - r, position.column - c)
                if (board.contains(p) && !board.isFriendAt(p, this)) {
                    // King can't move on the place, where a friendly chessman is located
                    // hence, we return before adding the position to the path
                    path.add(p)
                }
            }
        }
        path.removeAll(position);

        return path;
    }

    @Override
    String toString() {
        return "K"
    }
}
