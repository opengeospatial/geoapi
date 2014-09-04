/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.content;

import javax.measure.unit.Unit;
import org.opengis.util.Record;
import org.opengis.util.RecordType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * The characteristics of each dimension (layer) included in the resource.
 *
 * @author  Rémi Maréchal (geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_SampleDimension", specification=ISO_19115)
public interface SampleDimension extends RangeDimension {
    /**
     * Maximum value of data values in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return Maximum value of data values in each dimension included in the resource, or {@code null} if none.
     */
    @UML(identifier="maxValue", obligation=OPTIONAL, specification=ISO_19115)
    Double getMaxValue();

    /**
     * Minimum value of data values in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return Minimum value of data values in each dimension included in the resource, or {@code null} if none.
     */
    @UML(identifier="minValue", obligation=OPTIONAL, specification=ISO_19115)
    Double getMinValue();

    /**
     * Units of data in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return Units of data in each dimension included in the resource, or {@code null} if none.
     *
     * @condition Mandatory if {@linkplain #getMinValue()}, {@linkplain #getMaxValue()}
     *            or {@linkplain #getMeanValue()} are provided.
     */
    @UML(identifier="units", obligation=CONDITIONAL, specification=ISO_19115)
    Unit<?> getUnits();

    /**
     * Scale factor which has been applied to the cell value.
     * May be {@code null} if unspecified.
     *
     * @return Scale factor which has been applied to the cell value, or {@code null} if none.
     */
    @UML(identifier="scaleFactor", obligation=OPTIONAL, specification=ISO_19115)
    Double getScaleFactor();

    /**
     * Physical value corresponding to a cell value of zero.
     * May be {@code null} if unspecified.
     *
     * @return Physical value corresponding to a cell value of zero, or {@code null} if none.
     */
    @UML(identifier="offset", obligation=OPTIONAL, specification=ISO_19115)
    Double getOffset();

    /**
     * Mean value of data values in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return The mean value of data values in each dimension included in the resource, or {@code null} if none.
     */
    @UML(identifier="meanValue", obligation=OPTIONAL, specification=ISO_19115)
    Double getMeanValue();

    /**
     * Number of values used in a thematic classification resource.
     * May be {@code null} if unspecified.
     *
     * <blockquote><font size="-1"><b>Example:</b> the number of classes in a Land Cover Type coverage
     * or the number of cells with data in other types of coverages.</font></blockquote>
     *
     * @return The number of values used in a thematic classification resource, or {@code null} if none.
     */
    @UML(identifier="numberOfValues", obligation=OPTIONAL, specification=ISO_19115)
    Integer getNumberOfValues();

    /**
     * Standard deviation of data values in each dimension included in the resource.
     * May be {@code null} if unspecified.
     *
     * @return Standard deviation of data values in each dimension included in the resource, or {@code null} if none.
     */
    @UML(identifier="standardDeviation", obligation=OPTIONAL, specification=ISO_19115)
    Double getStandardDeviation();

    /**
     * Type of other attribute description.
     * May be {@code null} if unspecified.
     *
     * @return Type of other attribute description, or {@code null} if none.
     */
    @UML(identifier="otherPropertyType", obligation=OPTIONAL, specification=ISO_19115)
    RecordType getOtherPropertyType();

    /**
     * Instance of other attribute type that defines attributes not explicitly included in {@link CoverageContentType}.
     * May be {@code null} if unspecified.
     *
     * @return Instance of other/attributeType that defines attributes, or {@code null} if none.
     */
    @UML(identifier="otherProperty", obligation=OPTIONAL, specification=ISO_19115)
    Record getOtherProperty();

    /**
     * Maximum number of significant bits in the uncompressed representation for the value in each band of each pixel.
     * May be {@code null} if unspecified.
     *
     * @return Maximum number of significant bits in the uncompressed representation
     *         for the value in each band of each pixel, or {@code null} if none.
     */
    @UML(identifier="bitsPerValue", obligation=OPTIONAL, specification=ISO_19115)
    Integer getBitsPerValue();
}
