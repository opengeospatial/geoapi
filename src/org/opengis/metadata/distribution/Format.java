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


/**
 * Description of the computer language construct that specifies the representation
 * of data objects in a record, file, message, storage device or transmission channel.
 *
 * @UML datatype MD_Format
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Format {
    /**
     * Name of the data transfer format(s).
     *
     * @UML mandatory name
     */
    InternationalString getName();

    /**
     * Version of the format (date, number, etc.).
     *
     * @UML mandatory version
     */
    InternationalString getVersion();

    /**
     * Amendment number of the format version.
     *
     * @UML optional amendmentNumber
     */
    InternationalString getAmendmentNumber();

    /**
     * Name of a subset, profile, or product specification of the format.
     *
     * @UML optional specification
     */
    InternationalString getSpecification();

    /**
     * Recommendations of algorithms or processes that can be applied to read or
     * expand resources to which compression techniques have been applied.
     *
     * @UML optional fileDecompressionTechnique
     */
    InternationalString getFileDecompressionTechnique();

    /**
     * Provides information about the distributor’s format.
     *
     * @UML optional formatDistributor
     */
    Set/*<Distributor>*/ getFormatDistributors();
}
