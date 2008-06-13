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

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * <p>A parameter set holds a list of actualparameter values. The actual values shall
 * correspond to the formal parameters associated with the portrayal operation.
 * ParameterSet has a label which is referred from the portrayal catalogue.
 * </p>
 * 
 * <p>
 * parameterset objects are used to predefine specific portrayal operations.
 * The label could for example be "thick_red_line", where the parameter values are
 * colour = RED, thickness = 5, brush= SOLID.
 * </p>
 * 
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_ParameterSet", specification=ISO_19117)
public interface ParameterSet extends Collection<AttributeValue>{

    /**
     * Label used for this parameter set.
     * This is a short human readable value.
     * 
     * @return
     */
    @UML(identifier="label", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getLabel();
                
    /**
     * Returns a description of the parameter set.
     * It is a human readable value.
     *  
     * @return InternationalString
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();
        
}
