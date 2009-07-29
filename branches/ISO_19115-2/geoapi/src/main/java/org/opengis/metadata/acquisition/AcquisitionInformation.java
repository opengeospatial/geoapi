/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.acquisition;

import java.util.Collection;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the measuring instruments, the platform carrying them, and the mission to
 * which the data contributes.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_AcquisitionInformation", specification=ISO_19115_2)
public interface AcquisitionInformation {
    /**
     * Identifies the plan as implemented by the acquisition.
     *
     * @return Plan as implemented by the acquisition.
     */
    @UML(identifier="acquisitionPlan", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Plan> getAcquisitionPlans();

    /**
     * Identifies the requirement the data acquisition intends to satisfy.
     *
     * @return Requirement the data acquisition intends to satisfy.
     */
    @UML(identifier="acquisitionRequirement", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Requirement> getAcquisitionRequirements();

    /**
     * A record of the environmental circumstances during the data acquisition.
     *
     * @return Record of the environmental circumstances.
     */
    @UML(identifier="environmentalConditions", obligation=OPTIONAL, specification=ISO_19115_2)
    EnvironmentalRecord getEnvironmentalConditions();

    /**
     * General information about the instrument used in data acquisition.
     *
     * @return Instrument used in data acquisition.
     */
    @UML(identifier="instrument", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Instrument> getInstruments();

    /**
     * Identification of the area or object to be sensed.
     *
     * @return Area or object to be sensed.
     */
    @UML(identifier="objective", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Objective> getObjectives();

    /**
     * General information about an identifiable activity which provided the data.
     *
     * @return Identifiable activity which provided the data.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Operation> getOperations();

    /**
     * General information about the platform from which the data were taken.
     *
     * @return Platform from which the data were taken.
     */
    @UML(identifier="platform", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Platform> getPlatforms();
}
