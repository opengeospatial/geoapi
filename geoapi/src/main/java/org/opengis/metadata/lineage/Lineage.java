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
package org.opengis.metadata.lineage;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about the events or source data used in constructing the data specified by
 * the scope or lack of knowledge about lineage.
 * At least one of {@linkplain #getStatement() statement}, {@linkplain #getProcessSteps() process steps}
 * and {@linkplain #getSources() sources} shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="LI_Lineage", specification=ISO_19115)
public interface Lineage {
    /**
     * General explanation of the data producer's knowledge about the lineage of a dataset.
     *
     * @return explanation of the data producer's knowledge about the lineage, or {@code null}.
     *
     * @condition Shall be provided only if {@linkplain Scope#getLevel() scope level} is
     *            {@link ScopeCode#DATASET} or {@link ScopeCode#SERIES}.
     */
    @Profile(level=CORE)
    @UML(identifier="statement", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getStatement();

    /**
     * Type of resource and / or extent to which the lineage information applies.
     *
     * @return type of resource and / or extent, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19115)
    Scope getScope();

    /**
     * Additional documentation.
     *
     * <div class="note"><b>Example</b>
     * a publication that describes the whole process to generate this resource (for example a dataset).
     * </div>
     *
     * @return additional documentation.
     *
     * @since 3.1
     */
    @UML(identifier="additionalDocumentation", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Citation> getAdditionalDocumentation();

    /**
     * Information about events in the life of a resource specified by the scope.
     *
     * @return information about events in the life of a resource.
     *
     * @condition Mandatory if {@linkplain #getStatement() statement} and
     *            {@linkplain #getSources() source} are not provided.
     */
    @UML(identifier="processStep", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends ProcessStep> getProcessSteps();

    /**
     * Information about the source data used in creating the data specified by the scope.
     *
     * @return information about the source data.
     *
     * @condition Mandatory if {@linkplain #getStatement() statement} and
     *            {@linkplain #getProcessSteps() process step} are not provided.
     */
    @UML(identifier="source", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Source> getSources();
}
