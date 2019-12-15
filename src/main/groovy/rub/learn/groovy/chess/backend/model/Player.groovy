package rub.learn.groovy.chess.backend.model

import groovy.transform.TupleConstructor
import rub.learn.groovy.chess.backend.model.chessman.ChessmanType


@TupleConstructor(force = true)
class Player {
    final String name;
    final ChessmanType chessmanType;
}
