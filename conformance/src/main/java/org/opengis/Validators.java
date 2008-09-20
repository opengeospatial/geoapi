/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis;

import org.opengis.util.*;
import org.opengis.geometry.*;
import org.opengis.referencing.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.parameter.*;


/**
 * A set of convenience static methods for validating GeoAPI implementations. Every
 * {@code validate} method defined in this class delegate their work to one of many
 * {@code Validator} objects in various packages. Vendors can change the value of
 * {@code instance} fields in those {@code Validator} classes if they wish to override
 * some validation process on a system-wide basis.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class Validators {
    /**
     * For subclass constructors only.
     */
    protected Validators() {
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see GeometryValidator#validate(Envelope)
     */
    public static void validate(final Envelope object) {
        GeometryValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see GeometryValidator#validate(DirectPosition)
     */
    public static void validate(final DirectPosition object) {
        GeometryValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#dispatch(CoordinateReferenceSystem)
     */
    public static void validate(final CoordinateReferenceSystem object) {
        CRSValidator.instance.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#validate(GeocentricCRS)
     */
    public static void validate(final GeocentricCRS object) {
        CRSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#validate(GeographicCRS)
     */
    public static void validate(final GeographicCRS object) {
        CRSValidator.instance.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(ProjectedCRS)
     */
    public static void validate(final ProjectedCRS object) {
        CRSValidator.instance.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(DerivedCRS)
     */
    public static void validate(final DerivedCRS object) {
        CRSValidator.instance.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(ImageCRS)
     */
    public static void validate(final ImageCRS object) {
        CRSValidator.instance.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(EngineeringCRS)
     */
    public static void validate(final EngineeringCRS object) {
        CRSValidator.instance.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(VerticalCRS)
     */
    public static void validate(final VerticalCRS object) {
        CRSValidator.instance.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(TemporalCRS)
     */
    public static void validate(final TemporalCRS object) {
        CRSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#dispatch(CoordinateSystem)
     */
    public static void validate(final CoordinateSystem object) {
        CSValidator.instance.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CartesianCS)
     */
    public static void validate(final CartesianCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(EllipsoidalCS)
     */
    public static void validate(final EllipsoidalCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(SphericalCS)
     */
    public static void validate(final SphericalCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CylindricalCS)
     */
    public static void validate(final CylindricalCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(PolarCS)
     */
    public static void validate(final PolarCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(LinearCS)
     */
    public static void validate(final LinearCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(VerticalCS)
     */
    public static void validate(final VerticalCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(TimeCS)
     */
    public static void validate(final TimeCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(UserDefinedCS)
     */
    public static void validate(final UserDefinedCS object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CoordinateSystemAxis)
     */
    public static void validate(final CoordinateSystemAxis object) {
        CSValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#dispatch(Datum)
     */
    public static void validate(final Datum object) {
        DatumValidator.instance.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(PrimeMeridian)
     */
    public static void validate(final PrimeMeridian object) {
        DatumValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(Ellipsoid)
     */
    public static void validate(final Ellipsoid object) {
        DatumValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(GeodeticDatum)
     */
    public static void validate(final GeodeticDatum object) {
        DatumValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(VerticalDatum)
     */
    public static void validate(final VerticalDatum object) {
        DatumValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(TemporalDatum)
     */
    public static void validate(final TemporalDatum object) {
        DatumValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(ImageDatum)
     */
    public static void validate(final ImageDatum object) {
        DatumValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(EngineeringDatum)
     */
    public static void validate(final EngineeringDatum object) {
        DatumValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#dispatch(GeneralParameterDescriptor)
     */
    public static void validate(final GeneralParameterDescriptor object) {
        ParameterValidator.instance.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterDescriptor)
     */
    public static void validate(final ParameterDescriptor<?> object) {
        ParameterValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterDescriptorGroup)
     */
    public static void validate(final ParameterDescriptorGroup object) {
        ParameterValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#dispatch(GeneralParameterValue)
     */
    public static void validate(final GeneralParameterValue object) {
        ParameterValidator.instance.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterValue)
     */
    public static void validate(final ParameterValue<?> object) {
        ParameterValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterValueGroup)
     */
    public static void validate(final ParameterValueGroup object) {
        ParameterValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ReferencingValidator#dispatch(IdentifiedObject)
     */
    public static void validate(final IdentifiedObject object) {
        ReferencingValidator.instance.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ReferencingValidator#validate(ReferenceIdentifier)
     */
    public static void validate(final ReferenceIdentifier object) {
        ReferencingValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(GenericName)
     */
    public static void validate(final GenericName object) {
        NameValidator.instance.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(LocalName)
     */
    public static void validate(final LocalName object) {
        NameValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(ScopedName)
     */
    public static void validate(final ScopedName object) {
        NameValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(NameSpace)
     */
    public static void validate(final NameSpace object) {
        NameValidator.instance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(InternationalString)
     */
    public static void validate(final InternationalString object) {
        NameValidator.instance.validate(object);
    }
}
