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

import java.util.List;
import org.opengis.filter.expression.Expression;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;


/**
 * The Font element identifies a font of a certain family, style, and size.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("Font")
public interface Font {

    //*************************************************************
    // SVG PARAMETERS
    //*************************************************************

    /**
     * The "font-family" SvgParameter element gives the family name of a font to use.
     * Allowed values are system-dependent. Any number of font-family SvgParameter
     * elements may be given and they are assumed to be in preferred order.
     *
     * @return live list of font family
     */
    @XmlParameter("font-familly")
    List<Expression> getFamily();

    /**
     * The "font-style" SvgParameter element gives the style to use for a font. The allowed
     * values are "normal", "italic", and "oblique".
     * If null, the default is "normal".
     * @return Expression or Expression.NIL
     */
    @XmlParameter("font-style")
    Expression getStyle();

    /**
     * The "font-weight" SvgParameter element gives the amount of weight or boldness to use
     * for a font. Allowed values are "normal" and "bold".
     * If null, the default is "normal".
     *
     * @return Expression or or Expression.NIL
     */
    @XmlParameter("font-weight")
    Expression getWeight();

    /**
     * The "font-size" SvgParameter element gives the size to use for the font in pixels. The
     * default is defined to be 10 pixels, though various systems may have restrictions on what
     * sizes are available.
     *
     * @return Expression or null
     */
    @XmlParameter("font-size")
    Expression getSize();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);

}
