/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/lineage/Lineage.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.lineage;

// J2SE direct dependencies
import static org.opengis.annotation.ComplianceLevel.CORE;
import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;

import org.opengis.annotation.Profile;
import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;
import org.opengis.util.InternationalString;


/**
 * Information about the events or source data used in constructing the data specified by
 * the scope or lack of knowledge about lineage.
 *
 * Only one of {@linkplain #getStatement statement}, {@linkplain #getProcessSteps process steps}
 * and {@linkplain #getSources sources} should be provided.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="LI_Lineage", specification=ISO_19115)
public interface Lineage extends MetadataEntity{
    /**
     * General explanation of the data producer’s knowledge about the lineage of a dataset.
     * Should be provided only if
     * {@linkplain org.opengis.metadata.quality.Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset} or
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#SERIES series}.
     */
    @Profile (level=CORE)
    @UML(identifier="statement", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getStatement();

    /**
     * Information about an event in the creation process for the data specified by the scope.
     */
    @UML(identifier="processStep", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<ProcessStep> getProcessSteps();

    /**
     * Information about the source data used in creating the data specified by the scope.
     */
    @UML(identifier="source", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<Source> getSources();
}
