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
package org.opengis.metadata.quality;

// OpenGIS direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.ScopeCode;


/**
 * Description of the data specified by the scope.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="DQ_Scope", specification=ISO_19115)
public interface Scope {
    /**
     * Hierarchical level of the data specified by the scope.
     */
    @UML(identifier="level", obligation=MANDATORY, specification=ISO_19115)
    ScopeCode getLevel();

    /**
     * Information about the spatial, vertical and temporal extent of the data specified by the
     * scope.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115)
    Extent getExtent();

    /**
     * Detailed description about the level of the data specified by the scope.
     *
     * @todo Need to define the {@code ScopeDescription} union. This union depends
     *       on {@code FeatureType} and {@code AttributeType}.
     */
//  @UML(identifier="levelDescription", obligation=CONDITIONAL)
//  Collection<ScopeDescription> getLevelDescription();
}
