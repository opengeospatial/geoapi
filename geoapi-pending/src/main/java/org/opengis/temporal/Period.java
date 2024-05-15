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

import java.time.Instant;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A one-dimensional geometric primitive that represent extent in time.
 * It is an open interval bounded by beginning and ending points({@link Instant}).
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 */
 @UML(identifier="TM_Period", specification=ISO_19108)
public interface Period extends TemporalGeometricPrimitive {

     /**
      * Returns the beginning {@link Instant} at which this {@link Period} starts.
      *
      * @return the beginning {@link Instant} at which this {@link Period} starts.
      */
    @UML(identifier="Beginning", obligation=MANDATORY, specification=ISO_19108)
    Instant getBeginning();

    /**
      * Returns the ending {@link Instant} at which this {@link Period} ends.
      *
      * @return the ending {@link Instant} at which this {@link Period} ends.
      */
    @UML(identifier="Ending", obligation=MANDATORY, specification=ISO_19108)
    Instant getEnding();
}
