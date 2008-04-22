/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.style;

import org.opengis.annotation.XmlElement;

/**
 * Abstract superclass of the various symbolizer classes.
 *
 * <b>using a static geometry<b/>
 * <p>
 * you can use static geometry if needed, see {@link #getGeometryAttribut}
 * </p>
 * 
 * <b>Particular cases if the geometry is not the defined type of the symbolizer</b>
 * <p>
 * Geometry types other than inherently linear types can also be used. If a point geometry is
 * used, it should be interpreted as a line of “epsilon” (arbitrarily small) length with a
 * horizontal orientation centered on the point, and should be rendered with two end caps.
 * If a polygon is used (or other “area” type), then its closed outline is used as the line string
 * (with no end caps). If a raster geometry is used, its coverage-area outline is used for the
 * line, rendered with no end caps.
 * </p>
 *  
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
public interface Symbolizer {
    
    /**
     * Returns a string of containing the measure unit name.
     * This parameter is herited from GML.
     * Renderers shall use the unit to correctly render symbols.
     * 
     * recommended uom definitions are :
     * <p>
     * <ul>
     *     <li>{@code metre}</li>
     *     <li>{@code foot}</li>
     *     <li>{@code pixel}</li>
     * </ul>
     * <p>
     *  
     * @return String name for the measure unit to use,
     * can be null. If the unit is null than we shall use a the pixel unit
     */
    @XmlElement("uom")
    String getUnitOfMeasure();
    
    /**
     * Set the measure unit
     * See {@link #getUnitOfMeasure}
     * @param unitName : measure unit name 
     */
    @XmlElement("uom")
    void setUnitOfMeasure(String unitName);
    
    /**
     * Returns the name of the geometry feature attribute to use for drawing.
     * May return null if this symbol is to use the default geometry attribute,
     * whatever it may be. 
     * <p>                                                                               The content
     * of the element gives the property name in XPath syntax. In principle, a fixed geometry
     * could be defined using GML or operators could be defined for computing the geometry
     * from references or literals. However, using a feature property directly is by far the most
     * commonly useful method.
     * </p>
     * @return geometry attribut name
     */
    @XmlElement("Geometry")
    String getGeometryAttribute();

    /**
     * Sets the name of the geometry feature attribute that will be used for
     * drawing this symbol.
     * See {@link #getGeometryAttribute} for details.
     * @param name : geometry attribut name
     */
    @XmlElement("Geometry")
    void setGeometryAttribute(String name);

    /**
     * Returns a name for this symbolizer.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     * @return a name for this style.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Sets the name for this symbolizer.
     * See {@link #getName} for details.
     * @param name , new name for the style
     */
    @XmlElement("Name")
    void setName( String name);

    /**
     * Returns the description of this symbolizer.
     * 
     * @return Description with usual informations used
     * for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * Sets the description of this symbolizer.
     * See {@link #getDescription} for details.
     * @param description : the new description
     */
    @XmlElement("Description")
    void setDescription(Description description);

}
