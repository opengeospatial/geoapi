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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * An annotation mapping each interface, methods or fields to
 * the XML schema where they come from.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface XmlSchema {
    /**
     * The URL to the schema.
     */
    String URL();

    /**
     * The element name.
     */
    String element();
}
