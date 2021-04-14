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
package org.opengis.metadata.content;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.metadata.quality.CoverageResult;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specific type of information represented in the cell.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_CoverageContentTypeCode", specification=ISO_19115)
public final class CoverageContentType extends CodeList<CoverageContentType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -346887088822021485L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<CoverageContentType> VALUES = new ArrayList<>(8);

    /**
     * Meaningful numerical representation of a physical parameter that is not the actual
     * value of the physical parameter.
     */
    @UML(identifier="image", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType IMAGE = new CoverageContentType("IMAGE");

    /**
     * Code value with no quantitative meaning, used to represent a physical quantity.
     */
    @UML(identifier="thematicClassification", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType THEMATIC_CLASSIFICATION = new CoverageContentType("THEMATIC_CLASSIFICATION");

    /**
     * Value in physical units of the quantity being measured.
     */
    @UML(identifier="physicalMeasurement", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType PHYSICAL_MEASUREMENT = new CoverageContentType("PHYSICAL_MEASUREMENT");

    /**
     * Data, usually a physical measurement, used to support the calculation of the primary
     * {@linkplain #PHYSICAL_MEASUREMENT physical measurement} coverages in the dataset.
     *
     * <div class="note"><b>Example:</b>
     * grid of aerosol optical thickness used in the calculation of a sea surface temperature product.
     * </div>
     *
     * @since 3.1
     */
    @UML(identifier="auxillaryInformation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType AUXILLARY_INFORMATION = new CoverageContentType("AUXILLARY_INFORMATION");

    /**
     * Data used to characterize the quality of the {@linkplain #PHYSICAL_MEASUREMENT physical measurement}
     * coverage in the dataset. Typically included in a {@link CoverageResult}.
     *
     * @since 3.1
     */
    @UML(identifier="qualityInformation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType QUALITY_INFORMATION = new CoverageContentType("QUALITY_INFORMATION");

    /**
     * Reference information use to support the calculation or use of
     * {@linkplain #PHYSICAL_MEASUREMENT physical measurement} coverages in the dataset.
     *
     * <div class="note"><b>Example:</b>
     * grid of latitude longitude used to geolocate the physical measurement.
     * </div>
     *
     * @since 3.1
     */
    @UML(identifier="referenceInformation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType REFERENCE_INFORMATION = new CoverageContentType("REFERENCE_INFORMATION");

    /**
     * Results with values that are calculated using a model rather than being observed or calculated from observations.
     *
     * @since 3.1
     */
    @UML(identifier="modelResult", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType MODEL_RESULT = new CoverageContentType("MODEL_RESULT");

    /**
     * Data used to provide coordinate axis values.
     *
     * @since 3.1
     */
    @UML(identifier="coordinate", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CoverageContentType COORDINATE = new CoverageContentType("COORDINATE");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private CoverageContentType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code CoverageContentType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static CoverageContentType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new CoverageContentType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public CoverageContentType[] family() {
        return values();
    }

    /**
     * Returns the coverage content type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static CoverageContentType valueOf(String code) {
        return valueOf(CoverageContentType.class, code);
    }
}
