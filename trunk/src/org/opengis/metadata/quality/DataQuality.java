/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// J2SE direct dependencies
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.metadata.lineage.Lineage;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;
import static org.opengis.annotation.Specification.*;


/**
 * Quality information for the data specified by a data quality scope.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="DQ_DataQuality", specification=ISO_19115)
public interface DataQuality {
    /**
     * The specific data to which the data quality information applies.
     */
    @UML(identifier="scope", obligation=MANDATORY, specification=ISO_19115)
    Scope getScope();

    /**
     * Quantitative quality information for the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     */
    @UML(identifier="report", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<Element> getReports();

    /**
     * Non-quantitative quality information about the lineage of the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     */
    @Profile (level=CORE)
    @UML(identifier="lineage", obligation=CONDITIONAL, specification=ISO_19115)
    Lineage getLineage();
}
