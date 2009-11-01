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
package org.opengis.metadata.lineage;

import org.opengis.annotation.UML;

import org.opengis.util.InternationalString;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Report of what occurred during the process step.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="LE_ProcessStepReport", specification=ISO_19115_2)
public interface ProcessStepReport {
    /**
     * Name of the processing report.
     *
     * @return Name of the processing report.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getName();

    /**
     * Textual description of what occurred during the process step.
     *
     * @return What occurred during the process step.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115_2)
    InternationalString getDescription();

    /**
     * Type of file that contains the processing report.
     *
     * @return Type of file that contains the processing report.
     */
    @UML(identifier="fileType", obligation=OPTIONAL, specification=ISO_19115_2)
    InternationalString getFileType();
}
