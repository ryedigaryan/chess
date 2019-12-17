package rub.learn.groovy.chess

import rub.learn.groovy.chess.backend.logic.ClassicChessLogic
import rub.learn.groovy.chess.cli.ChessGameCLI
import rub.learn.groovy.chess.cli.ChessUICLI
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.connector.ChessBackend
import rub.learn.groovy.chess.connector.ChessGame
import rub.learn.groovy.chess.connector.ChessUI

class AppRunner {
    static void main(String[] args) {
        println("Running on Groovy " + GroovySystem.getVersion())
        PrintStream output = System.out
        Scanner input = new Scanner(System.in)
        PrintStream errorOutput = new PrintStream(new FileOutputStream("error.log"))

        ChessBackend backend = new ClassicChessLogic(errorOutput)
        ChessUI ui = new ChessUICLI(output, input);
        ChessGame game = new ChessGame(backend, ui)

        ChessGameCLI cli = new ChessGameCLI(output, input);
        game.createPlayer(cli.getFirstPlayerName(), ChessmanType.WHITE);
        game.createPlayer(cli.getSecondPlayerName(), ChessmanType.BLACK);
        game.start()
    }
}
