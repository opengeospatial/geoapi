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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * One-dimensional topological primitive in time.
 *
 * @author Alexander Petkov
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_Edge", specification=ISO_19108)
public interface TemporalEdge extends TemporalTopologicalPrimitive {
    
    /**
     * Returns an optional association that links this {@link TemporalEdge} to the corresponding {@link Period}.
     * 
     * @return an optional association that links this {@link TemporalEdge} to the corresponding {@link Period}.
     */
    @UML(identifier="Realization", obligation=OPTIONAL, specification=ISO_19108)
    Period getRealization();

    /**
     * Returns association that links this {@link TemporalEdge} to the {@link TemporalNode} that is its start.
     * The {@link TemporalEdge} 
     * 
     * @return association that links this {@link TemporalEdge} to the {@link TemporalNode} that is its start.
     */
    @UML(identifier="start", obligation=MANDATORY, specification=ISO_19108)
    TemporalNode getStart();

    /**
     * Returns association that links this {@link TemporalEdge} to the {@link TemporalNode} that is its ends.
     * 
     * @return association that links this {@link TemporalEdge} to the {@link TemporalNode} that is its ends.
     */
    @UML(identifier="end", obligation=MANDATORY, specification=ISO_19108)
    TemporalNode getEnd();
}
