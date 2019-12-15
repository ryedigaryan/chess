package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position


interface Chessman {

    ChessmanType getType();

    List<Position> getNextPossiblePositions();

    void setPosition(int row, int column)

    void moveTo(Position newPosition);

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