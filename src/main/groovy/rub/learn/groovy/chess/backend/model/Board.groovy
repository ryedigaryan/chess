package rub.learn.groovy.chess.backend.model

import rub.learn.groovy.chess.backend.model.chessman.Chessman
import rub.learn.groovy.chess.backend.model.chessman.ChessmanDelegate
import rub.learn.groovy.chess.backend.model.chessman.ChessmanFactory
import rub.learn.groovy.chess.common.Position
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

    abstract boolean isEmptyAt(Position p);
    abstract boolean isFriendAt(Position p, Chessman other);
    abstract boolean isEnemyAt(Position p, Chessman other);

    boolean contains(Position p) {
        if(p == null) {
            return false;
        }
        return p.row >= 0 && p.column >= 0 &&
               p.row < size().height && p.column < size().width;
    }
}