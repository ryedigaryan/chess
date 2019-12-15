package rub.learn.groovy.chess.backend.model.chessman


import rub.learn.groovy.chess.backend.model.geo.Position

class ChessmanCachedProxy implements Chessman, ChessmanDelegate {

    private Chessman root;
    private Position[] possiblePositions; // cache

    static Chessman of(Chessman root) {
        return new ChessmanCachedProxy(root);
    }

    ChessmanCachedProxy(Chessman root) {
        this.root = root
        root.addDelegate(this)
        possiblePositions = root.getNextPossiblePositions();
    }

    @Override
    ChessmanType getType() {
        return root.getType();
    }

    @Override
    List<Position> getNextPossiblePositions() {
        return possiblePositions;
    }

    @Override
    void moveTo(Position newPosition) {
        root.moveTo(newPosition)
    }

    @Override
    void addDelegate(ChessmanDelegate dlg) {
        root.addDelegate(dlg)
    }

    @Override
    void movingTo(Chessman c, Position p) {
    }

    @Override
    void moved(Chessman c) {
        // recalculate cache as the chessman will be moved once
        possiblePositions = root.getNextPossiblePositions();
    }

    @Override
    void removed(Chessman chessman) {

    }

    @Override
    void remove() {
        root.remove();
    }
}
