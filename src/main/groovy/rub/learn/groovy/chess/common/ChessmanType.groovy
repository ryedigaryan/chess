package rub.learn.groovy.chess.common

enum ChessmanType {
    // we always put WHITEs at the bottom of the board, and BLACKs at the top of the board
    // obviously UI can show differently, that's doesnt matter, but the backend applies logic
    // using sides defined here
    WHITE(Side.BOTTOM),
    BLACK(Side.TOP),
    ;

    Side side;

    ChessmanType(Side side) {
        this.side = side
    }
}
