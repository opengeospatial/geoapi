/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.metadata.lineage.Lineage;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Quality information for the data specified by a data quality scope.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="DQ_DataQuality")
public interface DataQuality {
    /**
     * The specific data to which the data quality information applies.
     */
/// @UML (identifier="Scope", obligation=MANDATORY)
    Scope getScope();

    /**
     * Quantitative quality information for the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     */
/// @UML (identifier="report", obligation=CONDITIONAL)
    Set/*<Element>*/ getReports();

    /**
     * Non-quantitative quality information about the lineage of the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     */
/// @UML (identifier="lineage", obligation=CONDITIONAL)
    Lineage getLineage();
}
