/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.observation.sampling;

import org.opengis.util.GenericName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A SamplingFeatureRelation association class carries a source, target and role.
 * The source is not specified in the class since it's the source object which hold the
 * relations instances.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="SamplingFeatureRelation", specification=OGC_07022)
public interface SamplingFeatureRelation {

    @UML(identifier="role", obligation=MANDATORY, specification=OGC_07022)
    GenericName getRole();

    @UML(identifier="target", obligation=MANDATORY, specification=OGC_07022)
    SamplingFeature getTarget();
    
}
