package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Position

final class ChessmanFactory {

    private final Board board;

    ChessmanFactory(Board board) {
        this.board = board;
    }

    Chessman whiteBishop(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Bishop(new Position(row, col), board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackBishop(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Bishop(new Position(row, col), board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman whiteKing(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new King(new Position(row, col), board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackKing(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new King(new Position(row, col), board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whiteKnight(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Knight(new Position(row, col), board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackKnight(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Knight(new Position(row, col), board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whitePawn(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Pawn(new Position(row, col), board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackPawn(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Pawn(new Position(row, col), board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whiteQueen(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Queen(new Position(row, col), board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackQueen(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Queen(new Position(row, col), board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whiteRock(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Rock(new Position(row, col), board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackRock(int row, int col) {
        Chessman c = ChessmanCachedProxy.of(new Rock(new Position(row, col), board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whiteBishop() {
        Chessman c = ChessmanCachedProxy.of(new Bishop(board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackBishop() {
        Chessman c = ChessmanCachedProxy.of(new Bishop(board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman whiteKing() {
        Chessman c = ChessmanCachedProxy.of(new King(board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackKing() {
        Chessman c = ChessmanCachedProxy.of(new King(board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whiteKnight() {
        Chessman c = ChessmanCachedProxy.of(new Knight(board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackKnight() {
        Chessman c = ChessmanCachedProxy.of(new Knight(board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whitePawn() {
        Chessman c = ChessmanCachedProxy.of(new Pawn(board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackPawn() {
        Chessman c = ChessmanCachedProxy.of(new Pawn(board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whiteQueen() {
        Chessman c = ChessmanCachedProxy.of(new Queen(board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackQueen() {
        Chessman c = ChessmanCachedProxy.of(new Queen(board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }

    Chessman whiteRock() {
        Chessman c = ChessmanCachedProxy.of(new Rock(board, ChessmanType.WHITE));
        c.addDelegate(board)
        return c
    }

    Chessman blackRock() {
        Chessman c = ChessmanCachedProxy.of(new Rock(board, ChessmanType.BLACK));
        c.addDelegate(board)
        return c
    }
}
