package rub.learn.groovy.chess.backend.model

import rub.learn.groovy.chess.backend.model.chessman.Chessman
import rub.learn.groovy.chess.backend.model.chessman.ChessmanDelegate
import rub.learn.groovy.chess.backend.model.chessman.ChessmanFactory
import rub.learn.groovy.chess.common.Point
import rub.learn.groovy.chess.common.Size

abstract class Board implements ChessmanDelegate {

    private ChessmanFactory cf;

    abstract Size size();

    Board() {
        cf = new ChessmanFactory(this)
    }

    ChessmanFactory chessmanFactory() {
        return cf
    }

    abstract Chessman getAt(Point p);
    abstract boolean isEmptyAt(Point p);
    abstract boolean isFriendAt(Point p, Chessman other);
    abstract boolean isEnemyAt(Point p, Chessman other);

    boolean contains(Point p) {
        if(p == null) {
            return false;
        }
        return p.row >= 0 && p.column >= 0 &&
               p.row < size().height && p.column < size().width;
    }
}