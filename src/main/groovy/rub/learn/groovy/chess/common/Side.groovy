package rub.learn.groovy.chess.common

enum Side {
    TOP([1, 0] as Point),
    BOTTOM([-1, 0] as Point),
    LEFT([0, -1] as Point),
    RIGHT([0, 1] as Point)
    ;

    private Point incrementVector;

    Side(Point incrementVector) {
        this.incrementVector = incrementVector
    }

    Point increment(Point p, int count = 1) {
        return p + incrementVector * count;
    }

    Point decrement(Point p, int count = 1) {
        return p - incrementVector * count;
    }
}