/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.constraint;

// J2SE dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;


/**
 * Restrictions on the access and use of a resource or metadata.
 *
 * @UML datatype MD_Constraints
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Constraints {
    /**
     * Limitation affecting the fitness for use of the resource. Example, "not to be used for
     * navigation".
     *
     * @UML optional useLimitation
     */
    List/*<InternationalString>*/ getUseLimitation();
}
