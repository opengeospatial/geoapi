/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designation of criterion for defining maximum and minimum wavelengths for a spectral band.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_BandDefinition", specification=ISO_19115_2)
public final class BandDefinition extends CodeList<BandDefinition> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6673852201803408346L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<BandDefinition> VALUES = new ArrayList<>(5);

    /**
     * Width of a distribution equal to the distance between the outer two points on the
     * distribution having power level half of that at the peak.
     *
     * @todo the same as the documentation for {@link #HALF_MAXIMUM}.
     */
    @UML(identifier="3dB", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition THREE_DB = new BandDefinition("THREE_DB");

    /**
     * Width of a distribution equal to the distance between the outer two points on the
     * distribution having power level half of that at the peak.
     *
     * @todo the same as the documentation for {@link #THREE_DB}.
     */
    @UML(identifier="halfMaximum", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition HALF_MAXIMUM = new BandDefinition("HALF_MAXIMUM");

    /**
     * Full spectral width of a spectral power density measured at 50% of its peak height.
     */
    @UML(identifier="fiftyPercent", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition FIFTY_PERCENT = new BandDefinition("FIFTY_PERCENT");

    /**
     * Width of a distribution equal to the distance between the outer two points on the
     * distribution having power level 1/e that of the peak.
     */
    @UML(identifier="oneOverE", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition ONE_OVER_E = new BandDefinition("ONE_OVER_E");

    /**
     * Width of a band with full sensitivity or absorption at every wavelength that detects
     * or absorbs the same amount of energy as the band described.
     */
    @UML(identifier="equivalentWidth", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition EQUIVALENT_WIDTH = new BandDefinition("EQUIVALENT_WIDTH");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private BandDefinition(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code BandDefinition}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static BandDefinition[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new BandDefinition[VALUES.size()]);
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
    public BandDefinition[] family() {
        return values();
    }

    /**
     * Returns the band definition that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static BandDefinition valueOf(String code) {
        return valueOf(BandDefinition.class, code);
    }
}
