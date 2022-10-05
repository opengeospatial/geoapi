/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import java.util.Collection;
import org.opengis.metadata.lineage.Lineage;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Quality information for the data specified by a data quality scope.
 * At least one of the {@linkplain #getReports() report} and {@linkplain #getLineage() lineage}
 * shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_DataQuality", specification=ISO_19157)
public interface DataQuality {
    /**
     * The specific data to which the data quality information applies.
     * The scope specifies the extent, spatial and/or temporal, and/or common characteristic(s)
     * that identify the data on which data quality is to be evaluated.
     * Examples:
     * <ul>
     *   <li>a data set series;</li>
     *   <li>a data set;</li>
     *   <li>a subset of data defined by one or more of the following characteristics:
     *     <ul>
     *       <li>types of items (sets of feature types);</li>
     *       <li>specific items (sets of feature instances);</li>
     *       <li>geographic extent;</li>
     *       <li>temporal extent.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the specific data to which the data quality information applies.
     */
    @UML(identifier="scope", obligation=MANDATORY, specification=ISO_19157)
    Scope getScope();

    /**
     * Quality information for the data specified by the scope.
     * The quality of a data set can be measured using a variety of methods;
     * a single data quality measure might be insufficient for fully evaluating
     * the quality of the data specified by the {@linkplain #getScope() scope}.
     * Therefore multiple data quality measures may be reported.
     * The data quality report should then include one instance of {@link Element} for each measure applied.
     *
     * @return quality information for the data specified by the scope.
     */
    @UML(identifier="report", obligation=MANDATORY, specification=ISO_19157)
    Collection<? extends Element> getReports();

    /**
     * Non-quantitative quality information about the lineage of the data specified by the scope.
     *
     * @return non-quantitative quality information about the lineage of the data specified,
     *         or {@code null}.
     *
     * @deprecated Removed from ISO 19157:2013.
     */
    @Deprecated
    @UML(identifier="lineage", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default Lineage getLineage() {
        return null;
    }

    /**
     * Reference to an external standalone quality report.
     * Can be used for providing more details than reported as standard metadata.
     *
     * @return reference to an external standalone quality report, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="standaloneQualityReport", obligation=OPTIONAL, specification=ISO_19157)
    default StandaloneQualityReportInformation getStandaloneQualityReport() {
        return null;
    }
}
