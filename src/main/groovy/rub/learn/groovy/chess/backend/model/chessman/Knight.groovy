package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

class Knight extends AbstractChessman {

    Knight(Board board, ChessmanType type) {
        super(board, type, ChessmanKind.KNIGHT)
    }

    Knight(Point initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type, ChessmanKind.KNIGHT)
    }

    @Override
    List<Point> getPossiblePath() {
        ArrayList<Point> path = new ArrayList<>();

        Point dir = new Point(1, 1);
        addInDirection(dir, path)
        dir.setColumn(-1);
        dir.setRow(-1)
        addInDirection(dir, path)

        return path;
    }

    private void addInDirection(Point dir, List<Point> result) {
        Point[] dirs = [[2, 1], [2, -1], [1, 2], [1, -2]] as Point[];

        for(d in dirs) {
            def p = position + (dir * d)
            if (board.contains(p) && !board.isFriendAt(p, this)) {
                result.add(p)
            }
        }
    }

    @Override
    String toString() {
        return "k"
    }
}
