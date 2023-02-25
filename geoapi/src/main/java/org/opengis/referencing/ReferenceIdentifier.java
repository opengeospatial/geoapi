/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Specification.*;


/**
 * Identifier used for reference systems.
 *
 * <div class="warning"><b>Upcoming API change — deprecation</b><br>
 * As of ISO 19115:2014, the {@code RS_Identifier} type has been merged with its {@code MD_Identifier} parent type.
 * Consequently in GeoAPI 4.0, all usage of this {@code ReferenceIdentifier} interface will be replaced by usage of
 * {@link Identifier}, and this {@code ReferenceIdentifier} interface will be deprecated.
 * </div>
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.1
 * @since   2.1
 */
@UML(identifier="RS_Identifier", specification=ISO_19115, version=2003)
public interface ReferenceIdentifier extends Identifier {
}
