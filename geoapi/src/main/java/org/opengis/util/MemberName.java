/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The name to identify a member of a {@linkplain Record record}. This name bears an association
 * with a {@linkplain TypeName type name}. There may be alternate implementations of this: for
 * instance, one implementation may apply to the in-memory model.  Another may apply to a shapefile
 * data store, etc.
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 */
@UML(identifier="MemberName", specification=ISO_19103)
public interface MemberName extends LocalName {
    /**
     * Returns the type of the data associated with the record member.
     *
     * @return The type of the data associated with the record member.
     */
    @UML(identifier="attributeType", obligation=MANDATORY, specification=ISO_19103)
    TypeName getAttributeType();
}
