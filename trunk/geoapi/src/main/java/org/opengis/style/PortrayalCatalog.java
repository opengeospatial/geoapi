/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.style;

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * A portrayal catalog consits of a set of feature portrayal objects. Many may
 * exist for each feature type that may occur in the dataset. each feature object 
 * has assigned a set of portrayal rules.
 * 
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_PortrayalCatalog", specification=ISO_19117)
public interface PortrayalCatalog {

    /**
     * Returns a collection of feature portrayals.
     * 
     * @return Collection<FeaturePortrayal> , this is the live collection.
     */
    @UML(identifier="featurePortrayal", obligation=MANDATORY, specification=ISO_19117)
    List<FeatureTypeStyle> getFeatureTypes();
        
    /**
     * Returns the default specification used if no rule return true.
     * This specification should not use any external functions.
     * This specification should use at least one spatial attribut.
     * 
     * @return PortrayalSpecification
     */
    @UML(identifier="defaultPortrayalSpec", obligation=MANDATORY, specification=ISO_19117)
    Symbolizer getDefaultSpecification();

}
