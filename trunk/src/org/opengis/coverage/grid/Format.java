/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// OpenGIS dependencies
import org.opengis.parameter.ParameterValueGroup;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A discovery mechanism to determine the formats supported by a {@link GridCoverageExchange}
 * implementation. A <code>GridCoverageExchange</code> implementation can support a number of
 * file format or resources.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 */
///@UML (identifier="CV_Format")
public interface Format {
    /**
     * Name of the file format.
     */
/// @UML (identifier="name", obligation=MANDATORY)
    String getName();

    /**
     * Description of the file format.
     * If no description, the value will be <code>null</code>.
     */
/// @UML (identifier="description", obligation=OPTIONAL)
    String getDescription();

    /**
     * Vendor or agency for the format.
     */
/// @UML (identifier="vendor", obligation=OPTIONAL)
    String getVendor();

    /**
     * Documentation URL for the format.
     */
/// @UML (identifier="docURL", obligation=OPTIONAL)
    String getDocURL();

    /**
     * Version number of the format.
     */
/// @UML (identifier="version", obligation=OPTIONAL)
    String getVersion();

    /**
     * Retrieve the parameter information for a {@link GridCoverageReader#read read} operation.
     */
/// @UML (identifier="getParameterInfo, numParameters", obligation=MANDATORY)
    ParameterValueGroup getReadParameters();

    /**
     * Retrieve the parameter information for a {@link GridCoverageWriter#write write} operation.
     */
/// @UML (identifier="getParameterInfo, numParameters", obligation=MANDATORY)
    ParameterValueGroup getWriteParameters();
}
