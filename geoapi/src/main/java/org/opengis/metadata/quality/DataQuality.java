/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.metadata.quality;

import java.util.Collection;
import org.opengis.metadata.lineage.Lineage;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Quality information for the data specified by a data quality scope.
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
     * <div class="warning"><b>Upcoming API change — renaming</b><br>
     * As of ISO 19115:2014, {@code DQ_Scope} (from {@link org.opengis.metadata.quality}) is replaced by
     * {@code MD_Scope} (from {@link org.opengis.metadata.maintenance}).
     * This change may be applied in GeoAPI 4.0.
     * </div>
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
    @Profile(level=CORE)
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
     *
     * @todo Renamed in 19157:2022: {@code QualityEvaluationReport}.
     */
    @UML(identifier="standaloneQualityReport", obligation=OPTIONAL, specification=ISO_19157)
    default StandaloneQualityReportInformation getStandaloneQualityReport() {
        return null;
    }
}
