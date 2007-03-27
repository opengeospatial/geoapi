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
package org.opengis.metadata.content;

// OpenGIS direct dependencies
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import org.opengis.util.MemberName;


/**
 * Information on the range of each dimension of a cell measurement value.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_RangeDimension", specification=ISO_19115)
public interface RangeDimension {
    /**
     * Number that uniquely identifies instances of bands of wavelengths on which a sensor
     * operates.
     */
    @UML(identifier="sequenceIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    MemberName getSequenceIdentifier();

    /**
     * Description of the range of a cell measurement value.
     */
    @UML(identifier="descriptor", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getDescriptor();
}
