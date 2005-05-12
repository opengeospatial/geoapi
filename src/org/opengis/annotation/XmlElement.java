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
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * Maps an interface or a method to the XML type, element or attribute.
 * Interfaces usually map to XML types, while methods map to XML element
 * or attribute. It is not the purpose of this annotation to differentiate
 * types from attributes, since this distinction can already be inferred from
 * Java reflection. This annotation, completed with reflection if needed, should
 * only provides enough information for finding the corresponding XML element in
 * the schema.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @since GeoAPI 1.1
 */
@Documented
@Target({TYPE,METHOD})
@Retention(RUNTIME)
public @interface XmlElement {
    /**
     * The name of the element in the XML schema.
     */
    String name();

    /**
     * The obligation declared in the XML schema. This metadata can be queried in order to
     * determine if at least one element is required for the annotated method or not. If the
     * obligation is {@link Obligation#MANDATORY}, then null value or empty collection are not
     * allowed.
     */
    Obligation obligation() default Obligation.MANDATORY;    
}
