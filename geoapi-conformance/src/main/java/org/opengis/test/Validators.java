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
package org.opengis.test;

import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.metadata.IIOMetadataFormat;

import org.opengis.util.*;
import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.citation.*;
import org.opengis.geometry.*;
import org.opengis.metadata.quality.*;
import org.opengis.metadata.maintenance.*;
import org.opengis.metadata.maintenance.Scope;      // Resolve ambiguity.
import org.opengis.parameter.*;
import org.opengis.referencing.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;

// Following imports are for javadoc
import org.opengis.test.util.*;
import org.opengis.test.geometry.*;
import org.opengis.test.metadata.*;
import org.opengis.test.referencing.*;
import org.opengis.test.coverage.image.*;


/**
 * A set of convenience static methods for validating GeoAPI implementations. Every
 * {@code validate} method defined in this class delegate their work to one of many
 * {@link Validator} objects in various packages. This class is especially convenient
 * when used with {@code static import} statements.
 *
 * <h2>Customization</h2>
 * To override some validation process on a <em>system-wide</em> basis, vendors can either
 * assign a new {@link ValidatorContainer} instance to the {@link #DEFAULT} static field, or
 * modify the fields ({@link ValidatorContainer#cs cs}, {@link ValidatorContainer#crs crs},
 * <i>etc.</i>) in the existing instance. The following example alters the existing instance
 * in order to accept non-standard axis names:
 *
 * {@snippet lang="java" :
 * Validators.DEFAULT.crs.enforceStandardNames = false;
 * }
 *
 * <p>To override some validation process without changing the system-wide setting,
 * vendors can create a new instance of {@link ValidatorContainer} and invoke its
 * non-static methods from the vendor's test cases.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class Validators {
    /**
     * The default container to be used by all static {@code validate} methods.
     * Vendors can change the validators referenced by this container, or change
     * their setting.
     *
     * <p>This field is not final in order to allow vendors to switch easily between
     * different configurations, for example:</p>
     *
     * {@snippet lang="java" :
     * ValidatorContainer original = Validators.DEFAULT;
     * Validators.DEFAULT = myConfig;
     * ... do some tests ...
     * Validators.DEFAULT = original;
     * }
     */
    public static ValidatorContainer DEFAULT = new ValidatorContainer();

    /**
     * For subclass constructors only.
     */
    protected Validators() {
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method (if any). Use this method only if the type is
     * unknown at compile-time.
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     */
    public static void dispatch(final Object object) {
        DEFAULT.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see MetadataBaseValidator#validate(Metadata)
     *
     * @since 3.1
     */
    public static void validate(final Metadata object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CitationValidator#validate(Citation)
     */
    public static void validate(final Citation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given objects.
     *
     * @param  object  the objects to test, or {@code null}.
     *
     * @see CitationValidator#validate(CitationDate...)
     *
     * @since 3.1
     */
    public static void validate(final CitationDate... object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CitationValidator#validate(Responsibility)
     *
     * @since 3.1
     */
    public static void validate(final Responsibility object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CitationValidator#validate(Party)
     *
     * @since 3.1
     */
    public static void validate(final Party object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CitationValidator#validate(Contact)
     *
     * @since 3.1
     */
    public static void validate(final Contact object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CitationValidator#validate(Telephone)
     *
     * @since 3.1
     */
    public static void validate(final Telephone object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CitationValidator#validate(Address)
     *
     * @since 3.1
     */
    public static void validate(final Address object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CitationValidator#validate(OnlineResource)
     *
     * @since 3.1
     */
    public static void validate(final OnlineResource object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see MaintenanceValidator#validate(DataQuality)
     *
     * @since 3.1
     */
    public static void validate(final DataQuality object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see QualityValidator#dispatch(Element)
     *
     * @since 3.1
     */
    public static void validate(final Element object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see MaintenanceValidator#validate(MaintenanceInformation)
     *
     * @since 3.1
     */
    public static void validate(final PositionalAccuracy object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see QualityValidator#dispatch(Result)
     *
     * @since 3.1
     */
    public static void validate(final Result object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see QualityValidator#validate(DescriptiveResult)
     *
     * @since 3.1
     */
    public static void validate(final DescriptiveResult object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see QualityValidator#validate(ConformanceResult)
     *
     * @since 3.1
     */
    public static void validate(final ConformanceResult object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see QualityValidator#validate(QuantitativeResult)
     *
     * @since 3.1
     */
    public static void validate(final QuantitativeResult object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see QualityValidator#validate(CoverageResult)
     *
     * @since 3.1
     */
    public static void validate(final CoverageResult object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see MaintenanceValidator#validate(MaintenanceInformation)
     *
     * @since 3.1
     */
    public static void validate(final MaintenanceInformation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see MaintenanceValidator#validate(Scope)
     *
     * @since 3.1
     */
    public static void validate(final Scope object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(Extent)
     */
    public static void validate(final Extent object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(TemporalExtent)
     */
    public static void validate(final TemporalExtent object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(VerticalExtent)
     */
    public static void validate(final VerticalExtent object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#dispatch(GeographicExtent)
     */
    public static void validate(final GeographicExtent object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(GeographicDescription)
     */
    public static void validate(final GeographicDescription object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(BoundingPolygon)
     */
    public static void validate(final BoundingPolygon object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(GeographicBoundingBox)
     */
    public static void validate(final GeographicBoundingBox object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see GeometryValidator#validate(Envelope)
     */
    public static void validate(final Envelope object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see GeometryValidator#validate(DirectPosition)
     */
    public static void validate(final DirectPosition object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CRSValidator#dispatch(CoordinateReferenceSystem)
     */
    public static void validate(final CoordinateReferenceSystem object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CRSValidator#validate(GeocentricCRS)
     */
    public static void validate(final GeocentricCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CRSValidator#validate(GeographicCRS)
     */
    public static void validate(final GeographicCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(ProjectedCRS)
     */
    public static void validate(final ProjectedCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(DerivedCRS)
     */
    public static void validate(final DerivedCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(ImageCRS)
     *
     * @deprecated {@code ImageCRS} is replaced by {@link EngineeringCRS} as of ISO 19111:2019.
     */
    @Deprecated(since="3.1")
    public static void validate(final ImageCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(EngineeringCRS)
     */
    public static void validate(final EngineeringCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(VerticalCRS)
     */
    public static void validate(final VerticalCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(TemporalCRS)
     */
    public static void validate(final TemporalCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(CompoundCRS)
     */
    public static void validate(final CompoundCRS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#dispatch(CoordinateSystem)
     */
    public static void validate(final CoordinateSystem object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(CartesianCS)
     */
    public static void validate(final CartesianCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(EllipsoidalCS)
     */
    public static void validate(final EllipsoidalCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(SphericalCS)
     */
    public static void validate(final SphericalCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(CylindricalCS)
     */
    public static void validate(final CylindricalCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(PolarCS)
     */
    public static void validate(final PolarCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(LinearCS)
     */
    public static void validate(final LinearCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(VerticalCS)
     */
    public static void validate(final VerticalCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(TimeCS)
     */
    public static void validate(final TimeCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(UserDefinedCS)
     */
    @Deprecated(since="3.1")
    public static void validate(final UserDefinedCS object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(CoordinateSystemAxis)
     */
    public static void validate(final CoordinateSystemAxis object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#dispatch(Datum)
     */
    public static void validate(final Datum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(PrimeMeridian)
     */
    public static void validate(final PrimeMeridian object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(Ellipsoid)
     */
    public static void validate(final Ellipsoid object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(GeodeticDatum)
     */
    public static void validate(final GeodeticDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(VerticalDatum)
     */
    public static void validate(final VerticalDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(TemporalDatum)
     */
    public static void validate(final TemporalDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(ImageDatum)
     *
     * @deprecated {@code ImageCRS} is replaced by {@link EngineeringCRS} as of ISO 19111:2019.
     */
    @Deprecated(since="3.1")
    @SuppressWarnings("removal")
    public static void validate(final ImageDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(EngineeringDatum)
     */
    public static void validate(final EngineeringDatum object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(DatumEnsemble)
     */
    public static void validate(final DatumEnsemble<?> object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#dispatch(CoordinateOperation)
     */
    public static void validate(final CoordinateOperation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(Conversion)
     */
    public static void validate(final Conversion object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(Transformation)
     */
    public static void validate(final Transformation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(ConcatenatedOperation)
     */
    public static void validate(final ConcatenatedOperation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(PassThroughOperation)
     */
    public static void validate(final PassThroughOperation object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(OperationMethod)
     */
    public static void validate(final OperationMethod object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(Formula)
     */
    public static void validate(final Formula object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(MathTransform)
     */
    public static void validate(final MathTransform object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#dispatch(GeneralParameterDescriptor)
     */
    public static void validate(final GeneralParameterDescriptor object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#validate(ParameterDescriptor)
     */
    public static void validate(final ParameterDescriptor<?> object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#validate(ParameterDescriptorGroup)
     */
    public static void validate(final ParameterDescriptorGroup object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#dispatch(GeneralParameterValue)
     */
    public static void validate(final GeneralParameterValue object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#validate(ParameterValue)
     */
    public static void validate(final ParameterValue<?> object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#validate(ParameterValueGroup)
     */
    public static void validate(final ParameterValueGroup object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ReferencingValidator#dispatchObject(IdentifiedObject)
     */
    public static void validate(final IdentifiedObject object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see MetadataBaseValidator#validate(Identifier)
     *
     * @since 3.1
     */
    public static void validate(final Identifier object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#dispatch(GenericName)
     */
    public static void validate(final GenericName object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#validate(LocalName)
     */
    public static void validate(final LocalName object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#validate(ScopedName)
     */
    public static void validate(final ScopedName object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#validate(NameSpace)
     */
    public static void validate(final NameSpace object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#validate(InternationalString)
     */
    public static void validate(final InternationalString object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ImageValidator#validate(ImageReaderSpi)
     */
    public static void validate(final ImageReaderSpi object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ImageValidator#validate(ImageWriterSpi)
     */
    public static void validate(final ImageWriterSpi object) {
        DEFAULT.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ImageValidator#validate(IIOMetadataFormat)
     */
    public static void validate(final IIOMetadataFormat object) {
        DEFAULT.validate(object);
    }
}
