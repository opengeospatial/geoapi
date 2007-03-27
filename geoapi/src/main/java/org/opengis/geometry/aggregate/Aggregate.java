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
package org.opengis.geometry.aggregate;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.geometry.Geometry;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Geometry that is an aggregate of other geometries.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Aggregate", specification=ISO_19107)
public interface Aggregate extends Geometry {
    /**
     * Returns the set containing the elements that compose this aggregate. The
     * set may be modified if this geometry {@linkplain #isMutable is mutable}.
     */
    @UML(identifier="element", obligation=MANDATORY, specification=ISO_19107)
    public Set<? extends Geometry> getElements();
}
