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

import javax.measure.Unit;
import javax.measure.quantity.Length;

import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;

import static org.opengis.annotation.Specification.*;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.PropertyName;

/**
 * Abstract superclass of the symbolizers defined by the Symbology Encoding specification.
 *
 * <p>Please note you are not free to create your own subtype o Symbolizer - we are limited
 * to LineSymbolizer, PointSymbolizer, PolygonSymbolizer, RasterSymbolizer and TextSymbolizer.</p>
 *
 * <p><b>Using a static geometry</b></p>
 * One can use static geometry if needed, see {@link #getGeometryPropertyName()}
 *
 * <p><b>Particular cases if the geometry is not the defined type of the symbolizer</b></p>
 * Geometry types other than inherently linear types can also be used. If a point geometry is
 * used, it should be interpreted as a line of "epsilon" (arbitrarily small) length with a
 * horizontal orientation centered on the point, and should be rendered with two end caps.
 * If a polygon is used (or other "area" type), then its closed outline is used as the line string
 * (with no end caps). If a raster geometry is used, its coverage-area outline is used for the
 * line, rendered with no end caps.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_PortrayalSpecification", specification=ISO_19117)
public interface Symbolizer {
    /**
     * Returns a  measure unit.
     * This parameter is inherited from GML.
     * Renderers shall use the unit to correctly render symbols.
     *
     * Recommended uom definitions are :
     * <ul>
     *   <li>{@code metre}</li>
     *   <li>{@code foot}</li>
     *   <li>{@code pixel}</li>
     * </ul>
     *
     * @return can be null. If the unit is null than we shall use a the pixel unit
     */
    @XmlElement("uom")
    Unit<Length> getUnitOfMeasure();

    /**
     * Returns the name of the geometry feature attribute to use for drawing.
     * May return null (or Expression.NIL) if this symbol is to use the default geometry attribute,
     * whatever it may be. Using null in this fashion is similar to a PropertyName using
     * the XPath expression ".".
     *
     * <p>The content of the element gives the property name in XPath syntax. In principle, a fixed geometry
     * could be defined using GML or operators could be defined for computing the geometry
     * from references or literals. However, using a feature property directly is by far the most
     * commonly useful method.</p>
     *
     * @return Geometry attribute name if geometry expression is a {@link PropertyName},
     *      or <code>null</code> if geometry expression is not a {@link PropertyName}.
     * @deprecated use {@link Symbolizer#getGeometry()} instead.
     */
    @XmlElement("Geometry")
    @Deprecated
    String getGeometryPropertyName();

    /**
     * Expression used to define a geometry for drawing. May return null if the default
     * geometry attribute should be used. This expression is often a PropertyName.
     *
     * @return Expression used to define a geometry for drawing, or Expression.NIL if the default geometry should be used.
     */
    Expression getGeometry();

    /**
     * Returns a name for this symbolizer.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     *
     * @return a name for this style.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Returns the description of this symbolizer.
     *
     * @return description with usual informations used for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * Calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     * @return value produced
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
