/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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

import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;


/**
 * A Halo is a type of Fill that is applied to the backgrounds of font glyphs. The use of
 * halos greatly improves the readability of text labels.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("Halo")
public interface Halo {

    /**
     * Returns the object that indicates how the halo area around the text
     * should be filled.
     *
     * The default halo fill is solid white (Color
     * “#FFFFFF”). The glyph’s fill is plotted on top of the halo. The default font fill is solid
     * black (Color “#000000”).
     *
     * @return Fill or null
     */
    @XmlElement("Fill")
    Fill getFill();

    /**
     * Returns the expression that will be evaluated to get the pixel radius of
     * the halo around the text.
     *
     * The Radius element gives the absolute size of a halo radius in pixels encoded as a
     * floating-point number. The radius is taken from the outside edge of a font glyph to extend
     * the area of coverage of the glyph (and the inside edge of “holes” in the glyphs). The halo
     * of a text label is considered to be a single shape. The default radius is one pixel.
     * Negative values are not allowed.
     *
     * @return Expression or null
     */
    @XmlElement("Radius")
    Expression getRadius();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);

}
