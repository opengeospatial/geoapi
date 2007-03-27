/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.annotation;

// J2SE dependencies
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * An annotation for classes or method that are <A HREF="http://geoapi.sourceforge.net">GeoAPI</A>
 * extension. This annotation is mutually exclusive with {@link UML}.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Documented
@Target({TYPE, FIELD, METHOD})
@Retention(SOURCE)
public @interface Extension {
}
