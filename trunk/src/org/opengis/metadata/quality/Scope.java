/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.ScopeCode;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the data specified by the scope.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @since GeoAPI 1.1
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
     * @revisit Need to define the <code>ScopeDescription</code> union. This union depends
     *          on <code>FeatureType</code> and <code>AttributeType</code>.
     */
//  @UML(identifier="levelDescription", obligation=CONDITIONAL)
//  Collection<ScopeDescription> getLevelDescription();
}
