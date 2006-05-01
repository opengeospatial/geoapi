/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

// OpenGIS dependencies
import org.opengis.parameter.ParameterValueGroup;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A discovery mechanism to determine the formats supported by a {@link GridCoverageExchange}
 * implementation. A {@code GridCoverageExchange} implementation can support a number of
 * file format or resources.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much 
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="CV_Format", specification=OGC_01004)
public interface Format {
    /**
     * Name of the file format.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=OGC_01004)
    String getName();

    /**
     * Description of the file format.
     * If no description, the value will be {@code null}.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=OGC_01004)
    String getDescription();

    /**
     * Vendor or agency for the format.
     */
    @UML(identifier="vendor", obligation=OPTIONAL, specification=OGC_01004)
    String getVendor();

    /**
     * Documentation URL for the format.
     */
    @UML(identifier="docURL", obligation=OPTIONAL, specification=OGC_01004)
    String getDocURL();

    /**
     * Version number of the format.
     */
    @UML(identifier="version", obligation=OPTIONAL, specification=OGC_01004)
    String getVersion();

    /**
     * Retrieve the parameter information for a {@link GridCoverageReader#read read} operation.
     */
    @UML(identifier="getParameterInfo, numParameters", obligation=MANDATORY, specification=OGC_01004)
    ParameterValueGroup getReadParameters();

    /**
     * Retrieve the parameter information for a {@link GridCoverageWriter#write write} operation.
     */
    @UML(identifier="getParameterInfo, numParameters", obligation=MANDATORY, specification=OGC_01004)
    ParameterValueGroup getWriteParameters();
}
