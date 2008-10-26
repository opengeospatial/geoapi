/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test;

import org.opengis.util.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.citation.*;
import org.opengis.geometry.*;
import org.opengis.parameter.*;
import org.opengis.referencing.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;


/**
 * A set of convenience static methods for validating GeoAPI implementations. Every
 * {@code validate} method defined in this class delegate their work to one of many
 * {@link Validator} objects in various packages. This class is especially convenient
 * when used with the {@code static import} feature of Java 5.
 *
 * <p>To override some validation process on a system-wide basis, vendors can change the
 * {@link #DEFAULT} static field or change the configuration of the object referenced
 * by that field.</p>
 *
 * <p>To override some validation process without changing the system-wide setting,
 * users can create a new instance of {@link ValidatorContainer} and use that instance
 * instead of this class.</p>
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class Validators {
    /**
     * The default container to be used by all static {@code validate} methods.
     * Vendors can change this field to a different container, or change the setting
     * of the referenced container. This field shall not be set to {@code null} however.
     */
    public static ValidatorContainer DEFAULT = new ValidatorContainer();

    /**
     * For subclass constructors only.
     */
    protected Validators() {
    }

    /**
     * Dispatches the given object to one of the {@code validate(object)} methods.
     * Use this method only if the type is unknow at compile-time.
     *
     * @param object The object to test, or {@code null}.
     */
    public final void dispatch(final Object object) {
        DEFAULT.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(Extent)
     */
    public static void validate(final Extent object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(TemporalExtent)
     */
    public static void validate(final TemporalExtent object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(VerticalExtent)
     */
    public static void validate(final VerticalExtent object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(GeographicExtent)
     */
    public static void validate(final GeographicExtent object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(GeographicDescription)
     */
    public static void validate(final GeographicDescription object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(BoundingPolygon)
     */
    public static void validate(final BoundingPolygon object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(GeographicBoundingBox)
     */
    public static void validate(final GeographicBoundingBox object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see GeometryValidator#validate(Envelope)
     */
    public static void validate(final Envelope object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see GeometryValidator#validate(DirectPosition)
     */
    public static void validate(final DirectPosition object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#validate(CoordinateReferenceSystem)
     */
    public static void validate(final CoordinateReferenceSystem object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#validate(GeocentricCRS)
     */
    public static void validate(final GeocentricCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#validate(GeographicCRS)
     */
    public static void validate(final GeographicCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(ProjectedCRS)
     */
    public static void validate(final ProjectedCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(DerivedCRS)
     */
    public static void validate(final DerivedCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(ImageCRS)
     */
    public static void validate(final ImageCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(EngineeringCRS)
     */
    public static void validate(final EngineeringCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(VerticalCRS)
     */
    public static void validate(final VerticalCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(TemporalCRS)
     */
    public static void validate(final TemporalCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CoordinateSystem)
     */
    public static void validate(final CoordinateSystem object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CartesianCS)
     */
    public static void validate(final CartesianCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(EllipsoidalCS)
     */
    public static void validate(final EllipsoidalCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(SphericalCS)
     */
    public static void validate(final SphericalCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CylindricalCS)
     */
    public static void validate(final CylindricalCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(PolarCS)
     */
    public static void validate(final PolarCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(LinearCS)
     */
    public static void validate(final LinearCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(VerticalCS)
     */
    public static void validate(final VerticalCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(TimeCS)
     */
    public static void validate(final TimeCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(UserDefinedCS)
     */
    public static void validate(final UserDefinedCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CoordinateSystemAxis)
     */
    public static void validate(final CoordinateSystemAxis object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(Datum)
     */
    public static void validate(final Datum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(PrimeMeridian)
     */
    public static void validate(final PrimeMeridian object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(Ellipsoid)
     */
    public static void validate(final Ellipsoid object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(GeodeticDatum)
     */
    public static void validate(final GeodeticDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(VerticalDatum)
     */
    public static void validate(final VerticalDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(TemporalDatum)
     */
    public static void validate(final TemporalDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(ImageDatum)
     */
    public static void validate(final ImageDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(EngineeringDatum)
     */
    public static void validate(final EngineeringDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(CoordinateOperation)
     */
    public static void validate(final CoordinateOperation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(Conversion)
     */
    public static void validate(final Conversion object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(Transformation)
     */
    public static void validate(final Transformation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(ConcatenatedOperation)
     */
    public static void validate(final ConcatenatedOperation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(PassThroughOperation)
     */
    public static void validate(final PassThroughOperation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(OperationMethod)
     */
    public static void validate(final OperationMethod object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(MathTransform)
     */
    public static void validate(final MathTransform object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(GeneralParameterDescriptor)
     */
    public static void validate(final GeneralParameterDescriptor object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterDescriptor)
     */
    public static void validate(final ParameterDescriptor<?> object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterDescriptorGroup)
     */
    public static void validate(final ParameterDescriptorGroup object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(GeneralParameterValue)
     */
    public static void validate(final GeneralParameterValue object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterValue)
     */
    public static void validate(final ParameterValue<?> object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterValueGroup)
     */
    public static void validate(final ParameterValueGroup object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ReferencingValidator#validate(IdentifiedObject)
     */
    public static void validate(final IdentifiedObject object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ReferencingValidator#validate(ReferenceIdentifier)
     */
    public static void validate(final ReferenceIdentifier object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CitationValidator#validate(Citation)
     */
    public static void validate(final Citation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(GenericName)
     */
    public static void validate(final GenericName object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(LocalName)
     */
    public static void validate(final LocalName object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(ScopedName)
     */
    public static void validate(final ScopedName object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(NameSpace)
     */
    public static void validate(final NameSpace object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(InternationalString)
     */
    public static void validate(final InternationalString object) {
        DEFAULT.validate(object);
    }
}
