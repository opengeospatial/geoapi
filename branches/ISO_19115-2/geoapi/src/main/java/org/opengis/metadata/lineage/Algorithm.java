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
package org.opengis.metadata.lineage;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Details of the methodology by which geographic information was derived from the instrument
 * readings.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="LE_Algorithm", specification=ISO_19115_2)
public interface Algorithm {
    /**
     * Information identifying the algorithm and version or date.
     *
     * @return Algorithm and version or date.
     */
    @UML(identifier="citation", obligation=MANDATORY, specification=ISO_19115_2)
    Citation getCitation();

    /**
     * Information describing the algorithm used to generate the data.
     *
     * @return Algorithm used to generate the data.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getDescription();
}
