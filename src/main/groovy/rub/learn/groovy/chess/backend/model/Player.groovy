package rub.learn.groovy.chess.backend.model

import groovy.transform.TupleConstructor
import rub.learn.groovy.chess.common.ChessmanType


@TupleConstructor(force = true)
class Player {
    final String name;
    final ChessmanType chessmanType;
}
