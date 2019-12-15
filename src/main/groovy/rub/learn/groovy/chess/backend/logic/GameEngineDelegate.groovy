package rub.learn.groovy.chess.backend.logic

import rub.learn.groovy.chess.backend.model.Player

interface GameEngineDelegate {
    void gameWon(Player winner);
    void gameLost(Player looser);
    void gameDraw(Player first, Player second);
}