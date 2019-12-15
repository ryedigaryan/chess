package rub.learn.groovy.chess.backend.logic

import rub.learn.groovy.chess.backend.model.ClassicChessboard
import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.backend.model.chessman.Chessman
import rub.learn.groovy.chess.backend.model.geo.Position

class GameEngine {
    final ClassicChessboard board = new ClassicChessboard()
    final Player[] players = new Player[2];
    final GameEngineDelegate delegate;
    private boolean gameEnded;

    // defines which player's turn is now
    private int currPlayerId;

    GameEngine(Player first, Player second, GameEngineDelegate delegate) {
        assert delegate != null;
        this.delegate = delegate;
        players[0] = first;
        players[1] = second;
    }

    private void changeTurn() {
        currPlayerId = nextPlayerId;
    }

    private int getNextPlayerId() {
        return currPlayerId == 0 ? 1 : 0;
    }

    Player getCurrentPlayer() {
        return players[currPlayerId];
    }

    Player getNextPlayer() {
        return players[getNextPlayerId()]
    }

    void moveChessman(Position from, Position to) {
        assert from != null;
        assert to != null;

        if (from == to) {
            return
        }
        Chessman movable = board.getAt(from.row, from.column)
        if (movable == null) {
            return
        }
        if(movable.getType() != getCurrentPlayer().getChessmanType()) {
            return
        }
        Chessman destination = board.getAt(to.row, to.column)
        if (destination.isFriend(movable)) {
            return
        }
        movable.moveTo(to)
        if (destination == null) {
            return
        }
        destination.remove();

        checkGameState()
    }

    private void checkGameState() {
        checkCurrentPlayerWon()
        if(!gameEnded) {
            checkDraw();
        }
    }

    private void checkCurrentPlayerWon() {
        if (board.getCount(nextPlayer.chessmanType) == 0) {
            delegate.gameWon(currentPlayer)
            delegate.gameLost(nextPlayer)
            gameEnded = true;
        }
    }

    void checkDraw() {
        Chessman kingOfNext = board.getKingOf(nextPlayer.chessmanType)
        List<Chessman> chessmenOfCurrent = board.getAll(currentPlayer.chessmanType)

        List<Position> kingPositions = kingOfNext.getNextPossiblePositions()
        Set<Position> attackingPositions = getAttackingPositions(chessmenOfCurrent)
        if(attackingPositions.containsAll(kingPositions)) {
            delegate.gameDraw(players[0], players[1])
        }
    }

    private static Set<Position> getAttackingPositions(List<Chessman> chessmen) {
        Set<Position> result = new HashSet<>();
        for(c in chessmen) {
            result.addAll(c.getNextPossiblePositions())
        }
        return result;
    }

}
