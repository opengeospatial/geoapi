/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter;

import java.time.Instant;
import java.util.OptionalInt;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * The version of a resource to select.
 * Exactly one of {@linkplain #getAction() action}, {@linkplain #getIndex() index}
 * and {@linkplain #getTimeStamp() time stamp} properties shall be provided.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.UNION)
@UML(identifier="Version", specification=ISO_19143)
public interface Version {
    /**
     * Indices a version relative to the identified resources.
     *
     * @return action to do for selecting a version relative to the identified resource.
     */
    @UML(identifier="enum", obligation=CONDITIONAL, specification=ISO_19143)
    default VersionAction getAction() {
        return null;
    }

    /**
     * Indicates that the <var>N</var><sup>th</sup> version of the resource shall be selected.
     * The first version of a resource shall be numbered 1.
     * If <var>N</var> exceeds the number of versions available,
     * the latest version of the resource shall be selected.
     *
     * @return index (starting at 1) of the version to select.
     */
    @UML(identifier="index", obligation=CONDITIONAL, specification=ISO_19143)
    default OptionalInt getIndex() {
        return OptionalInt.empty();
    }

    /**
     * Indicates that the version of the resource closest to the specified date shall be selected.
     *
     * @return date and time of the resource to select (nearest neighbor).
     */
    @UML(identifier="timeStamp", obligation=CONDITIONAL, specification=ISO_19143)
    default Instant getTimeStamp() {
        return null;
    }
}
