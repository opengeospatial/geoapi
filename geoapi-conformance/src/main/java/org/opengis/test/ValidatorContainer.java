/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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

import org.opengis.test.util.*;
import org.opengis.test.metadata.*;
import org.opengis.test.geometry.*;
import org.opengis.test.referencing.*;


/**
 * A set of convenience methods for validating GeoAPI implementations. Every {@code validate}
 * method defined in this class delegate their work to one of many {@code Validator} objects
 * in various packages. Vendors can change the value of fields in this class if they wish to
 * override some validation process.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class ValidatorContainer {
    /**
     * The validator for {@link GenericName} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public NameValidator naming = new NameValidator(this);

    /**
     * The validator for {@link Citation} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public CitationValidator citation = new CitationValidator(this);

    /**
     * The validator for {@link Extent} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public ExtentValidator extent = new ExtentValidator(this);

    /**
     * The validator for {@link Datum} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public DatumValidator datum = new DatumValidator(this);

    /**
     * The validator for {@link CoordinateSystem} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public CSValidator cs = new CSValidator(this);

    /**
     * The validator for {@link CoordinateReferenceSystem} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public CRSValidator crs = new CRSValidator(this);

    /**
     * The validator for {@link ParameterValue} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public ParameterValidator parameter = new ParameterValidator(this);

    /**
     * The validator for {@link CoordinateOperation} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public OperationValidator coordinateOperation = new OperationValidator(this);

    /**
     * The validator for {@link Geometry} and related objects.
     * Vendors can change this field to a different validator, or change the setting
     * of the referenced validator. This field shall not be set to {@code null} however.
     */
    public GeometryValidator geometry = new GeometryValidator(this);

    /**
     * An unmodifiable "live" list of all validators. Any change to the value of a field declared
     * in this class is reflected immediately in this list (so this list is <cite>unmodifiable</cite>
     * but not <cite>immutable</cite>). This list is convenient if the same setting must be applied
     * on all validators, for example in order to change their {@link Validator#logger logger} setting
     * or to set their set {@link Validator#requireMandatoryAttributes requireMandatoryAttributes}
     * field to {@code false}.
     */
    public final List<Validator> all = new AbstractList<Validator>() {
        public int size() {
            return 9;
        }

        public Validator get(int index) {
            switch (index) {
                case  0: return naming;
                case  1: return citation;
                case  2: return extent;
                case  3: return datum;
                case  4: return cs;
                case  5: return crs;
                case  6: return parameter;
                case  7: return coordinateOperation;
                case  8: return geometry;
                default: throw new IndexOutOfBoundsException(String.valueOf(index));
            }
        }
    };

    /**
     * Creates a new {@code ValidatorContainer} initialised with default validators.
     */
    public ValidatorContainer() {
    }

    /**
     * Dispatches the given object to one of the {@code validate(object)} methods.
     * Use this method only if the type is unknow at compile-time.
     *
     * @param object The object to test, or {@code null}.
     */
    public final void dispatch(final Object object) {
        if (object instanceof InternationalString) {
            validate((InternationalString) object);
        } else if (object instanceof ReferenceIdentifier) {
            validate((ReferenceIdentifier) object);
        } else if (object instanceof Citation) {
            validate((Citation) object);
        } else if (object instanceof GenericName) {
            validate((GenericName) object);
        } else if (object instanceof NameSpace) {
            validate((NameSpace) object);
        } else if (object instanceof IdentifiedObject) {
            validate((IdentifiedObject) object);
        } else if (object instanceof GeneralParameterValue) {
            validate((GeneralParameterValue) object);
        } else if (object instanceof DirectPosition) {
            validate((DirectPosition) object);
        } else if (object instanceof Envelope) {
            validate((Envelope) object);
        } else if (object instanceof GeographicExtent) {
            validate((GeographicExtent) object);
        } else if (object instanceof VerticalExtent) {
            validate((VerticalExtent) object);
        } else if (object instanceof TemporalExtent) {
            validate((TemporalExtent) object);
        } else if (object instanceof Extent) {
            validate((Extent) object);
        }
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(Extent)
     */
    public final void validate(final Extent object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(TemporalExtent)
     */
    public final void validate(final TemporalExtent object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(VerticalExtent)
     */
    public final void validate(final VerticalExtent object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#dispatch(GeographicExtent)
     */
    public final void validate(final GeographicExtent object) {
        extent.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(GeographicDescription)
     */
    public final void validate(final GeographicDescription object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(BoundingPolygon)
     */
    public final void validate(final BoundingPolygon object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ExtentValidator#validate(GeographicBoundingBox)
     */
    public final void validate(final GeographicBoundingBox object) {
        extent.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see GeometryValidator#validate(Envelope)
     */
    public final void validate(final Envelope object) {
        geometry.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see GeometryValidator#validate(DirectPosition)
     */
    public final void validate(final DirectPosition object) {
        geometry.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#dispatch(CoordinateReferenceSystem)
     */
    public final void validate(final CoordinateReferenceSystem object) {
        crs.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#validate(GeocentricCRS)
     */
    public final void validate(final GeocentricCRS object) {
        crs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CRSValidator#validate(GeographicCRS)
     */
    public final void validate(final GeographicCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(ProjectedCRS)
     */
    public final void validate(final ProjectedCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(DerivedCRS)
     */
    public final void validate(final DerivedCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(ImageCRS)
     */
    public final void validate(final ImageCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(EngineeringCRS)
     */
    public final void validate(final EngineeringCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(VerticalCRS)
     */
    public final void validate(final VerticalCRS object) {
        crs.validate(object);
    }

    /**
     * Validates the given coordinate reference system.
     *
     * @param object The object to validate, or {@code null}.
     * @see CRSValidator#validate(TemporalCRS)
     */
    public final void validate(final TemporalCRS object) {
        crs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#dispatch(CoordinateSystem)
     */
    public final void validate(final CoordinateSystem object) {
        cs.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CartesianCS)
     */
    public final void validate(final CartesianCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(EllipsoidalCS)
     */
    public final void validate(final EllipsoidalCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(SphericalCS)
     */
    public final void validate(final SphericalCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CylindricalCS)
     */
    public final void validate(final CylindricalCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(PolarCS)
     */
    public final void validate(final PolarCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(LinearCS)
     */
    public final void validate(final LinearCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(VerticalCS)
     */
    public final void validate(final VerticalCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(TimeCS)
     */
    public final void validate(final TimeCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(UserDefinedCS)
     */
    public final void validate(final UserDefinedCS object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CSValidator#validate(CoordinateSystemAxis)
     */
    public final void validate(final CoordinateSystemAxis object) {
        cs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#dispatch(Datum)
     */
    public final void validate(final Datum object) {
        datum.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(PrimeMeridian)
     */
    public final void validate(final PrimeMeridian object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(Ellipsoid)
     */
    public final void validate(final Ellipsoid object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(GeodeticDatum)
     */
    public final void validate(final GeodeticDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(VerticalDatum)
     */
    public final void validate(final VerticalDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(TemporalDatum)
     */
    public final void validate(final TemporalDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(ImageDatum)
     */
    public final void validate(final ImageDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see DatumValidator#validate(EngineeringDatum)
     */
    public final void validate(final EngineeringDatum object) {
        datum.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#dispatch(CoordinateOperation)
     */
    public final void validate(final CoordinateOperation object) {
        coordinateOperation.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(Conversion)
     */
    public final void validate(final Conversion object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(Transformation)
     */
    public final void validate(final Transformation object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(ConcatenatedOperation)
     */
    public final void validate(final ConcatenatedOperation object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(PassThroughOperation)
     */
    public final void validate(final PassThroughOperation object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(OperationMethod)
     */
    public final void validate(final OperationMethod object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(OperationMethod)
     */
    public final void validate(final Formula object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see OperationValidator#validate(MathTransform)
     */
    public final void validate(final MathTransform object) {
        coordinateOperation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#dispatch(GeneralParameterDescriptor)
     */
    public final void validate(final GeneralParameterDescriptor object) {
        parameter.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterDescriptor)
     */
    public final void validate(final ParameterDescriptor<?> object) {
        parameter.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterDescriptorGroup)
     */
    public final void validate(final ParameterDescriptorGroup object) {
        parameter.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#dispatch(GeneralParameterValue)
     */
    public final void validate(final GeneralParameterValue object) {
        parameter.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterValue)
     */
    public final void validate(final ParameterValue<?> object) {
        parameter.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ParameterValidator#validate(ParameterValueGroup)
     */
    public final void validate(final ParameterValueGroup object) {
        parameter.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ReferencingValidator#dispatchObject(IdentifiedObject)
     */
    public final void validate(final IdentifiedObject object) {
        crs.dispatchObject(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see ReferencingValidator#validate(ReferenceIdentifier)
     */
    public final void validate(final ReferenceIdentifier object) {
        crs.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see CitationValidator#validate(Citation)
     */
    public final void validate(final Citation object) {
        citation.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#dispatch(GenericName)
     */
    public final void validate(final GenericName object) {
        naming.dispatch(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(LocalName)
     */
    public final void validate(final LocalName object) {
        naming.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(ScopedName)
     */
    public final void validate(final ScopedName object) {
        naming.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(NameSpace)
     */
    public final void validate(final NameSpace object) {
        naming.validate(object);
    }

    /**
     * Tests the conformance of the given object.
     *
     * @param object The object to test, or {@code null}.
     * @see NameValidator#validate(InternationalString)
     */
    public final void validate(final InternationalString object) {
        naming.validate(object);
    }
}
