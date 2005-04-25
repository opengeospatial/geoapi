/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of a spatial and temporal reference system used by a dataset.
 *  
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @see org.opengis.referencing.crs.CoordinateReferenceSystem
 */
@UML (identifier="RS_ReferenceSystem", specification=ISO_19111)
public interface ReferenceSystem extends IdentifiedObject {
    /**
     * Area for which the (coordinate) reference system is valid.
     *
     * @return Coordinate reference system valid area, or <code>null</code> if not available.
     */
    @UML (identifier="validArea", obligation=OPTIONAL, specification=ISO_19111)
    Extent getValidArea();

    /**
     * Description of domain of usage, or limitations of usage, for which this
     * (coordinate) reference system object is valid.
     */
    @UML (identifier="scope", obligation=OPTIONAL, specification=ISO_19111)
    InternationalString getScope();
}
