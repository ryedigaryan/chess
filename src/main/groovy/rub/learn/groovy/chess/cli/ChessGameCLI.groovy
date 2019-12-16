package rub.learn.groovy.chess.cli

class ChessGameCLI {

    final String firstPlayerName;
    final String secondPlayerName;

    ChessGameCLI(PrintStream output, Scanner input) {
        output.print("Input First player (P1) Name: ")
        firstPlayerName = input.nextLine()
        output.print("Input Second player (P1) Name: ")
        secondPlayerName = input.nextLine()
    }
}
