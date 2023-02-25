/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.citation;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the series, or aggregate dataset, to which a dataset belongs.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="CI_Series", specification=ISO_19115)
public interface Series {
    /**
     * Name of the series, or aggregate dataset, of which the dataset is a part.
     * Returns {@code null} if none.
     *
     * @return The name of the series or aggregate dataset.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getName();

    /**
     * Information identifying the issue of the series.
     *
     * @return Information identifying the issue of the series.
     */
    @UML(identifier="issueIdentification", obligation=OPTIONAL, specification=ISO_19115)
    String getIssueIdentification();

    /**
     * Details on which pages of the publication the article was published.
     *
     * @return Details on which pages of the publication the article was published.
     */
    @UML(identifier="page", obligation=OPTIONAL, specification=ISO_19115)
    String getPage();
}
