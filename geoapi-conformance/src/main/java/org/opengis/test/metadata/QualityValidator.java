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
package org.opengis.test.metadata;

import org.opengis.util.Record;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.content.RangeDimension;
import org.opengis.metadata.quality.*;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.test.ValidatorContainer;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Validates {@link DataQuality} and related objects from the {@code org.opengis.metadata.quality} package.
 *
 * <p>This class is provided for users wanting to override the validation methods.
 * When the default behavior is sufficient, the {@link org.opengis.test.Validators}
 * static methods provide a more convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class QualityValidator extends MetadataValidator {
    /**
     * Creates a new validator instance.
     *
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public QualityValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata.quality");
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     * @return number of {@code validate(…)} methods invoked in this class for the given object.
     */
    public int dispatch(final Element object) {
        int n = 0;
        if (object != null) {
            if (object instanceof PositionalAccuracy) {validate((PositionalAccuracy) object); n++;}
            if (n == 0) {
                validateElement(object);
            }
        }
        return n;
    }

    /**
     * For each interface implemented by the given object, invokes the corresponding
     * {@code validate(…)} method defined in this class (if any).
     *
     * @param  object  the object to dispatch to {@code validate(…)} methods, or {@code null}.
     * @return number of {@code validate(…)} methods invoked in this class for the given object.
     */
    public int dispatch(final Result object) {
        int n = 0;
        if (object != null) {
            if (object instanceof DescriptiveResult)  {validate((DescriptiveResult)  object); n++;}
            if (object instanceof ConformanceResult)  {validate((ConformanceResult)  object); n++;}
            if (object instanceof QuantitativeResult) {validate((QuantitativeResult) object); n++;}
            if (object instanceof CoverageResult)     {validate((CoverageResult)     object); n++;}
            if (n == 0) {
                validateResult(object);
            }
        }
        return n;
    }

    /**
     * Validates the data quality.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final DataQuality object) {
        if (object == null) {
            return;
        }
        final Scope scope = object.getScope();
        mandatory("DataQuality: must have a scope.", scope);
        container.validate(scope);
        final Element[] reports = toArray(Element.class, object.getReports());
        if (requireMandatoryAttributes) {
            assertNotEquals(0, reports.length, "DataQuality: must have at least one report.");
        }
        for (final Element element : reports) {
            dispatch(element);
        }
    }

    /**
     * Validates the positional accuracy.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final PositionalAccuracy object) {
        if (object == null) {
            return;
        }
        validateElement(object);
    }

    /**
     * Validates the properties common to all elements.
     *
     * @param  object  the object to validate.
     */
    private void validateElement(final Element object) {
        container.validate(object.getStandaloneQualityReportDetails());
        for (final Result result : toArray(Result.class, object.getResults())) {
            dispatch(result);
        }
    }

    /**
     * Validates the descriptive result.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final DescriptiveResult object) {
        if (object == null) {
            return;
        }
        validateResult(object);
        validateMandatory(object.getStatement());
    }

    /**
     * Validates the conformance result.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final ConformanceResult object) {
        if (object == null) {
            return;
        }
        validateResult(object);
        Citation specification = object.getSpecification();
        mandatory("ConformanceResult: must have a specification.", specification);
        container.validate(specification);
        container.validate(object.getExplanation());
        mandatory("ConformanceResult: must have a Boolean.", object.pass());
    }

    /**
     * Validates the quantitative result.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final QuantitativeResult object) {
        if (object == null) {
            return;
        }
        validateResult(object);
        int count = toArray(Record.class, object.getValues()).length;
        if (requireMandatoryAttributes) {
            assertNotEquals(0, count, "QuantitativeResult: must have at least one value.");
        }
    }

    /**
     * Validates the coverage result.
     *
     * @param  object  the object to validate, or {@code null}.
     */
    public void validate(final CoverageResult object) {
        if (object == null) {
            return;
        }
        validateResult(object);
        mandatory("CoverageResult: must have a spatial representation type.",   object.getSpatialRepresentationType());
        mandatory("CoverageResult: must have a result spatial representation.", object.getResultSpatialRepresentation());
        final RangeDimension[] ranges = toArray(RangeDimension.class, object.getResultContent());
        if (object.getResultFormat() == null && object.getResultFile() == null && requireMandatoryAttributes) {
            assertNotEquals(0, ranges.length, "CoverageResult: must have at least one range dimension"
                    + " when result format and result file are not provided.");
        }
    }

    /**
     * Validates the properties common to all results.
     *
     * @param  object  the object to validate.
     */
    private void validateResult(final Result object) {
        container.validate(object.getResultScope());
    }
}
