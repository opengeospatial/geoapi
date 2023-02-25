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
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of capabilities which a service provider makes available to a service user
 * through a set of interfaces that define a behaviour - See ISO 19119 for further information.
 *
 * <BLOCKQUOTE><FONT SIZE=-1><B>NOTE:</B>
 * The prefix was {@code MD} in a previous ISO 19115 specification, but
 * has been renamed {@code SV} in corrigendum 2006.</FONT></BLOCKQUOTE>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="SV_ServiceIdentification", specification=ISO_19115)
public interface ServiceIdentification extends Identification {
}
