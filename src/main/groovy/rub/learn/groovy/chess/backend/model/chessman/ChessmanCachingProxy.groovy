package rub.learn.groovy.chess.backend.model.chessman

import rub.learn.groovy.chess.common.ChessmanKind
import rub.learn.groovy.chess.common.ChessmanType
import rub.learn.groovy.chess.common.Point


class ChessmanCachingProxy implements Chessman, ChessmanDelegate {

    private Chessman root;
    private Point[] cachedPath;

    static Chessman of(Chessman root) {
        return new ChessmanCachingProxy(root);
    }

    private ChessmanCachingProxy(Chessman root) {
        this.root = root
        root.addDelegate(this)
        cachedPath = root.getPossiblePath();
    }

    @Override
    ChessmanType getType() {
        return root.getType();
    }

    @Override
    ChessmanKind getKind() {
        return root.getKind()
    }

    @Override
    void setPosition(int row, int column) {
        root.setPosition(row, column)
    }

    @Override
    List<Point> getPossiblePath() {
        return cachedPath;
    }

    @Override
    void moveTo(Point newPosition) {
        root.moveTo(newPosition)
    }

    @Override
    void addDelegate(ChessmanDelegate dlg) {
        root.addDelegate(dlg)
    }

    @Override
    void movingTo(Chessman c, Point newPosition) {
    }

    @Override
    void moved(Chessman c, Point oldPosition) {
        cachedPath = root.getPossiblePath();
    }

    @Override
    void removed(Chessman chessman, Point position) {
        cachedPath = new Point[0];
    }

    @Override
    void remove() {
        root.remove();
    }

    @Override
    String toString() {
        return root.toString()
    }
}
