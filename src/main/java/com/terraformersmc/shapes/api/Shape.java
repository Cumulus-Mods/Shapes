package com.terraformersmc.shapes.api;

import com.terraformersmc.shapes.api.layer.Layer;
import com.terraformersmc.shapes.api.validator.Validator;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author <Wtoll> Will Toll on 2020-06-07
 * @project Shapes
 */
public interface Shape {

    static Shape of(Predicate<Position> equation, Position max, Position min) {
        return new Shape() {
            @Override
            public Position max() {
                return max;
            }

            @Override
            public Position min() {
                return min;
            }

            @Override
            public Predicate<Position> equation() {
                return equation;
            }
        };
    }

    Position max();
    Position min();
    Predicate<Position> equation();

    default Stream<Position> stream() {
        return Position.stream(this.max(), this.min()).filter((pos) -> this.equation().test(pos.copy()));
    }

    default Shape applyLayer(Layer layer) {
        return Shape.of(layer.modifyEquation(this), layer.modifyMax(this), layer.modifyMin(this));
    }

    default Shape validate(Validator validator, Consumer<Shape> consumer) {
        if (validator.validate(this)) consumer.accept(this);
        return this;
    }

    default void fill(Filler filler) {
        this.stream().forEach(filler);
    }
}
