/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.acquisition;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.geometry.Geometry;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of collection coverage.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_PlatformPass", specification=ISO_19115_2)
public interface PlatformPass {
    /**
     * Unique name of the pass.
     *
     * @return Unique name of the pass.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Area covered by the pass.
     *
     * @return Area covered by the pass.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115_2)
    Geometry getExtent();

    /**
     * Occurrence of one or more events for a pass.
     *
     * @return Occurrence of one or more events for a pass.
     */
    @UML(identifier="relatedEvent", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Event> getRelatedEvents();
}
