package rub.learn.groovy.chess.backend.logic

import rub.learn.groovy.chess.backend.model.ClassicChessboard
import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.backend.model.chessman.Chessman
import rub.learn.groovy.chess.common.Point
import rub.learn.groovy.chess.connector.ChessBackend
import rub.learn.groovy.chess.connector.ChessBackendDelegate

class ChessLogic implements ChessBackend {
    final ClassicChessboard board = new ClassicChessboard()
    final Player[] players = new Player[2];
    private final List<ChessBackendDelegate> delegates = new LinkedList<>();
    private boolean gameEnded;

    // defines which player's turn is now
    private int currPlayerId;

    ChessLogic() {
    }

    ChessLogic(Player first, Player second) {
        players[0] = first;
        players[1] = second;
    }

    @Override
    void setFirstPlayer(Player first) {
        players[0] = first;
    }

    @Override
    void setSecondPlayer(Player second) {
        players[1] = second;
    }

    @Override
    void changeTurn() {
        currPlayerId = nextPlayerId;
    }

    private int getNextPlayerId() {
        return currPlayerId == 0 ? 1 : 0;
    }

    @Override
    Player getCurrentPlayer() {
        return players[currPlayerId];
    }

    Player getNextPlayer() {
        return players[getNextPlayerId()]
    }

    @Override
    void addDelegate(ChessBackendDelegate delegate) {
        delegates.add(delegate);
    }

    //TODO: replace all println statements
    @Override
    boolean moveChessman(Point from, Point to) {
        assert from != null;
        assert to != null;

        if (from == to) {
            println("ERROR: from == to: $from == $to")
            return false;
        }
        Chessman movable = board.getAt(from.row, from.column)
        if (movable == null) {
            println("ERROR: no chessman found at $from")
            return false;
        }
        if (movable.getType() != getCurrentPlayer().getChessmanType()) {
            println("ERROR: trying to move opponent's chessman($movable.type)")
            return false;
        }

        List<Point> path = movable.getPossiblePath()
        if(!path.contains(to)) {
            println("ERROR: unreachable tile($to) for $movable.kind from $from")
            return false;
        }

        Chessman destination = board.getAt(to.row, to.column)
        /*
         * 3 cases are possible
         * 1. Destination is empty (is null) - just moving to new position
         * 2. There is enemy unit on the destination - remove enemy unit and move to it's position
         */
        if (destination == null) {
            movable.moveTo(to)
        }
        else {
            // Destination is enemy, because Chessman#getPossiblePath() excludes friendly units.
            // Though the logic could be extended in other game (or in a chess extension) where
            // attacking friendly unit actually applies positive effect (heals, speed-ups, etc)
            // and this means that that part of code (excluding friendly units' from possible path)
            // should be placed here (in the class which is responsible for logic) but for now
            // this implementation works perfectly for chess, therefore, we do not need to do so
            destination.remove();
            movable.moveTo(to)
        }

        checkGameState()
        println("OK")
        return true
    }

    private void checkGameState() {
        checkCurrentPlayerWon()
        if (!gameEnded) {
            checkDraw();
        }
    }

    // TODO: Wrong logic, mb instead should check if current lost
    private void checkCurrentPlayerWon() {
        if (board.getCount(nextPlayer.chessmanType) == 0) {
            for (delegate in delegates) {
                delegate.gameWon(currentPlayer)
                delegate.gameLost(nextPlayer)
            }
            gameEnded = true;
        }
    }

    // TODO: Wrong logic
    void checkDraw() {
        Chessman kingOfNext = board.getKingOf(nextPlayer.chessmanType)
        List<Chessman> chessmenOfCurrent = board.getAll(currentPlayer.chessmanType)

        List<Point> kingPositions = kingOfNext.getPossiblePath()
        if(kingPositions.isEmpty()) {
            // kingPositions is empty when it is bounded with friendly units or chessboards's walls
            return;
        }
        Set<Point> attackingPositions = getAttackingPositions(chessmenOfCurrent)
        if (attackingPositions.containsAll(kingPositions)) {
            for (delegate in delegates) {
                delegate.gameDraw(players[0], players[1])
            }
        }
    }

    private static Set<Point> getAttackingPositions(List<Chessman> chessmen) {
        Set<Point> result = new HashSet<>();
        for (c in chessmen) {
            result.addAll(c.getPossiblePath())
        }
        return result;
    }

}
