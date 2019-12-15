package rub.learn.groovy.chess.connector

import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

class ChessGame implements ChessBackendDelegate, ChessUIDelegate {
    private final ChessBackend backend;
    private final ChessUI ui;

    ChessGame(ChessBackend backend, ChessUI ui) {
        this.backend = backend
        this.ui = ui

        backend.addDelegate(this);
        ui.addDelegate(this);
    }

    @Override
    Player createPlayer(String name, ChessmanType chessmanType) {
        final Player p = new Player(name, chessmanType);
        if(chessmanType == ChessmanType.WHITE) {
            backend.setFirstPlayer(p);
            ui.setCurrentPlayer(p)
            return p;
        }
        backend.setSecondPlayer(p);
        return p;
    }

    @Override
    void playerMovesChess(Player player, Position from, Position to) {
        if(player != backend.getCurrentPlayer()) {
            return
        }

        backend.moveChessman(from, to);
        backend.changeTurn()
        ui.setCurrentPlayer(backend.getCurrentPlayer())
    }

    @Override
    void gameWon(Player winner) {
        ui.showGameWon(winner);
    }

    @Override
    void gameLost(Player looser) {
        ui.showGameLost(looser)
    }

    @Override
    void gameDraw(Player first, Player second) {
        ui.showGameDraw(first, second)
    }
}
