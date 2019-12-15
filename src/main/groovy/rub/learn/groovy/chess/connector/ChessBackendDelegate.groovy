package rub.learn.groovy.chess.connector

import rub.learn.groovy.chess.backend.model.Player

interface ChessBackendDelegate {
    void gameWon(Player winner);
    void gameLost(Player looser);
    void gameDraw(Player first, Player second);
}