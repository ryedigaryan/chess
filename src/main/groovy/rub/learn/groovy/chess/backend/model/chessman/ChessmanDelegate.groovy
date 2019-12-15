package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.common.Position


interface ChessmanDelegate {

    void movingTo(Chessman c, Position p);
    void moved(Chessman c);
    void removed(Chessman chessman)
}
