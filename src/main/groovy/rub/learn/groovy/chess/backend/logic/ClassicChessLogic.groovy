package rub.learn.groovy.chess.backend.logic

import rub.learn.groovy.chess.backend.model.ClassicChessboard
import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.backend.model.chessman.Chessman
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point
import rub.learn.groovy.chess.connector.ChessBackend
import rub.learn.groovy.chess.connector.ChessBackendDelegate
import sun.reflect.generics.reflectiveObjects.NotImplementedException

class ClassicChessLogic implements ChessBackend {
    final ClassicChessboard board = new ClassicChessboard()
    final Player[] players = new Player[2];
    private final List<ChessBackendDelegate> delegates = new LinkedList<>();
    private PrintStream errorOutput;
    private GameState currentPlayerState = GameState.IN_PROGRESS;

    // defines which player's turn is now
    private int currPlayerId;

    ClassicChessLogic(PrintStream errorOutput) {
        this.errorOutput = errorOutput
    }

    ClassicChessLogic(Player first, Player second, PrintStream errorOutput) {
        players[0] = first;
        players[1] = second;
        this.errorOutput = errorOutput
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
    boolean changeTurn() {
        checkCurrentPlayerState();
        if(currentPlayerState.isFinal()) {
            return false;
        }
        currPlayerId = nextPlayerId;
        checkCurrentPlayerState();
        return true;
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

    @Override
    boolean moveChessman(Point from, Point to) {
        ensureGameInProgress()

        assert from != null;
        assert to != null;

        if (from == to) {
            errorOutput.println("ERROR: from == to: $from == $to")
            return false;
        }
        Chessman movable = board.getAt(from.row, from.column)
        if (movable == null) {
            errorOutput.println("ERROR: no chessman found at $from")
            return false;
        }
        if (movable.getType() != getCurrentPlayer().getChessmanType()) {
            errorOutput.println("ERROR: trying to move opponent's chessman($movable.type)")
            return false;
        }

        if(currentPlayerState == GameState.KING_IN_CHECK) {
            return applyKingInCheckLogic(movable, from, to);
        }
        else {
            return applyNormalLogic(movable, from, to);
        }
    }

    //TODO: Implement
    //  -  here we should ensure that after moving the movable, king gets out of check state
    boolean applyKingInCheckLogic(Chessman movable, Point from, Point to) {
        throw new NotImplementedException();
    }

    //TODO: Implement
    //  -  here we should ensure that after moving the movable, king does not appears in check state
    boolean applyNormalLogic(Chessman movable, Point from, Point to) {
        List<Point> path = movable.getPossiblePath()
        if (!path.contains(to)) {
            errorOutput.println("ERROR: unreachable tile($to) for $movable.kind from $from")
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
        } else {
            // Destination is enemy, because Chessman#getPossiblePath() excludes friendly units.
            // Though the logic could be extended in other game (or in a chess extension) where
            // attacking friendly unit actually applies positive effect (heals, speed-ups, etc)
            // and this means that that part of code (excluding friendly units' from possible path)
            // should be placed here (in the class which is responsible for logic) but for now
            // this implementation works perfectly for chess, therefore, we do not need to do so
            destination.remove();
            movable.moveTo(to)
        }
        return true;
    }

    private void ensureGameInProgress() {
        if (currentPlayerState.isFinal()) {
            errorOutput.println("Game already ended: $currentPlayer.name $currentPlayerState")
            throw new IllegalStateException("Game already ended")
        }
    }

    // This would be more correct to divide this enum into new 2 enums:
    // 1. GameState - STARTING/IN_PROGRESS/ENDED
    // 2. PlayerState - CHECK/WON/LOST/NOTHING(or null)
    // But to not complicate tings for now we combined them.
    enum GameState {
        KING_IN_CHECK(false),
        LOST(true),
        WON(true),
        IN_PROGRESS(false),
        ;

        private boolean isFinal;

        GameState(boolean isFinal) {
            this.isFinal = isFinal;
        }

        boolean isFinal() {
            return isFinal;
        }
    }

    private void checkCurrentPlayerState() {
        switch (currentPlayerState = calculateCurrentPlayerState()) {
            case GameState.KING_IN_CHECK:
                notifyKingInCheck();
                break
            case GameState.LOST:
                notifyLost(currentPlayer)
                notifyWon(nextPlayer)
                break
            case GameState.WON:
                notifyWon(currentPlayer)
                notifyLost(nextPlayer)
                break
            case GameState.IN_PROGRESS:
                break
        }
    }

    private GameState calculateCurrentPlayerState() {
        // a is current player
        // b is next player

        ChessmanType aType = currentPlayer.chessmanType
        Set<Point> aAttackPos = getAttackingPositions(board.getAll(aType))
        Chessman aKing = board.getKingOf(aType);
        List<Point> aKingPath = aKing.getPossiblePath();

        ChessmanType bType = nextPlayer.chessmanType
        Set<Point> bAttackPos = getAttackingPositions(board.getAll(bType))
        Chessman bKing = board.getKingOf(bType);
        List<Point> bKingPath = bKing.getPossiblePath();

        boolean aKingIsUnderAttack = bAttackPos.contains(aKing.position);
        boolean aKingCannotMove = aKingPath.isEmpty() || bAttackPos.containsAll(aKingPath);
        boolean bKingIsUnderAttack = aAttackPos.contains(bKing.position);
        boolean bKingCannotMove = bKingPath.isEmpty() || aAttackPos.containsAll(bKingPath);

        if(aKingIsUnderAttack) {
            if(aKingCannotMove) {
                return GameState.LOST;
            }
            return GameState.KING_IN_CHECK;
        }

        if(bKingIsUnderAttack) {
            if(bKingCannotMove) {
                return GameState.WON;
            }
        }

        return GameState.IN_PROGRESS;
    }

    void notifyKingInCheck() {

    }

    private void notifyDraw() {
        for (delegate in delegates) {
            delegate.gameDraw(players[0], players[1])
        }
    }

    private void notifyWon(Player winner) {
        for (delegate in delegates) {
            delegate.gameWon(winner)
        }
    }

    private void notifyLost(Player looser) {
        for (delegate in delegates) {
            delegate.gameLost(looser)
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
