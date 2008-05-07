/*$************************************************************************************************
 **
 ** $Id: XmlElement.java 982 2007-03-27 10:54:51Z desruisseaux $
 **
 ** $URL: https://geoapi.svn.sourceforge.net/svnroot/geoapi/trunk/geoapi/src/main/java/org/opengis/annotation/XmlElement.java $
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * Maps an interface or a method to the XML Parameter.
 * This annotation, completed with reflection if needed, should
 * only provides enough information for finding the corresponding XML parameter in
 * the {@linkplain XmlSchema schema}.
 *
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@Documented
@Target({TYPE,METHOD})
@Retention(RUNTIME)
public @interface XmlParameter {
    
    /**
     * The name of the element in the XML schema.
     */
    String value();
}
