package rub.learn.groovy.chess.connector

import rub.learn.groovy.chess.backend.model.Player

interface ChessUI {

    void setCurrentPlayer(Player p);
    void addDelegate(ChessUIDelegate delegate);
//    void removeDelegate(ChessUIDelegate delegate); // we don't use this, to not complicate things :)

    void showGameWon(Player winner);
    void showGameLost(Player looser);
    void showGameDraw(Player first, Player second);
}
