/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.test.referencing;

import java.util.List;

import org.opengis.referencing.operation.*;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import org.opengis.test.ValidatorContainer;
import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.Assertions.assertBetween;
import static org.opengis.test.Assertions.assertStrictlyPositive;


/**
 * Validates {@link CoordinateOperation} and related objects from the
 * {@code org.opengis.referencing.operation} package.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class OperationValidator extends ReferencingValidator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public OperationValidator(final ValidatorContainer container) {
        super(container, "org.opengis.referencing.operation");
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     * @return number of {@code validate(…)} methods invoked in this class for the given object.
     */
    public int dispatch(final CoordinateOperation object) {
        int n = 0;
        if (object != null) {
            if (object instanceof Conversion)            {validate((Conversion)            object); n++;}
            if (object instanceof Transformation)        {validate((Transformation)        object); n++;}
            if (object instanceof PointMotionOperation)  {validate((PointMotionOperation)  object); n++;}
            if (object instanceof ConcatenatedOperation) {validate((ConcatenatedOperation) object); n++;}
            if (object instanceof PassThroughOperation)  {validate((PassThroughOperation)  object); n++;}
            if (n == 0) {
                if (object instanceof SingleOperation) {
                    validateOperation((SingleOperation) object);
                } else {
                    validateCoordinateOperation(object);
                }
            }
        }
        return n;
    }

    /**
     * Validates the given "pass through" operation.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final PassThroughOperation object) {
        if (object == null) {
            return;
        }
        validateCoordinateOperation(object);
        final MathTransform transform = object.getMathTransform();
        mandatory(transform, "PassThroughOperation: shall have a MathTransform.");

        final CoordinateOperation operation = object.getOperation();
        mandatory(operation, "PassThroughOperation: getOperation() is mandatory.");
        assertNotSame(object, operation, "PassThroughOperation: getOperation() cannot be this.");
        dispatch(operation);

        final int[] index = object.getModifiedCoordinates();
        mandatory(index, "PassThroughOperation: modified coordinates are mandatory.");
        if (operation == null || index == null) {
            return;
        }
        final int sourceDimension = transform.getSourceDimensions();
        for (int i : index) {
            assertBetween(0, sourceDimension-1, i, "PassThroughOperation: invalid modified coordinate index.");
        }
    }

    /**
     * Validates the given concatenated operation.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final ConcatenatedOperation object) {
        if (object == null) {
            return;
        }
        validateCoordinateOperation(object);
        final MathTransform transform = object.getMathTransform();
        mandatory(transform, "ConcatenatedOperation: shall have a MathTransform.");

        final List<? extends CoordinateOperation> operations = object.getOperations();
        mandatory(operations, "ConcatenatedOperation: shall provide a list of operations.");
        if (operations == null) {
            return;
        }
        validate(operations);
        CoordinateOperation first=null, last=null;
        for (final CoordinateOperation single : operations) {
            assertNotNull(single, "ConcatenatedOperation: getOperations() cannot contain null element.");
            assertNotSame(single, object, "ConcatenatedOperation: cannot contain itself as a single element.");
            dispatch(single);
            if (first == null) {
                first = single;
            } else {
                // Do not validate MathTransform since it is already done by dispatch(single).
                final MathTransform lastMT =   last.getMathTransform();
                final MathTransform thisMT = single.getMathTransform();
                if (lastMT != null && thisMT != null) {
                    assertEquals(lastMT.getTargetDimensions(), thisMT.getSourceDimensions(),
                            "ConcatenatedOperation: source dimension of a single operation " +
                            "must match the target dimension of the previous one.");
                }
                // Do not validate CRS since it is already done by dispatch(single).
                final CoordinateReferenceSystem targetCRS =   last.getTargetCRS();
                final CoordinateReferenceSystem sourceCRS = single.getSourceCRS();
                if (targetCRS != null && sourceCRS != null) {
                    assertEquals(dimension(targetCRS), dimension(sourceCRS),
                            "ConcatenatedOperation: source dimension of a single operation " +
                            "must match the target dimension of the previous one.");
                }
            }
            last = single;
        }
        assertNotNull(last, "ConcatenatedOperation: shall contain at least one single operation.");
        if (transform != null) {
            final MathTransform firstMT = first.getMathTransform();
            final MathTransform lastMT  = last .getMathTransform();
            if (firstMT != null) {
                assertEquals(firstMT.getSourceDimensions(), transform.getSourceDimensions(),
                        "ConcatenatedOperation: source dimension must match " +
                        "the source dimension of the first single operation.");
            }
            if (lastMT != null) {
                assertEquals(lastMT.getTargetDimensions(), transform.getTargetDimensions(),
                        "ConcatenatedOperation: target dimension must match " +
                        "the target dimension of the last single operation.");
            }
        }
        final CoordinateReferenceSystem sourceCRS = object.getSourceCRS();
        final CoordinateReferenceSystem targetCRS = object.getTargetCRS();
        final CoordinateReferenceSystem  firstCRS = first .getSourceCRS();
        final CoordinateReferenceSystem   lastCRS = last  .getTargetCRS();
        if (sourceCRS != null && firstCRS != null) {
            assertSame(firstCRS, sourceCRS,
                    "ConcatenatedOperation: sourceCRS must be the source of the first single operation.");
        }
        if (targetCRS != null && lastCRS != null) {
            assertSame(lastCRS, targetCRS,
                    "ConcatenatedOperation: targetCRS must be the target of the last single operation.");
        }
    }

    /**
     * Validates the given coordinate operation. This method is private because we
     * choose to expose only non-ambiguous {@code validate} methods in public API.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    @SuppressWarnings("deprecation")
    private void validateCoordinateOperation(final CoordinateOperation object) {
        if (object == null) {
            return;
        }
        assertFalse((object instanceof ConcatenatedOperation) && (object instanceof SingleOperation),
                "CoordinateOperation: cannot be both a ConcatenatedOperation and a SingleOperation.");
        validateIdentifiedObject(object);
        container.validate(object.getScope());
        container.validate(object.getDomainOfValidity());
        validate("domain", object.getDomains(), ValidatorContainer::validate, false);

        final CoordinateReferenceSystem sourceCRS = object.getSourceCRS();
        final CoordinateReferenceSystem targetCRS = object.getTargetCRS();
        container.validate(sourceCRS);
        container.validate(targetCRS);

        // Note: MathTransform can be null in defining conversion. We will
        // check for non-null value in more specific validation methods only.
        final MathTransform transform = object.getMathTransform();
        validate(transform);
        if (transform != null) {
            if (sourceCRS != null) {
                assertEquals(dimension(sourceCRS), transform.getSourceDimensions(),
                        "CoordinateOperation: MathTransform source dimension must match sourceCRS dimension.");
            }
            if (targetCRS != null) {
                assertEquals(dimension(targetCRS), transform.getTargetDimensions(),
                        "CoordinateOperation: MathTransform target dimension must match targetCRS dimension.");
            }
        }
    }

    /**
     * Validates the given operation. This method is private because we choose
     * to expose only non-ambiguous {@code validate} methods in public API.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    @SuppressWarnings("UnnecessaryUnboxing")
    private void validateOperation(final SingleOperation object) {
        if (object == null) {
            return;
        }
        validateCoordinateOperation(object);
        assertFalse((object instanceof Conversion) && (object instanceof Transformation),
                "Operation: cannot be both a Conversion and a Transformation.");

        final OperationMethod method = object.getMethod();
        mandatory(method, "Operation: OperationMethod is mandatory.");
        validate(method);
        if (method != null) {
            @SuppressWarnings("deprecation") final Integer opSourceDimension = method.getSourceDimensions();
            @SuppressWarnings("deprecation") final Integer opTargetDimension = method.getTargetDimensions();
            final MathTransform transform = object.getMathTransform();
            // Do not validate because it is already done by validateCoordinateOperation(object).
            if (transform != null) {
                if (opSourceDimension != null) {
                    assertEquals(opSourceDimension.intValue(), transform.getSourceDimensions(),
                            "Operation: MathTransform source dimension must match OperationMethod source dimension.");
                }
                if (opTargetDimension != null) {
                    assertEquals(opTargetDimension.intValue(), transform.getTargetDimensions(),
                            "Operation: MathTransform target dimension must match OperationMethod target dimension.");
                }
            }
        }
        final ParameterValueGroup parameters = object.getParameterValues();
        mandatory(method, "Operation: ParameterValues are mandatory.");
        container.validate(parameters);
    }

    /**
     * Validates the given conversion.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final Conversion object) {
        if (object == null) {
            return;
        }
        validateOperation(object);
        if (object.getMathTransform() == null) {
            forbidden(object.getSourceCRS(), "Conversion: defining conversion should not have a source CRS");
            forbidden(object.getTargetCRS(), "Conversion: defining conversion should not have a target CRS");
        } else {
            mandatory(object.getSourceCRS(), "Conversion: non-defining conversion should have a source CRS.");
            mandatory(object.getTargetCRS(), "Conversion: non-defining conversion should have a target CRS.");
        }
        forbidden(object.getSourceEpoch(),      "Conversion: should not have a source epoch.");
        forbidden(object.getTargetEpoch(),      "Conversion: should not have a target epoch.");
        forbidden(object.getOperationVersion(), "Conversion: should not have operation version.");
    }

    /**
     * Validates the given transformation.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final Transformation object) {
        if (object == null) {
            return;
        }
        validateOperation(object);
        mandatory(object.getSourceCRS(),        "Transformation: sourceCRS is a mandatory attribute.");
        mandatory(object.getTargetCRS(),        "Transformation: targetCRS is a mandatory attribute.");
        mandatory(object.getMathTransform(),    "Transformation: MathTransform is a mandatory attribute.");
        mandatory(object.getOperationVersion(), "Transformation: operationVersion is a mandatory attribute.");
        forbidden(object.getSourceEpoch(),      "Transformation: should not have a source epoch.");
        forbidden(object.getTargetEpoch(),      "Transformation: should not have a target epoch.");
    }

    /**
     * Validates the point motion operation.
     *
     * @param  object  the object to validate, or {@code null}.
     * @since 3.1
     */
    public void validate(final PointMotionOperation object) {
        if (object == null) {
            return;
        }
        validateOperation(object);
        CoordinateReferenceSystem sourceCRS, targetCRS;
        mandatory(sourceCRS = object.getSourceCRS(), "PointMotionOperation: sourceCRS is a mandatory attribute.");
        mandatory(targetCRS = object.getTargetCRS(), "PointMotionOperation: targetCRS is a mandatory attribute.");
        if (sourceCRS != null && targetCRS != null) {
            assertEquals(sourceCRS, targetCRS, "PointMotionOperation: sourceCRS and targetCRS shall be equal.");
        }
        mandatory(object.getSourceEpoch(),      "PointMotionOperation: source epoch is a mandatory attribute.");
        mandatory(object.getTargetEpoch(),      "PointMotionOperation: target epoch is a mandatory attribute.");
        mandatory(object.getOperationVersion(), "PointMotionOperation: operationVersion is a mandatory attribute.");
        mandatory(object.getMathTransform(),    "PointMotionOperation: MathTransform is a mandatory attribute.");
    }

    /**
     * Validates the given operation method.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final OperationMethod object) {
        if (object == null) {
            return;
        }
        @SuppressWarnings("deprecation") final Integer sourceDimension = object.getSourceDimensions();
        @SuppressWarnings("deprecation") final Integer targetDimension = object.getTargetDimensions();
        if (sourceDimension != null) {
            assertStrictlyPositive(sourceDimension, "OperationMethod: source dimension must be greater than zero.");
        }
        if (targetDimension != null) {
            assertStrictlyPositive(targetDimension, "OperationMethod: target dimension must be greater than zero.");
        }
        validate(object.getFormula());
        container.validate(object.getParameters());
        validateIdentifiedObject(object);
    }

    /**
     * Validates the given formula.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final Formula object) {
        if (object == null) {
            return;
        }
        container.validate(object.getFormula());
        container.validate(object.getCitation());
    }

    /**
     * Validates the given math transform.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final MathTransform object) {
        if (object == null) {
            return;
        }
        final int sourceDimension = object.getSourceDimensions();
        final int targetDimension = object.getTargetDimensions();
        assertStrictlyPositive(sourceDimension, "MathTransform: source dimension must be greater than zero.");
        assertStrictlyPositive(targetDimension, "MathTransform: target dimension must be greater than zero.");
        if (object instanceof MathTransform1D) {
            assertEquals(1, sourceDimension, "MathTransform1D: source dimension must be 1.");
            assertEquals(1, targetDimension, "MathTransform1D: target dimension must be 1.");
        }
        if (object instanceof MathTransform2D) {
            assertEquals(2, sourceDimension, "MathTransform2D: source dimension must be 2.");
            assertEquals(2, targetDimension, "MathTransform2D: target dimension must be 2.");
        }
        if (object.isIdentity()) {
            assertEquals(sourceDimension, targetDimension,
                    "MathTransform: identity transforms must have the same source and target dimensions.");
        }
    }

    /**
     * {@return the number of dimensions of the given CRS}.
     *
     * @param  crs  the coordinate reference system for which to get the number of dimensions.
     */
    private static int dimension(final CoordinateReferenceSystem crs) {
        return crs.getCoordinateSystem().getDimension();
    }
}
