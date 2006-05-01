/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.temporal;

// OpenGIS direct dependencies
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides information about a temporal reference system.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 *
 * @todo Maybe it should extends {@link org.opengis.referencing.ReferenceSystem}.
 */
@UML(identifier="TM_ReferenceSystem", specification=ISO_19108)
public interface TemporalReferenceSystem {
    /**
     * Provides a name that uniquely identifies the temporal referece system.
     *
     * Currently returns MD_Identifier, which is defined in ISO 19115, while ISO 19108 
     * requires that RS_Identifier (defined in ISO 19111 and http://www.opengis.org/docs/03-073r1.zip) 
     * is returned. From the looks of it, org.opengis.referencing.ReferenceSystem could also fit the bill.
     * @return {@link Identifier}
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19108)
    Identifier getName();

    /**
     * Identifies the space and time within which the reference system is applicable.
     * The return type allows for describing both spatial and temporal extent.
     * This attribute shall be used whenever an application schema includes
     * {@link TemporalPosition}s referenced to a reference system which has a valid extent
     * that is less than the extent of a data set containing such values.
     */
    @UML(identifier="DomainOfValidity", obligation=MANDATORY, specification=ISO_19108)
    Extent getDomainOfValidity();
}
