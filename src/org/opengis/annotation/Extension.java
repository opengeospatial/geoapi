/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.annotation;

// J2SE dependencies
import java.lang.annotation.Documented;


/**
 * An annotation for classes or method that are <A HREF="http://geoapi.sourceforge.net">GeoAPI</A>
 * extension. This annotation is mutually exclusive with {@link UML}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @since 1.1
 */
@Documented
public @interface Extension {
}
