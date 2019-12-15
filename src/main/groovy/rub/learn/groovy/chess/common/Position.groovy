package rub.learn.groovy.chess.common

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@TupleConstructor(defaults = false, force = true)
@ToString(includePackage = false)
@EqualsAndHashCode(allProperties = true)
class Position {
    int row;
    int column;

    Position() {}

    Position(Position other) {
        this << other;
    }

    void leftShift(Position right) {
        assert right != null;
        row = right.row
        column = right.column;
    }

    void rightShift(Position left) {
        assert left != null
        left << this;
    }

    Position plus(Position p) {
        return new Position(row + p.row, column + p.column)
    }

    Position minus(Position p) {
        return new Position(row - p.row, column - p.column)
    }

    Position multiply(Position p) {
        return new Position(row * p.row, column * p.column)
    }

    Position div(Position p) {
        return new Position(row.intdiv(p.row), column.intdiv(p.column))
    }
}
