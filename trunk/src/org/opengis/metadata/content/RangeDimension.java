/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information on the range of each dimension of a cell measurement value.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_RangeDimension")
public interface RangeDimension {
    /**
     * Number that uniquely identifies instances of bands of wavelengths on which a sensor
     * operates.
     *
     * @revisit Uncomment this method when <code>MemberName</code> will be defined.
     */
//  @UML (identifier="sequenceIdentifier", obligation=OPTIONAL)
//  MemberName getSequenceIdentifier();

    /**
     * Description of the range of a cell measurement value.
     */
/// @UML (identifier="descriptor", obligation=OPTIONAL)
    InternationalString getDescriptor();
}
