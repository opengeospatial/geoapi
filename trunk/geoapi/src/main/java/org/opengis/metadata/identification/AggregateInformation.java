/*$************************************************************************************************
 **
 ** $Id: $
 **
 ** $URL: $
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

//J2SE direct dependencies
import java.util.ArrayList;
import java.util.List;

//OpenGIS direct dependencies
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;

//Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Aggregate dataset information.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @since GeoAPI 2.1
 */
@UML(identifier="MD_AggregateInformation", specification=ISO_19115)
public interface AggregateInformation {
    
    /**
     * Citation information about the aggregate dataset
     */
    @UML(identifier="aggregateDataSetName", obligation=CONDITIONAL, specification=ISO_19115)
    Citation getAggregateDataSetName();
    
    /**
     * Identification information about aggregate dataset
     */
    @UML(identifier="aggregateDataSetIdentifier", obligation=CONDITIONAL, specification=ISO_19115)
    Identifier getAggregateDataSetIdentifier();
    
    /**
     * Association type of the aggregate dataset
     */
    @UML(identifier="associationType", obligation=MANDATORY, specification=ISO_19115)
    AssociationType getAssociationType();
    
    /**
     * Type of initiative under which the aggregate dataset was produced
     */
    @UML(identifier="initiativeType", obligation=OPTIONAL, specification=ISO_19115)
    InitiativeType getInitiativeType();

}
