package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

abstract class AbstractChessman implements Chessman {
    protected final List<ChessmanDelegate> delegates = new ArrayList<>();
    protected final Position position = new Position();
    protected final Board board;
    protected final ChessmanType type;

    AbstractChessman(Board board, ChessmanType type) {
        this.board = board
        this.type = type
    }

    AbstractChessman(Position initialPosition, Board board, ChessmanType type) {
        position << initialPosition
        this.board = board
        this.type = type
    }

    @Override
    ChessmanType getType() {
        return type
    }

    @Override
    void setPosition(int row, int column) {
        position.row = row;
        position.column = column;
    }

    @Override
    // does not checks if the newPosition is possible or not
    void moveTo(Position newPosition) {
        notifyMoving(newPosition);
        position << newPosition;
        notifyMoved(position)
    }

    @Override
    void remove() {
        notifyRemoved();
    }

    void notifyRemoved() {
        for (d in delegates) {
            d.removed(this);
        }
    }

    @Override
    void addDelegate(ChessmanDelegate dlg) {
        delegates.add(dlg)
    }

    protected void notifyMoving(Position p) {
        for (d in delegates) {
            d.movingTo(this, p);
        }
    }

    protected void notifyMoved(Position p) {
        for (d in delegates) {
            d.moved(this);
        }
    }

    protected void addAllInDirection(Position direction, ArrayList<Position> result) {
        Position p = position + direction;
        while (board.contains(p)) {
            if(board.isFriendAt(p, this)) {
                // chessman can't move on the place, where other friendly chessman is located
                // hence, we return before adding the position to the result list
                return
            }
            result.add(p);
            p += direction;
            if(board.isEnemyAt(p, this)) {
                // chessman can move on the place, where an enemy chessman is located
                // hence, we return after adding the position to the result list
                return
            }
        }
    }
}
