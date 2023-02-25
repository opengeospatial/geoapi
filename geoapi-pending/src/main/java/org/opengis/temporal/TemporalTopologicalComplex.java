/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import java.util.Collection;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import org.opengis.referencing.IdentifiedObject;

/**
 * An aggregation of connected {@linkplain TemporalTopologicalPrimitive temporal topological
 * primitives}. This is the only subclass of {@linkplain TemporalComplex temporal complex}.
 *
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_TopologicalComplex", specification=ISO_19108)
public interface TemporalTopologicalComplex extends TemporalComplex, IdentifiedObject {
    /**
     * Returns aggregation of connected {@linkplain TemporalTopologicalPrimitive temporal topological
     * primitives}.
     *
     * @return aggregation of connected {@linkplain TemporalTopologicalPrimitive temporal topological
     * primitives}.
     */
    @UML(identifier="primitive", obligation=MANDATORY, specification=ISO_19108)
    Collection<TemporalTopologicalPrimitive> getTemporalTopologicalPrimitives();
}
