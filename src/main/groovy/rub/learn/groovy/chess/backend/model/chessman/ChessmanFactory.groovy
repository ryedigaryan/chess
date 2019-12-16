package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point

final class ChessmanFactory {

    private final Board board;

    ChessmanFactory(Board board) {
        this.board = board;
    }

    private Chessman proxy(Chessman r) {
//        Chessman c = ChessmanCachingProxy.of(r)
        Chessman c = r
        c.addDelegate(board)
        return c
    }

    Chessman whiteBishop(int row, int col) {
        Chessman c = proxy(new Bishop(new Point(row, col), board, ChessmanType.WHITE));
        return c
    }

    Chessman blackBishop(int row, int col) {
        Chessman c = proxy(new Bishop(new Point(row, col), board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteKing(int row, int col) {
        Chessman c = proxy(new King(new Point(row, col), board, ChessmanType.WHITE));
        return c
    }

    Chessman blackKing(int row, int col) {
        Chessman c = proxy(new King(new Point(row, col), board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteKnight(int row, int col) {
        Chessman c = proxy(new Knight(new Point(row, col), board, ChessmanType.WHITE));
        return c
    }

    Chessman blackKnight(int row, int col) {
        Chessman c = proxy(new Knight(new Point(row, col), board, ChessmanType.BLACK));
        return c
    }

    Chessman whitePawn(int row, int col) {
        Chessman c = proxy(new Pawn(new Point(row, col), board, ChessmanType.WHITE));
        return c
    }

    Chessman blackPawn(int row, int col) {
        Chessman c = proxy(new Pawn(new Point(row, col), board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteQueen(int row, int col) {
        Chessman c = proxy(new Queen(new Point(row, col), board, ChessmanType.WHITE));
        return c
    }

    Chessman blackQueen(int row, int col) {
        Chessman c = proxy(new Queen(new Point(row, col), board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteRock(int row, int col) {
        Chessman c = proxy(new Rook(new Point(row, col), board, ChessmanType.WHITE));
        return c
    }

    Chessman blackRock(int row, int col) {
        Chessman c = proxy(new Rook(new Point(row, col), board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteBishop() {
        Chessman c = proxy(new Bishop(board, ChessmanType.WHITE));
        return c
    }

    Chessman blackBishop() {
        Chessman c = proxy(new Bishop(board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteKing() {
        Chessman c = proxy(new King(board, ChessmanType.WHITE));
        return c
    }

    Chessman blackKing() {
        Chessman c = proxy(new King(board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteKnight() {
        Chessman c = proxy(new Knight(board, ChessmanType.WHITE));
        return c
    }

    Chessman blackKnight() {
        Chessman c = proxy(new Knight(board, ChessmanType.BLACK));
        return c
    }

    Chessman whitePawn() {
        Chessman c = proxy(new Pawn(board, ChessmanType.WHITE));
        return c
    }

    Chessman blackPawn() {
        Chessman c = proxy(new Pawn(board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteQueen() {
        Chessman c = proxy(new Queen(board, ChessmanType.WHITE));
        return c
    }

    Chessman blackQueen() {
        Chessman c = proxy(new Queen(board, ChessmanType.BLACK));
        return c
    }

    Chessman whiteRock() {
        Chessman c = proxy(new Rook(board, ChessmanType.WHITE));
        return c
    }

    Chessman blackRock() {
        Chessman c = proxy(new Rook(board, ChessmanType.BLACK));
        return c
    }
}
