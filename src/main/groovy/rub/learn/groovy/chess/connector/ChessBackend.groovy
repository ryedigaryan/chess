package rub.learn.groovy.chess.connector

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.common.Point

interface ChessBackend {

    Board getBoard();

    void addDelegate(ChessBackendDelegate delegate);
//    void removeDelegate(ChessBackendDelegate delegate); // we don't use this, to not complicate things :)

    Player getCurrentPlayer();

    boolean changeTurn();

    // returns true if succeeded, usually changeTurns() should be called if succeeded
    boolean moveChessman(Point from, Point to);

    void setFirstPlayer(Player first);

    void setSecondPlayer(Player second);
}
