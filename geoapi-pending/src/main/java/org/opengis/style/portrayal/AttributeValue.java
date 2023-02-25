/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.style.portrayal;


import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The AttributeValue holds the value for an associated AttributeDefinition.
 *
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_AttributeValue", specification=ISO_19117)
public interface AttributeValue {
    /**
     * Gets the value for the associated AttributeDefinition.
     * This object shall have the same Class as defined in the AttributeDefinition.
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19117)
    Object getValue();

    /**
     * Gets the associated AttributeDefinition.
     */
    @UML(identifier="attributeType", obligation=MANDATORY, specification=ISO_19117)
    AttributeDefinition getDefinition();
}
