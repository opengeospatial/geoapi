/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
import java.util.Collections;

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Comprehensive information about the procedure(s), process(es) and algorithm(s) applied
 * in the process step.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="LE_Processing", specification=ISO_19115_2)
public interface Processing {
    /**
     * Information to identify the processing package that produced the data.
     *
     * @return identifier of the processing package that produced the data.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Reference to document describing processing software.
     *
     * @return document describing processing software.
     */
    @UML(identifier="softwareReference", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Citation> getSoftwareReferences() {
        return Collections.emptyList();
    }

    /**
     * Additional details about the processing procedures.
     *
     * @return processing procedures, or {@code null}.
     */
    @UML(identifier="procedureDescription", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getProcedureDescription() {
        return null;
    }

    /**
     * Reference to documentation describing the processing.
     *
     * @return documentation describing the processing.
     */
    @UML(identifier="documentation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Citation> getDocumentations() {
        return Collections.emptyList();
    }

    /**
     * Parameters to control the processing operations, entered at run time.
     *
     * @return parameters to control the processing operations, or {@code null}.
     */
    @UML(identifier="runTimeParameters", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getRunTimeParameters() {
        return null;
    }

    /**
     * Details of the methodology by which geographic information was derived from the
     * instrument readings.
     *
     * @return methodology by which geographic information was derived from the instrument readings.
     */
    @UML(identifier="algorithm", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Algorithm> getAlgorithms() {
        return Collections.emptyList();
    }
}
