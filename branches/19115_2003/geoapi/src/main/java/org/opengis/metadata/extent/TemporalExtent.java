/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/extent/TemporalExtent.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.extent;

// J2SE direct dependencies
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Date;

import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;


/**
 * Time period covered by the content of the dataset.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @todo UML specifies only one attribute, {@code extent}, of {@code TM_Primitive} type.
 */
@UML(identifier="EX_TemporalExtent", specification=ISO_19115)
public interface TemporalExtent extends MetadataEntity{
    /**
     * Returns the date and time for the content of the dataset.
     *
     * @todo ISO 19115 use a TM_Primitive return type from ISO 19108.
     *       We need to give a closer look at this specification.
     */
	
	
//  @UML(identifier="extent", obligation=MANDATORY)
//  Date getExtent();
    Date getStartTime();
    Date getEndTime();

}
