package rub.learn.groovy.chess.connector

import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.common.Position

interface ChessBackend {

    void addDelegate(ChessBackendDelegate delegate);
//    void removeDelegate(ChessBackendDelegate delegate); // we don't use this, to not complicate things :)

    Player getCurrentPlayer();

    void changeTurn();

    void moveChessman(Position from, Position to);

    void setFirstPlayer(Player first);

    void setSecondPlayer(Player second);
}
