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
package org.opengis.metadata.maintenance;

// J2SE directdependencies
import java.util.Set;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the class of information covered by the information.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @todo Collection types in this interface are not yet defined, because they require
 *       {@code Feature} and {@code FeatureType}.
 */
@UML(identifier="MD_ScopeDescription", specification=ISO_19115)
public interface ScopeDescription {
    /**
     * Attributes to which the information applies.
     */
    @UML(identifier="attributes", obligation=CONDITIONAL, specification=ISO_19115)
    public Set getAttributes();

    /**
     * Features to which the information applies.
     */
    @UML(identifier="features", obligation=CONDITIONAL, specification=ISO_19115)
    public Set getFeatures();

    /**
     * Reature instances to which the information applies.
     */
    @UML(identifier="featureInstances", obligation=CONDITIONAL, specification=ISO_19115)
    public Set getFeatureInstances();
}
