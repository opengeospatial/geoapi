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

// OpenGIS direct dependencies
import org.opengis.metadata.lineage.Lineage;


/**
 * Quality information for the data specified by a data quality scope.
 *
 * @UML datatype DQ_DataQuality
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface DataQuality {
    /**
     * The specific data to which the data quality information applies.
     *
     * @UML mandatory Scope
     */
    Scope getScope();

    /**
     * Quantitative quality information for the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     *
     * @UML conditional report
     */
    Element[] getReports();

    /**
     * Non-quantitative quality information about the lineage of the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     *
     * @UML conditional lineage
     */
    Lineage getLineage();
}
