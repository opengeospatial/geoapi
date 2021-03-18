/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.style;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;

import org.opengis.annotation.XmlElement;


/**
 * The ContrastEnhancement element defines contrast enhancement for a channel of a
 * false-color image or for a color image.
 *
 * In the case of a color image, the relative grayscale brightness of a pixel color is used.
 * “Normalize” means to stretch the contrast so that the dimmest color is stretched to black
 * and the brightest color is stretched to white, with all colors in between stretched out
 * linearly. “Histogram” means to stretch the contrast based on a histogram of how many
 * colors are at each brightness level on input, with the goal of producing equal number of
 * pixels in the image at each brightness level on output. This has the effect of revealing
 * many subtle ground features.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("ContrastEnchancement:type")
public final class ContrastMethod extends CodeList<ContrastMethod> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -7328502367911363577L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ContrastMethod> VALUES = new ArrayList<ContrastMethod>(3);

    /**
     * Normalize enchancement.
     * “Normalize” means to stretch the contrast so that the dimmest color is stretched to black
     * and the brightest color is stretched to white, with all colors in between stretched out
     * linearly.
     */
    @XmlElement("Normalize")
    public static final ContrastMethod NORMALIZE = new ContrastMethod("NORMALIZE");

    /**
     * Histogram enchancement.
     * “Histogram” means to stretch the contrast based on a histogram of how many
     * colors are at each brightness level on input, with the goal of producing equal number of
     * pixels in the image at each brightness level on output.
     */
    @XmlElement("Histogram")
    public static final ContrastMethod HISTOGRAM = new ContrastMethod("HISTOGRAM");

    /**
     * No enchancement.
     * this is the default value.
     */
    public static final ContrastMethod NONE = new ContrastMethod("NONE");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by an other element of this type.
     */
    private ContrastMethod(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ContrastType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static ContrastMethod[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new ContrastMethod[VALUES.size()]);
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
    public ContrastMethod[] family() {
        return values();
    }

    /**
     * Returns the contrast type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static ContrastMethod valueOf(String code) {
        return valueOf(ContrastMethod.class, code);
    }
}
