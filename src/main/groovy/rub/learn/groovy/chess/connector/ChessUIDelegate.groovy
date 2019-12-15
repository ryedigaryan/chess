package rub.learn.groovy.chess.connector

import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

interface ChessUIDelegate {

    Player createPlayer(String name, ChessmanType chessmanType);
    void playerMovesChess(Player player, Position from, Position to);
}
