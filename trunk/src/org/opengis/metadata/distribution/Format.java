/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Description of the computer language construct that specifies the representation
 * of data objects in a record, file, message, storage device or transmission channel.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Format")
public interface Format {
    /**
     * Name of the data transfer format(s).
     */
/// @UML (identifier="name", obligation=MANDATORY)
    InternationalString getName();

    /**
     * Version of the format (date, number, etc.).
     */
/// @UML (identifier="version", obligation=MANDATORY)
    InternationalString getVersion();

    /**
     * Amendment number of the format version.
     */
/// @UML (identifier="amendmentNumber", obligation=OPTIONAL)
    InternationalString getAmendmentNumber();

    /**
     * Name of a subset, profile, or product specification of the format.
     */
/// @UML (identifier="specification", obligation=OPTIONAL)
    InternationalString getSpecification();

    /**
     * Recommendations of algorithms or processes that can be applied to read or
     * expand resources to which compression techniques have been applied.
     */
/// @UML (identifier="fileDecompressionTechnique", obligation=OPTIONAL)
    InternationalString getFileDecompressionTechnique();

    /**
     * Provides information about the distributor’s format.
     */
/// @UML (identifier="formatDistributor", obligation=OPTIONAL)
    Set/*<Distributor>*/ getFormatDistributors();
}
