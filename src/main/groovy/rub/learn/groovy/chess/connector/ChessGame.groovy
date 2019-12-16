package rub.learn.groovy.chess.connector

import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

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
    void playerMovesChessman(Player player, Point from, Point to) {
        if(player != backend.getCurrentPlayer()) {
            return
        }

        if(backend.moveChessman(from, to)) {
            backend.changeTurn()
            ui.setCurrentPlayer(backend.getCurrentPlayer())
        }
    }

    @Override
    void gameWon(Player winner) {
        if(!isGameInProgress) {
            return;
        }

        isGameInProgress = false;
        ui.showGameWon(winner);
    }

    @Override
    void gameLost(Player looser) {
        if(!isGameInProgress) {
            return;
        }

        isGameInProgress = false;
        ui.showGameLost(looser)
    }

    @Override
    void gameDraw(Player first, Player second) {
        if(!isGameInProgress) {
            return;
        }

        isGameInProgress = false;
        ui.showGameDraw(first, second)
    }


    private boolean isGameInProgress;
    void start() {
        if(isGameInProgress) {
            return;
        }

        isGameInProgress = true;
        while(isGameInProgress) {
            ui.clear();
            ui.showBoard(backend.getBoard());
            // no FPS, everything is sequential, hence we do not worry about
            // million times of calling this method
            // process input should wait(sleep) until user interacts with the game
            ui.processInput();
        }
    }
}
