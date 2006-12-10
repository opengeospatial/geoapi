/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/content/RangeDimension.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// OpenGIS direct dependencies
import org.opengis.metadata.MetadataEntity;
import org.opengis.util.InternationalString;
import org.opengis.util.LocalName;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information on the range of each dimension of a cell measurement value.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_RangeDimension", specification=ISO_19115)
public interface RangeDimension extends MetadataEntity{
    /**
     * Number that uniquely identifies instances of bands of wavelengths on which a sensor
     * operates.
     */
    @UML(identifier="sequenceIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    LocalName getSequenceIdentifier();

    /**
     * Description of the range of a cell measurement value.
     */
    @UML(identifier="descriptor", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getDescriptor();
}
