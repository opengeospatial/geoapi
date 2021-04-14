/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test.referencing;

import java.util.List;

import org.opengis.referencing.operation.*;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


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
        mandatory("PassThroughOperation: shall have a MathTransform.", transform);

        final CoordinateOperation operation = object.getOperation();
        mandatory("PassThroughOperation: getOperation() is mandatory.", operation);
        assertNotSame("PassThroughOperation: getOperation() can't be this.", object, operation);
        dispatch(operation);

        final int[] index = object.getModifiedCoordinates();
        mandatory("PassThroughOperation: modified coordinates are mandatory.", index);
        if (operation == null || index == null) {
            return;
        }
        final int sourceDimension = transform.getSourceDimensions();
        for (int i : index) {
            assertBetween("PassThroughOperation: invalid modified coordinate index.", 0, sourceDimension-1, i);
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
        mandatory("ConcatenatedOperation: shall have a MathTransform.", transform);

        final List<? extends CoordinateOperation> operations = object.getOperations();
        mandatory("ConcatenatedOperation: shall provide a list of operations.", operations);
        if (operations == null) {
            return;
        }
        validate(operations);
        CoordinateOperation first=null, last=null;
        for (final CoordinateOperation single : operations) {
            assertNotNull("ConcatenatedOperation: getOperations() can't contain null element.", single);
            assertNotSame("ConcatenatedOperation: can't contain itself as a single element.", single, object);
            dispatch(single);
            if (first == null) {
                first = single;
            } else {
                // Do not validate MathTransform since it is already done by dispatch(single).
                final MathTransform lastMT =   last.getMathTransform();
                final MathTransform thisMT = single.getMathTransform();
                if (lastMT != null && thisMT != null) {
                    assertEquals("ConcatenatedOperation: source dimension of a single operation " +
                            "must match the target dimension of the previous one.",
                            lastMT.getTargetDimensions(), thisMT.getSourceDimensions());
                }
                // Do not validate CRS since it is already done by dispatch(single).
                final CoordinateReferenceSystem targetCRS =   last.getTargetCRS();
                final CoordinateReferenceSystem sourceCRS = single.getSourceCRS();
                if (targetCRS != null && sourceCRS != null) {
                    assertEquals("ConcatenatedOperation: source dimension of a single operation " +
                            "must match the target dimension of the previous one.",
                            dimension(targetCRS), dimension(sourceCRS));
                }
            }
            last = single;
        }
        assertNotNull("ConcatenatedOperation: shall contain at least one single operation.", last);
        if (transform != null) {
            final MathTransform firstMT = first.getMathTransform();
            final MathTransform lastMT  = last .getMathTransform();
            if (firstMT != null) {
                assertEquals("ConcatenatedOperation: source dimension must match " +
                        "the source dimension of the first single operation.",
                        firstMT.getSourceDimensions(), transform.getSourceDimensions());
            }
            if (lastMT != null) {
                assertEquals("ConcatenatedOperation: target dimension must match " +
                        "the target dimension of the last single operation.",
                        lastMT.getTargetDimensions(), transform.getTargetDimensions());
            }
        }
        final CoordinateReferenceSystem sourceCRS = object.getSourceCRS();
        final CoordinateReferenceSystem targetCRS = object.getTargetCRS();
        final CoordinateReferenceSystem  firstCRS = first .getSourceCRS();
        final CoordinateReferenceSystem   lastCRS = last  .getTargetCRS();
        if (sourceCRS != null && firstCRS != null) {
            assertSame("ConcatenatedOperation: sourceCRS must be the source " +
                    "of the first single operation.", firstCRS, sourceCRS);
        }
        if (targetCRS != null && lastCRS != null) {
            assertSame("ConcatenatedOperation: targetCRS must be the target " +
                    "of the last single operation.", lastCRS, targetCRS);
        }
    }

    /**
     * Validates the given coordinate operation. This method is private because we
     * choose to expose only non-ambiguous {@code validate} methods in public API.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    private void validateCoordinateOperation(final CoordinateOperation object) {
        if (object == null) {
            return;
        }
        assertFalse("CoordinateOperation: can't be both a ConcatenatedOperation and a SingleOperation.",
                (object instanceof ConcatenatedOperation) && (object instanceof SingleOperation));
        validateIdentifiedObject(object);
        container.validate(object.getScope());
        container.validate(object.getDomainOfValidity());

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
                assertEquals("CoordinateOperation: MathTransform source dimension must match sourceCRS dimension.",
                        dimension(sourceCRS), transform.getSourceDimensions());
            }
            if (targetCRS != null) {
                assertEquals("CoordinateOperation: MathTransform target dimension must match targetCRS dimension.",
                        dimension(targetCRS), transform.getTargetDimensions());
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
        assertFalse("Operation: can't be both a Conversion and a Transformation.",
                (object instanceof Conversion) && (object instanceof Transformation));

        final OperationMethod method = object.getMethod();
        mandatory("Operation: OperationMethod is mandatory.", method);
        if (method != null) {
            validate(method);
            final Integer opSourceDimension = method.getSourceDimensions();
            final Integer opTargetDimension = method.getTargetDimensions();
            final MathTransform transform = object.getMathTransform();
            // Do not validate because it is already done by validateCoordinateOperation(object).
            if (transform != null) {
                if (opSourceDimension != null) {
                    assertEquals("Operation: MathTransform source dimension must match OperationMethod source dimension.",
                            opSourceDimension.intValue(), transform.getSourceDimensions());
                }
                if (opTargetDimension != null) {
                    assertEquals("Operation: MathTransform target dimension must match OperationMethod target dimension.",
                            opTargetDimension.intValue(), transform.getTargetDimensions());
                }
            }
        }
        final ParameterValueGroup parameters = object.getParameterValues();
        mandatory("Operation: ParameterValues are mandatory.", method);
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
        assertFalse("Projection: can't be both planar and conic.",
                (object instanceof PlanarProjection) && (object instanceof ConicProjection));
        assertFalse("Projection: can't be both planar and cylindrical.",
                (object instanceof PlanarProjection) && (object instanceof CylindricalProjection));
        assertFalse("Projection: can't be both cylindrical and conic.",
                (object instanceof CylindricalProjection) && (object instanceof ConicProjection));

        if (object.getMathTransform() != null) {
            mandatory("Conversion: non-defining conversion should have a source CRS.", object.getSourceCRS());
            mandatory("Conversion: non-defining conversion should have a target CRS.", object.getTargetCRS());
        }
        forbidden("Conversion: should not have operation version.", object.getOperationVersion());
        if (object.getMathTransform() == null) {
            forbidden("Conversion: defining conversion should not have source CRS", object.getSourceCRS());
            forbidden("Conversion: defining conversion should not have target CRS", object.getTargetCRS());
        }
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
        mandatory("Transformation: operationVersion is a mandatory attribute.", object.getOperationVersion());
        mandatory("Transformation: sourceCRS is a mandatory attribute.",        object.getSourceCRS());
        mandatory("Transformation: targetCRS is a mandatory attribute.",        object.getTargetCRS());
        mandatory("Transformation: MathTransform is a mandatory attribute.",    object.getMathTransform());
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
        final Integer sourceDimension = object.getSourceDimensions();
        final Integer targetDimension = object.getTargetDimensions();
        if (sourceDimension != null) {
            assertStrictlyPositive("OperationMethod: source dimension must be greater than zero.", sourceDimension);
        }
        if (targetDimension != null) {
            assertStrictlyPositive("OperationMethod: target dimension must be greater than zero.", targetDimension);
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
        assertStrictlyPositive("MathTransform: source dimension must be greater than zero.", sourceDimension);
        assertStrictlyPositive("MathTransform: target dimension must be greater than zero.", targetDimension);
        if (object instanceof MathTransform1D) {
            assertEquals("MathTransform1D: source dimension must be 1.", 1, sourceDimension);
            assertEquals("MathTransform1D: target dimension must be 1.", 1, targetDimension);
        }
        if (object instanceof MathTransform2D) {
            assertEquals("MathTransform2D: source dimension must be 2.", 2, sourceDimension);
            assertEquals("MathTransform2D: target dimension must be 2.", 2, targetDimension);
        }
        if (object.isIdentity()) {
            assertEquals("MathTransform: identity transforms must have the same source and target dimensions.",
                    sourceDimension, targetDimension);
        }
    }

    /**
     * Returns the dimension of the given CRS.
     */
    private static int dimension(final CoordinateReferenceSystem crs) {
        return crs.getCoordinateSystem().getDimension();
    }
}
