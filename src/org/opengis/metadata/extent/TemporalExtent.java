/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.extent;

// J2SE direct dependencies
import java.util.Date;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Time period covered by the content of the dataset.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit UML specifies only one attribute, <code>extent</code>, of <code>TM_Primitive</code>
 *          type.
 */
@UML (identifier="EX_TemporalExtent", specification=ISO_19115)
public interface TemporalExtent {
    /**
     * Returns the date and time for the content of the dataset.
     *
     * @revisit ISO 19115 use a TM_Primitive return type from ISO 19108.
     *          We need to give a closer look at this specification.
     */
//  @UML (identifier="extent", obligation=MANDATORY)
//  Date getExtent();
}
