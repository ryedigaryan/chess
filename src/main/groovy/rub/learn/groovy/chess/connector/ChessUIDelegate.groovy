package rub.learn.groovy.chess.connector

import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

interface ChessUIDelegate {

    Player createPlayer(String name, ChessmanType chessmanType);
    void playerMovesChessman(Player player, Point from, Point to);
}
