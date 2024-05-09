/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import org.opengis.referencing.IdentifiedObject;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * An abstract class that represents a non-decomposed element of geometry or topology of time.
 *
 * @author  ISO 19108 (for abstract model and documentation)
 * @author  Stephane Fellah (Image Matters)
 * @author  Alexander Petkov
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Remi Marechal (Geomatys).
 * @version 4.0
 * @since   2.3
 */
@UML(identifier="TM_Primitive", specification=ISO_19108)
public interface TemporalPrimitive extends IdentifiedObject, TemporalObject, TemporalOrder {
}
