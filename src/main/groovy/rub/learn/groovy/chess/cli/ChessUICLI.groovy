package rub.learn.groovy.chess.cli

import rub.learn.groovy.chess.backend.model.Board
import rub.learn.groovy.chess.backend.model.Player
import rub.learn.groovy.chess.backend.model.chessman.Chessman
import rub.learn.groovy.chess.common.*
import rub.learn.groovy.chess.connector.ChessUI
import rub.learn.groovy.chess.connector.ChessUIDelegate

class ChessUICLI implements ChessUI {
    private final List<ChessUIDelegate> delegates = new LinkedList<>();
    private Player currentPlayer;

    private final PrintStream output;
    private final Scanner input;

    ChessUICLI(PrintStream output, Scanner input) {
        this.output = output;
        this.input = input;
    }

    @Override
    void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    @Override
    void addDelegate(ChessUIDelegate delegate) {
        delegates.add(delegate);
    }

    @Override
    void showBoard(Board board) {
        char[][] textBoard = boardToText(board);
        boolean reversed = (currentPlayer.getChessmanType().getSide() == Side.BOTTOM);
        printTextBoard(textBoard, reversed);
    }

    @Override
    void clear() {
        output.println(System.lineSeparator() * 20)
    }

    void printTextBoard(char[][] textBoard, boolean reversed) {
        Size s = new Size(textBoard.length, textBoard[0].length)
        if(reversed)
        for (Point p = [s.height - 1, 0]; p.row >= 0; p.row--) {
            for (p.column = 0; p.column < s.width; p.column++) {
                if (p.row > 0 && p.column > 0 && p.row < s.height - 1 && p.column < s.width - 1) {
                    printChessTile(p, textBoard[p.row][p.column])
                } else {
                    output.print(textBoard[p.row][p.column])
                }
            }
            output.println()
        }
        else
        for (Point p = [0, 0]; p.row < s.height; p.row++) {
            for (p.column = 0; p.column < s.width; p.column++) {
                if (p.row > 0 && p.column > 0 && p.row < s.height - 1 && p.column < s.width - 1) {
                    printChessTile(p, textBoard[p.row][p.column])
                } else {
                    output.print(textBoard[p.row][p.column])
                }
            }
            output.println()
        }
    }

    void printChessTile(Point p, char symbol) {
        if ((p.row + p.column) % 2 == 0) {
            output.print("\033[48;5;7m\033[38;5;15m$symbol\033[0m")
        } else {
            output.print("\033[38;5;15m\033[48;5;0m$symbol\033[0m")
        }
    }

    static char[][] boardToText(Board board) {
        def bSize = board.size()
        Size size = bSize + 2
        char[][] boardMatrix = new char[size.height][size.width];

        for (int i in [0, size.height - 1]) {
            for (int j in [0, size.width - 1]) {
                boardMatrix[i][j] = (' ' as char);
            }
        }

        for (int i = 1; i <= bSize.height; ++i) {
            for (int j in [0, size.width - 1]) {
                boardMatrix[i][j] = ('A' as char) + i - 1
            }
        }

        for (int j = 1; j <= bSize.width; ++j) {
            for (int i in [0, size.height - 1]) {
                boardMatrix[i][j] = ('0' as char) + j;
            }
        }

        for (Point p = [0, 0]; p.row < bSize.height; p.row++) {
            for (p.column = 0; p.column < bSize.width; p.column++) {
                Chessman c = board[p]
                boardMatrix[p.row + 1][p.column + 1] = getSymbolFor(c)
            }
        }

        return boardMatrix;
    }

    @Override
    void showGameWon(Player winner) {
        output.println("Congrats $winner.name, you WON !!!!")
    }

    @Override
    void showGameLost(Player looser) {
        output.println("Sorry $looser.name, you LOST :/")
    }

    @Override
    void showGameDraw(Player first, Player second) {
        output.println("Both $first.name & $second.name are EQUALLY AMAZING !!!!")
    }

    @Override
    void processInput() {
        output.print("$currentPlayer.name: ")
        Point from = parsePosition(input.next())
        Point to = parsePosition(input.next())

        for (d in delegates) {
            d.playerMovesChessman(currentPlayer, from, to)
        }
    }

    private static Point parsePosition(String cmd) {
        int r = Character.toUpperCase(cmd.charAt(0)) - ('A' as char) // Chessboard rows are symbols from 'A' to 'Z'
        int c = cmd.charAt(1) - ('1' as char) // Chessboard columns are numbers from 1 to 8

        return new Point(r, c)
    }

    private static char getSymbolFor(Chessman c) {
        if (c == null) {
            return ' ' as char
        }

        final ChessmanKind k = c.getKind();
        final ChessmanType t = c.getType();
        String charIdkWhyAsString;
        switch (k) {
            case ChessmanKind.KING:
                charIdkWhyAsString = (t == ChessmanType.WHITE ? '♔' : '♚')
                break
            case ChessmanKind.QUEEN:
                charIdkWhyAsString = (t == ChessmanType.WHITE ? '♕' : '♛')
                break
            case ChessmanKind.ROOK:
                charIdkWhyAsString = (t == ChessmanType.WHITE ? '♖' : '♜')
                break
            case ChessmanKind.BISHOP:
                charIdkWhyAsString = (t == ChessmanType.WHITE ? '♗' : '♝')
                break
            case ChessmanKind.KNIGHT:
                charIdkWhyAsString = (t == ChessmanType.WHITE ? '♘' : '♞')
                break
            case ChessmanKind.PAWN:
                charIdkWhyAsString = (t == ChessmanType.WHITE ? '♙' : '♟')
                break
        }
        return charIdkWhyAsString as char;
    }
}
