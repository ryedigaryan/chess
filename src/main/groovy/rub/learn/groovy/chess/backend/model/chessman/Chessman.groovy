package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point


interface Chessman {

    ChessmanType getType();

    ChessmanKind getKind();

    List<Point> getPossiblePath();

    Point getPosition();

    void setPosition(int row, int column)

    void moveTo(Point newPosition);

    void remove();

    void addDelegate(ChessmanDelegate dlg);

    default boolean isEnemy(Chessman other) {
        if (other == null) {
            return false
        }
        return this.getType() != other.getType();
    }

    default boolean isFriend(Chessman other) {
        if (other == null) {
            return false;
        }
        return this.getType() == other.getType();
    }
}