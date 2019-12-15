package rub.learn.groovy.chess.backend.model

import rub.learn.groovy.chess.backend.model.chessman.Chessman
import rub.learn.groovy.chess.backend.model.chessman.ChessmanFactory
import rub.learn.groovy.chess.backend.model.chessman.ChessmanType
import rub.learn.groovy.chess.backend.model.chessman.King
import rub.learn.groovy.chess.backend.model.geo.Position
import rub.learn.groovy.chess.backend.model.geo.Size

class ClassicChessboard extends Board {

    private final Size size;
    private final Map<ChessmanType, LinkedList<Chessman>> chessmen;
    private final Chessman[][] grid;

    ClassicChessboard() {
        size = new Size(8, 8)
        chessmen = new HashMap<>()
        chessmen.put(ChessmanType.WHITE, new LinkedList<Chessman>())
        chessmen.put(ChessmanType.BLACK, new LinkedList<Chessman>())
        grid = new Chessman[size.height][size.width];
        initChessmen();
    }

    void initChessmen() {
        ChessmanFactory cf = chessmanFactory();
        // init pawns
        for (int j = 0; j < size.width; j++) {
            placeAt(1, j, cf.whitePawn())
        }
        for (int j = 0; j < size.width; j++) {
            placeAt(6, j, cf.blackPawn())
        }
        // init rocks
        placeAt(0, 0, cf.whiteRock())
        placeAt(0, 7, cf.whiteRock())
        placeAt(7, 0, cf.blackRock())
        placeAt(7, 7, cf.blackRock())
        // init knights
        placeAt(0, 1, cf.whiteKnight())
        placeAt(0, 6, cf.whiteKnight())
        placeAt(7, 1, cf.blackKnight())
        placeAt(7, 6, cf.blackKnight())
        // init bishops
        placeAt(0, 2, cf.whiteBishop())
        placeAt(0, 5, cf.whiteBishop())
        placeAt(7, 2, cf.blackBishop())
        placeAt(7, 5, cf.blackBishop())
        // init king & queen
        placeAt(0, 3, cf.whiteQueen())
        placeAt(0, 4, cf.whiteKing())
        placeAt(7, 3, cf.blackQueen())
        placeAt(7, 4, cf.blackKing())
    }

    @Override
    Size size() {
        return size;
    }

    @Override
    boolean isEmptyAt(Position p) {
        return grid[p.row][p.column] == null
    }

    @Override
    boolean isFriendAt(Position p, Chessman other) {
        assert other != null;
        Chessman c = grid[p.row][p.column]
        return other.isFriend(c);
    }

    @Override
    boolean isEnemyAt(Position p, Chessman other) {
        assert other != null;
        Chessman c = grid[p.row][p.column]
        return other.isEnemy(c);
    }

    Chessman getAt(int r, int c) {
        return grid[r][c];
    }

    protected void placeAt(int r, int c, Chessman chessman) {
        grid[r][c] = chessman;
        chessman.setPosition(r, c)
        chessmen.get(chessman.getType())
                .add(chessman);
    }

    List<Chessman> getAll(ChessmanType type) {
        return chessmen.get(type)
    }

    Chessman getKingOf(ChessmanType type) {
        for(c in getAll(type)) {
            if(c instanceof King) {
                return c;
            }
        }
        return null;
    }

    @Override
    void movingTo(Chessman c, Position p) {

    }

    @Override
    void moved(Chessman c) {

    }

    @Override
    void removed(Chessman chessman) {
        boolean removed = chessmen.get(chessman.getType())
                                  .remove(chessman)
        assert removed;
    }

    int getCount(ChessmanType type) {
        return chessmen.get(type)
                       .size();
    }
}
