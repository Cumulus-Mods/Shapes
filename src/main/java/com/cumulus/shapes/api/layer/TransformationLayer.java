package com.cumulus.shapes.api.layer;

import com.cumulus.shapes.api.Position;
import com.cumulus.shapes.api.Shape;

import java.util.function.Predicate;

/**
 * @author <Wtoll> Will Toll on 2020-06-07
 * @project Shapes
 */
public abstract class TransformationLayer implements Layer {
    protected abstract Position transform(Position pos);

    public abstract Position inverseTransform(Position pos);

    @Override
    public Position modifyMax(Shape shape) {
        return transform(shape.max());
    }

    @Override
    public Position modifyMin(Shape shape) {
        return transform(shape.min());
    }

    @Override
    public Predicate<Position> modifyEquation(Shape shape) {
        return (pos) -> shape.equation().test(this.inverseTransform(pos));
    }
}
