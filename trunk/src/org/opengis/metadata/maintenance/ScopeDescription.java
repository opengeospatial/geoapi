/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.maintenance;

// J2SE directdependencies
import java.util.Set;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Description of the class of information covered by the information.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit Collection types in this interface are not yet defined, because they require
 *          <code>Feature</code> and <code>FeatureType</code>.
 */
///@UML (identifier="MD_ScopeDescription")
public interface ScopeDescription {
    /**
     * Attributes to which the information applies.
     */
/// @UML (identifier="attributes", obligation=CONDITIONAL)
    public Set getAttributes();

    /**
     * Features to which the information applies.
     */
/// @UML (identifier="features", obligation=CONDITIONAL)
    public Set getFeatures();

    /**
     * Reature instances to which the information applies.
     */
/// @UML (identifier="featureInstances", obligation=CONDITIONAL)
    public Set getFeatureInstances();
}
