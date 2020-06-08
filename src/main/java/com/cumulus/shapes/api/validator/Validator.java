package com.cumulus.shapes.api.validator;

import com.cumulus.shapes.api.Shape;

/**
 * @author <Wtoll> Will Toll on 2020-06-07
 * @project Shapes
 */
public interface Validator {
    boolean validate(Shape shape);
}
