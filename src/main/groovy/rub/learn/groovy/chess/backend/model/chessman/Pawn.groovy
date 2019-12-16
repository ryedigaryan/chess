package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point
import rub.learn.groovy.chess.common.Side

class Pawn extends AbstractChessman {

    private int maxStep = 2;

    Pawn(Board board, ChessmanType type) {
        super(board, type, ChessmanKind.PAWN)
    }

    Pawn(Point initialPosition, Board board, ChessmanType type) {
        super(initialPosition, board, type, ChessmanKind.PAWN)
    }

    @Override
    protected void notifyMoved(Point oldPosition) {
        if(maxStep == 2)
            maxStep = 1 // pawn can move 2 blocks only first time

        super.notifyMoved(oldPosition)
    }

    @Override
    List<Point> getPossiblePath() {
        ArrayList<Point> path = new ArrayList<>(maxStep);

        Point diff = new Point(0, 0);
        for (increaseToNext(diff); diff.row.abs() <= maxStep; increaseToNext(diff)) {
            Point p = position + diff
            if(board.contains(p) && (!board.isFriendAt(p, this) && !board.isEnemyAt(p, this))) {
                path.add(p);
            }
            p = Side.LEFT.increment(p)
            if(board.contains(p) && board.isEnemyAt(p, this)) {
                // we can go and eat enemy
                path.add(p);
            }
            p = Side.RIGHT.increment(p, 2)
            if(board.contains(p) && board.isEnemyAt(p, this)) {
                // we can go and eat enemy
                path.add(p);
            }
        }

        return path;
    }

    private void increaseToNext(Point p) {
        p << getType().getSide().decrement(p)
    }

    @Override
    String toString() {
        return "p"
    }
}
