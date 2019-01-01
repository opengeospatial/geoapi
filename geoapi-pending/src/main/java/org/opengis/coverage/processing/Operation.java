/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.coverage.processing;

import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * This interface provides descriptive information for a grid coverage processing
 * operation. The descriptive information includes such information as the name of
 * the operation, operation description, number of source grid coverages required
 * for the operation etc.
 *
 * <div class="warning"><b>Warning — this class will change</b><br>
 * Current API is derived from OGC <a href="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</a>.
 * We plan to replace it by new interfaces derived from ISO 19123 (<cite>Schema for coverage geometry
 * and functions</cite>). Current interfaces should be considered as legacy and are included in this
 * distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much
 * compatibility as possible, but no migration plan has been determined yet.
 * </div>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 *
 * @todo This interface should be renamed {@code CoverageOperation}.
 */
@UML(identifier="CV_Operation", specification=OGC_01004)
public interface Operation {
    /**
     * Name of the processing operation. This name is passed as a parameter
     * {@link GridCoverageProcessor} to instantiate
     * a new grid coverage on which the processing operation is performed.
     *
     * @return the name of the processing operation.
     *
     * @todo The return type will be changed from {@link String} to {@link Identifier}.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=OGC_01004)
    String getName();

    /**
     * Description of the processing operation.
     * If no description is available, the value will be {@code null}.
     *
     * @return the description of the processing operation, or {@code null}.
     *
     * @deprecated Return type need to be changed, maybe to {@link InternationalString}.
     */
    @Deprecated
    @UML(identifier="description", obligation=OPTIONAL, specification=OGC_01004)
    String getDescription();

    /**
     * Vendor of the processing operation implementation.
     * If no vendor name is available, the value will be {@code null}.
     *
     * @return the implementation vendor name, or {@code null}.
     *
     * @deprecated To be replaced by {@code getName().getAuthority()}.
     */
    @Deprecated
    @UML(identifier="vendor", obligation=OPTIONAL, specification=OGC_01004)
    String getVendor();

    /**
     * URL for documentation on the processing operation.
     * If no online documentation is available the string will be {@code null}.
     *
     * @return the URL for documentation on the processing operation, or {@code null}.
     *
     * @deprecated To be replaced by a method returning a {@link Citation}.
     */
    @Deprecated
    @UML(identifier="docURL", obligation=OPTIONAL, specification=OGC_01004)
    String getDocURL();

    /**
     * Version number for the implementation.
     *
     * @return the version number for the implementation, or {@code null}.
     *
     * @deprecated Replacement to be determined.
     */
    @Deprecated
    @UML(identifier="version", obligation=OPTIONAL, specification=OGC_01004)
    String getVersion();

    /**
     * Number of source grid coverages required for the operation.
     *
     * @return the number of source grid coverages required for the operation.
     */
    @UML(identifier="numSources", obligation=OPTIONAL, specification=OGC_01004)
    int getNumSources();

    /**
     * Retrieve the parameters information.
     *
     * @return the parameter informations.
     */
    @UML(identifier="getParameterInfo, numParameters", obligation=MANDATORY, specification=OGC_01004)
    ParameterValueGroup getParameters();
}
