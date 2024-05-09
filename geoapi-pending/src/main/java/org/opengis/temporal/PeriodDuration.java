/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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

import java.time.temporal.TemporalAmount;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;

/**
 * @deprecated Replaced by {@link TemporalAmount} from the standard Java API.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @since   2.3
 * @version 3.1
 */
@Deprecated(since = "3.1", forRemoval = true)
@UML(identifier="TM_PeriodDuration", specification=ISO_19108)
public interface PeriodDuration extends TemporalAmount {
}
