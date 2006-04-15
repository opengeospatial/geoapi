/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.processing;

// OpenGIS dependencies
import org.opengis.parameter.ParameterValueGroup;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * This interface provides descriptive information for a grid coverage processing
 * operation. The descriptive information includes such information as the name of
 * the operation, operation description, number of source grid coverages required
 * for the operation etc.
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
 * @since GeoAPI 1.0
 */
@UML(identifier="CV_Operation", specification=OGC_01004)
public interface Operation {
    /**
     * Name of the processing operation. This name is passed as a parameter to the
     * {@link GridCoverageProcessor#doOperation doOperation} method to instantiate
     * a new grid coverage on which the processing operation is performed.
     *
     * @return The name of the processing operation.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=OGC_01004)
    String getName();

    /**
     * Description of the processing operation.
     * If no description is available, the value will be {@code null}.
     *
     * @return The description of the processing operation, or {@code null}.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=OGC_01004)
    String getDescription();

    /**
     * Vendor of the processing operation implementation.
     * If no vendor name is available, the value will be {@code null}.
     *
     * @return The implementation vendor name, or {@code null}.
     */
    @UML(identifier="vendor", obligation=OPTIONAL, specification=OGC_01004)
    String getVendor();

    /**
     * URL for documentation on the processing operation.
     * If no online documentation is available the string will be {@code null}.
     *
     * @return The URL for documentation on the processing operation, or {@code null}.
     */
    @UML(identifier="docURL", obligation=OPTIONAL, specification=OGC_01004)
    String getDocURL();

    /**
     * Version number for the implementation.
     *
     * @return The version number for the implementation, or {@code null}.
     */
    @UML(identifier="version", obligation=OPTIONAL, specification=OGC_01004)
    String getVersion();

    /**
     * Number of source grid coverages required for the operation.
     *
     * @return The number of source grid coverages required for the operation.
     */
    @UML(identifier="numSources", obligation=OPTIONAL, specification=OGC_01004)
    int getNumSources();

    /**
     * Retrieve the parameters information.
     *
     * @return The parameter informations.
     */
    @UML(identifier="getParameterInfo, numParameters", obligation=MANDATORY, specification=OGC_01004)
    ParameterValueGroup getParameters();
}
