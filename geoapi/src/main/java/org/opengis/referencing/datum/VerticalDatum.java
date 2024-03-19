/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.datum;

import java.util.Map;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of a particular reference level surface used as a zero-height surface.
 * The description includes its position with respect to the Earth for any of the height
 * types recognized by the ISO 19111 standard.
 *
 * <p>There are several types of vertical reference frames, and each may place constraints
 * on the {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis Coordinate Axis} with which
 * it is combined to create a {@linkplain org.opengis.referencing.crs.VerticalCRS Vertical CRS}.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.0
 * @since   1.0
 *
 * @see DatumAuthorityFactory#createVerticalDatum(String)
 * @see DatumFactory#createVerticalDatum(Map, VerticalDatumType)
 */
@UML(identifier="CD_VerticalDatum", specification=ISO_19111, version=2007)
public interface VerticalDatum extends Datum {
    /**
     * The type of this vertical datum.
     *
     * @todo Renamed {@code RealizationMethod} in ISO 19111:2019.
     *
     * @return the type of this vertical datum.
     *
     * @see #getAnchorPoint()
     */
    @UML(identifier="vertDatumType", obligation=MANDATORY, specification=ISO_19111)
    VerticalDatumType getVerticalDatumType();
}
