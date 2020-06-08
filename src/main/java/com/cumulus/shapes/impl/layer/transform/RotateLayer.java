package com.cumulus.shapes.impl.layer.transform;

import com.cumulus.shapes.api.Position;
import com.cumulus.shapes.api.Shape;
import com.cumulus.shapes.api.layer.Layer;
import com.cumulus.shapes.api.Quaternion;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author <Wtoll> Will Toll on 2020-06-07
 * @project Shapes
 */
public class RotateLayer implements Layer {

    private final Quaternion rotation;

    public RotateLayer(Quaternion rotation) {
        this.rotation = rotation;
    }

    public static RotateLayer of(Quaternion rotation) {
        return new RotateLayer(rotation);
    }

    @Override
    public Position modifyMax(Shape shape) {
        List<Position> translatedVertices = Position.vertices(shape.max(), shape.min()).stream().map((pos) -> pos.rotateBy(rotation)).collect(Collectors.toList());
        return Position.of(
                translatedVertices.stream().map(Position::getX).max(Double::compareTo).get(),
                translatedVertices.stream().map(Position::getY).max(Double::compareTo).get(),
                translatedVertices.stream().map(Position::getZ).max(Double::compareTo).get()
        );
    }

    @Override
    public Position modifyMin(Shape shape) {
        List<Position> translatedVertices = Position.vertices(shape.max(), shape.min()).stream().map((pos) -> pos.rotateBy(rotation)).collect(Collectors.toList());
        return Position.of(
                translatedVertices.stream().map(Position::getX).min(Double::compareTo).get(),
                translatedVertices.stream().map(Position::getY).min(Double::compareTo).get(),
                translatedVertices.stream().map(Position::getZ).min(Double::compareTo).get()
        );
    }

    @Override
    public Predicate<Position> modifyEquation(Shape shape) {
        return (pos) -> shape.equation().test(pos.rotateBy(rotation.conjugate()));
    }
}
