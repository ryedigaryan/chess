package rub.learn.groovy.chess.common

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@TupleConstructor(defaults = false, force = true)
@ToString(includePackage = false)
@EqualsAndHashCode(allProperties = true)
class Point {
    int row;
    int column;

    Point() {}

    Point(Point other) {
        this << other;
    }

    void rotateClockwise(Point center = [0, 0]) {
        if(center != ([0, 0] as Point)) {
            //TODO: implement
            throw new NotImplementedException()
        }
        int r = row;
        row = -column;
        column = r;
    }

    void rotateCounterClockwise(Point center = [0, 0]) {
        if(center != ([0, 0] as Point)) {
            //TODO: implement
            throw new NotImplementedException()
        }
        int c = column;
        column = -row;
        row = c;
    }

    void goLeft(int amount = 1) {
        column -= amount;
    }

    void goRight(int amount = 1) {
        column += amount;
    }

    void goTop(int amount = 1) {
        row += amount;
    }

    void goBottom(int amount = 1) {
        row -= amount;
    }

    //////////////////////////////
    // Operation overloading
    //////////////////////////////

    void leftShift(Point right) {
        assert right != null;
        row = right.row
        column = right.column;
    }

    void rightShift(Point left) {
        assert left != null
        left << this;
    }

    Point plus(Point p) {
        return new Point(row + p.row, column + p.column)
    }

    Point minus(Point p) {
        return new Point(row - p.row, column - p.column)
    }

    Point multiply(Point p) {
        return new Point(row * p.row, column * p.column)
    }

    Point div(Point p) {
        return new Point(row.intdiv(p.row), column.intdiv(p.column))
    }

    Point multiply(Number k) {
        return new Point(row * k, column * k)
    }
}
