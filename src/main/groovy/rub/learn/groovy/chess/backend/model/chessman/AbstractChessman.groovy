package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

abstract class AbstractChessman implements Chessman {
    protected final List<ChessmanDelegate> delegates = new ArrayList<>();
    protected final Point position = new Point();
    protected final Board board;
    protected final ChessmanType type;
    protected final ChessmanKind kind;

    AbstractChessman(Board board, ChessmanType type, ChessmanKind kind) {
        this.board = board
        this.type = type
        this.kind = kind
    }

    AbstractChessman(Point initialPosition, Board board, ChessmanType type, ChessmanKind kind) {
        position << initialPosition
        this.board = board
        this.type = type
        this.kind = kind
    }

    @Override
    ChessmanType getType() {
        return type
    }

    @Override
    ChessmanKind getKind() {
        return kind;
    }

    @Override
    Point getPosition() {
        return position;
    }

    @Override
    void setPosition(int row, int column) {
        position.row = row;
        position.column = column;
    }

    @Override
    // does not checks if the newPosition is possible or not
    void moveTo(Point newPosition) {
        notifyMoving(newPosition);
        Point oldPosition = new Point(position);
        position << newPosition;
        notifyMoved(oldPosition)
    }

    @Override
    void remove() {
        notifyRemoved();
    }

    void notifyRemoved() {
        for (d in delegates) {
            d.removed(this, position);
        }
    }

    @Override
    void addDelegate(ChessmanDelegate dlg) {
        delegates.add(dlg)
    }

    protected void notifyMoving(Point newPosition) {
        for (d in delegates) {
            d.movingTo(this, newPosition);
        }
    }

    protected void notifyMoved(Point oldPosition) {
        for (d in delegates) {
            d.moved(this, oldPosition);
        }
    }

    protected void addAllInDirection(Point direction, ArrayList<Point> path) {
        Point p = position + direction;
        while (board.contains(p)) {
            if(board.isFriendAt(p, this)) {
                // chessman CAN'T move on the place, where other friendly chessman is located
                // hence, we return BEFORE adding the position to the path
                return
            }
            path.add(p);
            if(board.isEnemyAt(p, this)) {
                // chessman CAN move on the place, where an enemy chessman is located
                // hence, we return AFTER adding the position to the path
                return
            }
            p += direction;
        }
    }
}
