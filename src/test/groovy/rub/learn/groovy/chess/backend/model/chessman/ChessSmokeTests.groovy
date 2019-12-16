package rub.learn.groovy.chess.backend.model.chessman

import org.testng.Assert
import org.testng.Reporter
import org.testng.annotations.Test
import rub.learn.groovy.chess.backend.model.ClassicChessboard

class ChessSmokeTests {

    static ClassicChessboard board = new ClassicChessboard();

    @Test
    void bishopTest1() {
        Chessman bs = board.chessmanFactory().whiteBishop(3, 3)
        Assert.assertNotNull(bs, "Bishop is null")
        def bsNextPoses = bs.getPossiblePath()
        Assert.assertNotNull(bsNextPoses, "Bishop next positions is null")
        Assert.assertEquals(bsNextPoses.size(), 13)
        Reporter.log(bsNextPoses as String)
    }

    @Test
    void bishopTest2() {
        Chessman bs = board.chessmanFactory().whiteBishop(5, 2)
        Assert.assertNotNull(bs, "Bishop is null")
        def bsNextPoses = bs.getPossiblePath()
        Assert.assertNotNull(bsNextPoses, "Bishop next positions is null")
        Assert.assertEquals(bsNextPoses.size(), 11)
        Reporter.log(bsNextPoses as String)
    }

    @Test
    void kingTest() {
        Chessman k = board.chessmanFactory().whiteKing(5, 5)
        Assert.assertNotNull(k, "King is null")
        def bsNextPoses = k.getPossiblePath()
        Assert.assertNotNull(bsNextPoses, "King next positions is null")
        Assert.assertEquals(bsNextPoses.size(), 8)
        Reporter.log(bsNextPoses as String)
    }

    @Test
    void knightTest() {
        Chessman k = board.chessmanFactory().whiteKnight(5, 5)
        Assert.assertNotNull(k, "Knight is null")
        def bsNextPoses = k.getPossiblePath()
        Assert.assertNotNull(bsNextPoses, "Knight next positions is null")
        Assert.assertEquals(bsNextPoses.size(), 8)
        Reporter.log(bsNextPoses as String)
    }

    @Test
    void pawnTest() {
        Chessman p = board.chessmanFactory().whitePawn(2, 3)
        Assert.assertNotNull(p, "Pawn is null")
        def bsNextPoses = p.getPossiblePath()
        Assert.assertNotNull(bsNextPoses, "Pawn next positions is null")
        Assert.assertEquals(bsNextPoses.size(), 2)
        Reporter.log(bsNextPoses as String)
        Reporter.log("Moving pawn")

        p.moveTo(bsNextPoses[0])
        bsNextPoses = p.getPossiblePath()
        Assert.assertNotNull(bsNextPoses, "Pawn next positions is null")
        Assert.assertEquals(bsNextPoses.size(), 1)
        Reporter.log(bsNextPoses as String)
    }

    @Test
    void queenTest() {
        Chessman q = board.chessmanFactory().whiteQueen(2, 2)
        Assert.assertNotNull(q, "Queen is null")
        def bsNextPoses = q.getPossiblePath()
        Assert.assertNotNull(bsNextPoses, "Queen next positions is null")
        Assert.assertEquals(bsNextPoses.size(), 25)
        Reporter.log(bsNextPoses as String)
    }

    @Test
    void rockTest() {
        Chessman k = board.chessmanFactory().whiteRock(0, 0)
        Assert.assertNotNull(k, "Rook is null")
        def bsNextPoses = k.getPossiblePath()
        Assert.assertNotNull(bsNextPoses, "Rook next positions is null")
        Assert.assertEquals(bsNextPoses.size(), 14)
        Reporter.log(bsNextPoses as String)
    }
}
