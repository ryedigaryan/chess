package rub.learn.groovy.chess.common

import groovy.transform.ToString
import groovy.transform.TupleConstructor

@TupleConstructor(defaults = false, force = true)
@ToString(includePackage = false)
class Size {
    int width;
    int height;

    Size() {}

    Size(Size other) {
        this << other;
    }

    void leftShift(Size right) {
        assert right != null;
        width = right.width
        height = right.height;
    }

    void rightShift(Size left) {
        left << this;
    }

    Size plus(int v) {
        return new Size(width: (width + v), height: (height + v))
    }
}

