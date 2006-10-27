/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter.expression;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Expression class whose value is computed by retrieving the value indicated
 * by the provided name.
 * <p>
 * The most common applicatoin of this is to retrive
 * a {@linkplain org.opengis.feature.Feature feature}'s property using
 * an xpath expression.
 * </p>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("PropertyName")
public interface PropertyName extends Expression {
    /**
     * Returns the name of the property whose value will be returned by the
     * {@link #evaluate evaluate} method.
     */
    String getPropertyName();
 
    /**
     * Name of the propertyValue that will be returned by {@link evaulate}.
     * 
     * @param name propertyValue that will be returned
     */
    void setPropertyName( String name );
}