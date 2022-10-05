/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import java.util.Date;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.metadata.maintenance.Scope;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface of more specific result classes.
 * At least one data quality result shall be provided for each {@linkplain Element data quality element}.
 * Different types of results can be provided for the same data quality elements. This could be
 * a {@linkplain QuantitativeResult quantitative result},
 * a {@linkplain ConformanceResult conformance result},
 * a {@linkplain DescriptiveResult descriptive result} or
 * a {@linkplain CoverageResult coverage result}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 *
 * @see Element#getResults()
 *
 * @since 2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="DQ_Result", specification=ISO_19157)
public interface Result {
    /**
     * Scope of the result.
     * Quality frequently differs between various parts of the data set for which quality is evaluated.
     * Therefore several evaluations may be applied for the same {@linkplain Element data quality element}.
     * To avoid repeating the measure and evaluation procedure descriptions in several instances of {@link Element},
     * several results with individual result scopes can be used.
     *
     * @return scope of the result, or {@code null} if unspecified.
     *
     * @see DataQuality#getScope()
     *
     * @since 3.1
     */
    @UML(identifier="resultScope", obligation=OPTIONAL, specification=ISO_19157)
    default Scope getResultScope() {
        return null;
    }

    /**
     * Date when the result was generated.
     *
     * @return date of the result, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19157)
    default Date getDateTime() {
        return null;
    }
}
