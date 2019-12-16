package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.common.Point


interface ChessmanDelegate {

    void movingTo(Chessman c, Point newPosition);
    void moved(Chessman c, Point oldPosition);
    void removed(Chessman chessman, Point position)
}
