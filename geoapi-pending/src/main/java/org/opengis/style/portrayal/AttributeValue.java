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
package org.opengis.style.portrayal;


import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * The AttributValue holds the value for an associated AttributeDefinition.
 * 
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_AttributeValue", specification=ISO_19117)
public interface AttributeValue {

    /**
     * Gets the value for the associated AttributeDefinition.
     * This objact shall have the same Class as defined in the AttributeDefinition.
     * 
     * @return Object
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19117)
    Object getValue();
        
    /**
     * Gets the associated AttributeDefinition.
     * 
     * @return AttributDefinition
     */
    @UML(identifier="attributType", obligation=MANDATORY, specification=ISO_19117)
    AttributeDefinition getDefinition();
        
}
