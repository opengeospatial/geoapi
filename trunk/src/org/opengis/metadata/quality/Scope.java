/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// OpenGIS direct dependencies
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.ScopeCode;


/**
 * Description of the data specified by the scope.
 *
 * @UML datatype DQ_Scope
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Scope {
    /**
     * Hierarchical level of the data specified by the scope.
     *
     * @UML mandatory level
     */
    ScopeCode getLevel();

    /**
     * Information about the spatial, vertical and temporal extent of the data specified by the
     * scope.
     *
     * @UML optional extent
     */
    Extent getExtent();

    /**
     * Detailed description about the level of the data specified by the scope.
     *
     * @UML conditional levelDescription
     *
     * @revisit Need to define the <code>ScopeDescription</code> union. This union depends
     *          on <code>FeatureType</code> and <code>AttributeType</code>.
     */
//    ScopeDescription[] getLevelDescription();
}
