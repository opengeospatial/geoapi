/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.acquisition;

import java.util.Collection;
import java.util.Collections;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the measuring instruments, the platform carrying them, and the mission to
 * which the data contributes.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_AcquisitionInformation", specification=ISO_19115_2)
public interface AcquisitionInformation {
    /**
     * Identifies the plan as implemented by the acquisition.
     *
     * @return plan as implemented by the acquisition.
     */
    @UML(identifier="acquisitionPlan", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Plan> getAcquisitionPlans() {
        return Collections.emptyList();
    }

    /**
     * Identifies the requirement the data acquisition intends to satisfy.
     *
     * @return requirement the data acquisition intends to satisfy.
     */
    @UML(identifier="acquisitionRequirement", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Requirement> getAcquisitionRequirements() {
        return Collections.emptyList();
    }

    /**
     * A record of the environmental circumstances during the data acquisition.
     *
     * @return record of the environmental circumstances, or {@code null}.
     */
    @UML(identifier="environmentalConditions", obligation=OPTIONAL, specification=ISO_19115_2)
    default EnvironmentalRecord getEnvironmentalConditions() {
        return null;
    }

    /**
     * General information about the instrument used in data acquisition.
     *
     * @return instrument used in data acquisition.
     */
    @UML(identifier="instrument", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Instrument> getInstruments() {
        return Collections.emptyList();
    }

    /**
     * Identification of the area or object to be sensed.
     *
     * @return area or object to be sensed.
     */
    @UML(identifier="objective", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Objective> getObjectives() {
        return Collections.emptyList();
    }

    /**
     * General information about an identifiable activity which provided the data.
     *
     * @return identifiable activity which provided the data.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Operation> getOperations() {
        return Collections.emptyList();
    }

    /**
     * General information about the platform from which the data were taken.
     *
     * @return platform from which the data were taken.
     */
    @UML(identifier="platform", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Platform> getPlatforms() {
        return Collections.emptyList();
    }
}
