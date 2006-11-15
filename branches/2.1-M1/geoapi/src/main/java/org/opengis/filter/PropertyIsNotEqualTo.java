/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL: https://svn.sourceforge.net/svnroot/geoapi/trunk/geoapi/src/main/java/org/opengis/filter/PropertyIsEqualTo.java $
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

//Annotations
import org.opengis.annotation.XmlElement;

/**
 * Filter operator that compares that its two sub-expressions are not equal to each other.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Justin Deoliveira, The Open Planning Project
 * @since GeoAPI 2.0
 */
@XmlElement("PropertyIsNotEqualTo")
public interface PropertyIsNotEqualTo extends BinaryComparisonOperator {
}
