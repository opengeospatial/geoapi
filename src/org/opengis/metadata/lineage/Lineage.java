/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.lineage;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;


/**
 * Information about the events or source data used in constructing the data specified by
 * the scope or lack of knowledge about lineage.
 *
 * Only one of {@linkplain #getStatement statement}, {@linkplain #getProcessSteps process steps}
 * and {@link #getSources sources} should be provided.
 *
 * @UML datatype LI_Lineage
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Lineage {
    /**
     * General explanation of the data producer’s knowledge about the lineage of a dataset.
     * Should be provided only if
     * {@linkplain org.opengis.metadata.quality.Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset} or
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#SERIES series}.
     *
     * @UML conditional statement
     */
    InternationalString getStatement();

    /**
     * Information about an event in the creation process for the data specified by the scope.
     *
     * @UML conditional processStep
     */
    Set/*<ProcessStep>*/ getProcessSteps();

    /**
     * Information about the source data used in creating the data specified by the scope.
     *
     * @UML conditional source
     */
    Set/*<Source>*/ getSources();
}
