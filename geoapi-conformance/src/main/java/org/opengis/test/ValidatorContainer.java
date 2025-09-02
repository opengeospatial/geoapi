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

import java.util.List;
import java.util.AbstractList;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.metadata.IIOMetadataFormat;

import org.opengis.util.*;
import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.quality.*;
import org.opengis.metadata.maintenance.*;
import org.opengis.metadata.maintenance.Scope;      // Resolve ambiguity.
import org.opengis.geometry.*;
import org.opengis.parameter.*;
import org.opengis.referencing.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;

import org.opengis.test.util.*;
import org.opengis.test.metadata.*;
import org.opengis.test.geometry.*;
import org.opengis.test.referencing.*;
import org.opengis.test.coverage.image.*;


/**
 * A set of convenience methods for validating GeoAPI implementations. Every {@code validate}
 * method defined in this class delegate their work to one of many {@code Validator} objects
 * in various packages. Vendors can change the value of fields in this class if they wish to
 * override some validation process.
 *
 * <h2>Customization</h2>
 * All {@code validate(…)} methods in this class are final because this class is not the extension
 * point for overriding validation processes. Instead, extend the appropriate {@link Validator}
 * subclass and assign an instance to the corresponding field in this class. For example in order
 * to override the validation of {@link org.opengis.referencing.crs.GeodeticCRS} objects,
 * one can do:
 *
 * {@snippet lang="java" :
 * ValidatorContainer container = new ValidationContainer();
 * container.crs = new CRSValidator(container) {
 *     @Override
 *     public void validate(GeodeticCRS object) {
 *         super.validate(object);
 *         // Perform additional validation here.
 *     }
 * };}
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class ValidatorContainer {
    /**
     * The validator for {@link GenericName} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public NameValidator naming = new NameValidator(this);

    /**
     * The validator for {@link Metadata} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     *
     * @since 3.1
     */
    @SuppressWarnings("this-escape")
    public MetadataBaseValidator metadata = new MetadataBaseValidator(this);

    /**
     * The validator for {@link Citation} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public CitationValidator citation = new CitationValidator(this);

    /**
     * The validator for {@link DataQuality} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     *
     * @since 3.1
     */
    @SuppressWarnings("this-escape")
    public QualityValidator quality = new QualityValidator(this);

    /**
     * The validator for {@link MaintenanceInformation} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     *
     * @since 3.1
     */
    @SuppressWarnings("this-escape")
    public MaintenanceValidator maintenance = new MaintenanceValidator(this);

    /**
     * The validator for {@link Extent} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public ExtentValidator extent = new ExtentValidator(this);

    /**
     * The validator for {@link Datum} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public DatumValidator datum = new DatumValidator(this);

    /**
     * The validator for {@link CoordinateSystem} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public CSValidator cs = new CSValidator(this);

    /**
     * The validator for {@link CoordinateReferenceSystem} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public CRSValidator crs = new CRSValidator(this);

    /**
     * The validator for {@link ParameterValue} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public ParameterValidator parameter = new ParameterValidator(this);

    /**
     * The validator for {@link CoordinateOperation} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public OperationValidator coordinateOperation = new OperationValidator(this);

    /**
     * The validator for {@link Geometry} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public GeometryValidator geometry = new GeometryValidator(this);

    /**
     * The validator for images and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    @SuppressWarnings("this-escape")
    public ImageValidator image = new ImageValidator(this);

    /**
     * An unmodifiable "live" list of all validators. Any change to the value of a field declared
     * in this class is reflected immediately in this list (so this list is <i>unmodifiable</i>
     * but not <i>immutable</i>). This list is convenient if the same setting must be applied
     * on all validators, for example in order to change their {@link Validator#logger logger} setting
     * or to set their set {@link Validator#requireMandatoryAttributes requireMandatoryAttributes}
     * field to {@code false}.
     */
    public final List<Validator> all;

    /**
     * Creates a new {@code ValidatorContainer} initialized with new {@link Validator} instances.
     * Note that this constructor does not inherit the configuration of the {@link Validators#DEFAULT} instance.
     */
    public ValidatorContainer() {
        all = new AbstractList<Validator>() {
            /** Returns the number of elements in this list. */
            @Override public int size() {
                return 13;
            }

            /** Returns the validator at the given index. */
            @Override public Validator get(final int index) {
                switch (index) {
                    case  0: return naming;
                    case  1: return metadata;
                    case  2: return citation;
                    case  3: return quality;
                    case  4: return maintenance;
                    case  5: return extent;
                    case  6: return datum;
                    case  7: return cs;
                    case  8: return crs;
                    case  9: return parameter;
                    case 10: return coordinateOperation;
                    case 11: return geometry;
                    case 12: return image;
                    default: throw new IndexOutOfBoundsException(index);
                }
            }
        };
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     * Use this method only if the type is unknown at compile-time.
     *
     * @param  object The object to dispatch to {@code validate(…)} methods, or {@code null}.
     */
    public final void dispatch(final Object object) {
        if (object instanceof Metadata)              validate((Metadata)              object);
        if (object instanceof Citation)              validate((Citation)              object);
        if (object instanceof CitationDate)          validate((CitationDate)          object);
        if (object instanceof CitationDate[])        validate((CitationDate[])        object);
        if (object instanceof Responsibility)        validate((Responsibility)        object);
        if (object instanceof Party)                 validate((Party)                 object);
        if (object instanceof Contact)               validate((Contact)               object);
        if (object instanceof Telephone)             validate((Telephone)             object);
        if (object instanceof Address)               validate((Address)               object);
        if (object instanceof OnlineResource)        validate((OnlineResource)        object);
        if (object instanceof DataQuality)           validate((DataQuality)           object);
        if (object instanceof Element)               validate((Element)               object);  // Dispatch according sub-type.
        if (object instanceof Result)                validate((Result)                object);  // Dispatch according sub-type.
        if (object instanceof Extent)                validate((Extent)                object);
        if (object instanceof GeographicExtent)      validate((GeographicExtent)      object);  // Dispatch according sub-type.
        if (object instanceof VerticalExtent)        validate((VerticalExtent)        object);
        if (object instanceof TemporalExtent)        validate((TemporalExtent)        object);
        if (object instanceof IdentifiedObject)      validate((IdentifiedObject)      object);  // Dispatch according sub-type.
        if (object instanceof Identifier)            validate((Identifier)            object);
        if (object instanceof GenericName)           validate((GenericName)           object);  // Dispatch according sub-type.
        if (object instanceof NameSpace)             validate((NameSpace)             object);
        if (object instanceof GeneralParameterValue) validate((GeneralParameterValue) object);  // Dispatch according sub-type.
        if (object instanceof Envelope)              validate((Envelope)              object);
        if (object instanceof DirectPosition)        validate((DirectPosition)        object);
        if (object instanceof InternationalString)   validate((InternationalString)   object);
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
    public final void validate(final Metadata object) {
        metadata.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CitationValidator#validate(Citation)
     */
    public final void validate(final Citation object) {
        citation.validate(object);
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
    public final void validate(final CitationDate... object) {
        citation.validate(object);
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
    public final void validate(final Responsibility object) {
        citation.validate(object);
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
    public final void validate(final Party object) {
        citation.validate(object);
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
    public final void validate(final Contact object) {
        citation.validate(object);
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
    public final void validate(final Telephone object) {
        citation.validate(object);
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
    public final void validate(final Address object) {
        citation.validate(object);
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
    public final void validate(final OnlineResource object) {
        citation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see QualityValidator#validate(DataQuality)
     *
     * @since 3.1
     */
    public final void validate(final DataQuality object) {
        quality.validate(object);
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
    public final void validate(final Element object) {
        quality.dispatch(object);
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
    public final void validate(final PositionalAccuracy object) {
        quality.validate(object);
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
    public final void validate(final Result object) {
        quality.dispatch(object);
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
    public final void validate(final DescriptiveResult object) {
        quality.validate(object);
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
    public final void validate(final ConformanceResult object) {
        quality.validate(object);
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
    public final void validate(final QuantitativeResult object) {
        quality.validate(object);
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
    public final void validate(final CoverageResult object) {
        quality.validate(object);
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
    public final void validate(final MaintenanceInformation object) {
        maintenance.validate(object);
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
    public final void validate(final Scope object) {
        maintenance.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(Extent)
     */
    public final void validate(final Extent object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(TemporalExtent)
     */
    public final void validate(final TemporalExtent object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(VerticalExtent)
     */
    public final void validate(final VerticalExtent object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#dispatch(GeographicExtent)
     */
    public final void validate(final GeographicExtent object) {
        extent.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(GeographicDescription)
     */
    public final void validate(final GeographicDescription object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(BoundingPolygon)
     */
    public final void validate(final BoundingPolygon object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ExtentValidator#validate(GeographicBoundingBox)
     */
    public final void validate(final GeographicBoundingBox object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see GeometryValidator#validate(Envelope)
     */
    public final void validate(final Envelope object) {
        geometry.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see GeometryValidator#validate(DirectPosition)
     */
    public final void validate(final DirectPosition object) {
        geometry.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CRSValidator#dispatch(CoordinateReferenceSystem)
     */
    public final void validate(final CoordinateReferenceSystem object) {
        crs.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CRSValidator#validate(GeodeticCRS)
     *
     * @since 3.1
     */
    public final void validate(final GeodeticCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(ProjectedCRS)
     */
    public final void validate(final ProjectedCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(DerivedCRS)
     */
    public final void validate(final DerivedCRS object) {
        crs.validate(object);
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
    public final void validate(final ImageCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(EngineeringCRS)
     */
    public final void validate(final EngineeringCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(VerticalCRS)
     */
    public final void validate(final VerticalCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(TemporalCRS)
     */
    public final void validate(final TemporalCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(ParametricCRS)
     *
     * @since 3.1
     */
    public final void validate(final ParametricCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param  object  the object to validate, or {@code null}.
     *
     * @see CRSValidator#validate(CompoundCRS)
     */
    public final void validate(final CompoundCRS object) {
        crs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#dispatch(CoordinateSystem)
     */
    public final void validate(final CoordinateSystem object) {
        cs.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(CartesianCS)
     */
    public final void validate(final CartesianCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(EllipsoidalCS)
     */
    public final void validate(final EllipsoidalCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(SphericalCS)
     */
    public final void validate(final SphericalCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(CylindricalCS)
     */
    public final void validate(final CylindricalCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(PolarCS)
     */
    public final void validate(final PolarCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(LinearCS)
     */
    public final void validate(final LinearCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(VerticalCS)
     */
    public final void validate(final VerticalCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(TimeCS)
     */
    public final void validate(final TimeCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(ParametricCS)
     *
     * @since 3.1
     */
    public final void validate(final ParametricCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(UserDefinedCS)
     */
    @Deprecated(since="3.1")
    public final void validate(final UserDefinedCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see CSValidator#validate(CoordinateSystemAxis)
     */
    public final void validate(final CoordinateSystemAxis object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#dispatch(Datum)
     */
    public final void validate(final Datum object) {
        datum.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(PrimeMeridian)
     */
    public final void validate(final PrimeMeridian object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(Ellipsoid)
     */
    public final void validate(final Ellipsoid object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(GeodeticDatum)
     */
    public final void validate(final GeodeticDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(VerticalDatum)
     */
    public final void validate(final VerticalDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(TemporalDatum)
     */
    public final void validate(final TemporalDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(ParametricDatum)
     *
     * @since 3.1
     */
    public final void validate(final ParametricDatum object) {
        datum.validate(object);
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
    public final void validate(final ImageDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(EngineeringDatum)
     */
    public final void validate(final EngineeringDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see DatumValidator#validate(DatumEnsemble)
     *
     * @since 3.1
     */
    public final void validate(final DatumEnsemble<?> object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#dispatch(CoordinateOperation)
     */
    public final void validate(final CoordinateOperation object) {
        coordinateOperation.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(Conversion)
     */
    public final void validate(final Conversion object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(Transformation)
     */
    public final void validate(final Transformation object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(PointMotionOperation)
     *
     * @since 3.1
     */
    public final void validate(final PointMotionOperation object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(ConcatenatedOperation)
     */
    public final void validate(final ConcatenatedOperation object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(PassThroughOperation)
     */
    public final void validate(final PassThroughOperation object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(OperationMethod)
     */
    public final void validate(final OperationMethod object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(OperationMethod)
     */
    public final void validate(final Formula object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see OperationValidator#validate(MathTransform)
     */
    public final void validate(final MathTransform object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#dispatch(GeneralParameterDescriptor)
     */
    public final void validate(final GeneralParameterDescriptor object) {
        parameter.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#validate(ParameterDescriptor)
     */
    public final void validate(final ParameterDescriptor<?> object) {
        parameter.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#validate(ParameterDescriptorGroup)
     */
    public final void validate(final ParameterDescriptorGroup object) {
        parameter.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#dispatch(GeneralParameterValue)
     */
    public final void validate(final GeneralParameterValue object) {
        parameter.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#validate(ParameterValue)
     */
    public final void validate(final ParameterValue<?> object) {
        parameter.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ParameterValidator#validate(ParameterValueGroup)
     */
    public final void validate(final ParameterValueGroup object) {
        parameter.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ReferencingValidator#dispatchObject(IdentifiedObject)
     */
    public final void validate(final IdentifiedObject object) {
        crs.dispatchObject(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ReferencingValidator#validate(ObjectDomain)
     *
     * @since 3.1
     */
    public final void validate(final ObjectDomain object) {
        crs.validate(object);
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
    public final void validate(final Identifier object) {
        metadata.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#dispatch(GenericName)
     */
    public final void validate(final GenericName object) {
        naming.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#validate(LocalName)
     */
    public final void validate(final LocalName object) {
        naming.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#validate(ScopedName)
     */
    public final void validate(final ScopedName object) {
        naming.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#validate(NameSpace)
     */
    public final void validate(final NameSpace object) {
        naming.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see NameValidator#validate(InternationalString)
     */
    public final void validate(final InternationalString object) {
        naming.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ImageValidator#validate(ImageReaderSpi)
     */
    public final void validate(final ImageReaderSpi object) {
        image.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ImageValidator#validate(ImageWriterSpi)
     */
    public final void validate(final ImageWriterSpi object) {
        image.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param  object  the object to test, or {@code null}.
     *
     * @see ImageValidator#validate(IIOMetadataFormat)
     */
    public final void validate(final IIOMetadataFormat object) {
        image.validate(object);
    }
}
