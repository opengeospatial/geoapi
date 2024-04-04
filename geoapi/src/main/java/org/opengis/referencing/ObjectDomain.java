/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Scope and domain of validity of a CRS-related object.
 * The scope is a description of the primary purpose to which a coordinate reference system,
 * datum or coordinate system is applied. The domain of validity describes the spatial and
 * temporal extent in which the object can be used.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="ObjectDomain", specification=ISO_19111)
public interface ObjectDomain {
    /**
     * Description of usage, or limitations of usage, for which this object is valid.
     * If unknown, the text should be "not known".
     *
     * @return the domain of usage.
     */
    @UML(identifier="scope", obligation=MANDATORY, specification=ISO_19111)
    InternationalString getScope();

    /**
     * Spatial and temporal extent in which this object is valid.
     *
     * @return the area or time frame of usage.
     */
    @UML(identifier="domainOfValidity", obligation=MANDATORY, specification=ISO_19111)
    Extent getDomainOfValidity();
}
