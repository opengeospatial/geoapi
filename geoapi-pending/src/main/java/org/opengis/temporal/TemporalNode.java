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
 * A zero dimensional topological primitive in time.
 *
 * @author Alexander Petkov
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_Node", specification=ISO_19108)
public interface TemporalNode extends TemporalTopologicalPrimitive {
    
    /**
     * Returns an optional association that may link this {@link TemporalNode}
     * to its corresponding {@link Instant}.
     * 
     * @return associated {@link Instant}.
     */
    @UML(identifier="Realization", obligation=OPTIONAL, specification=ISO_19108)
    Instant getRealization();

    /**
     * Returns association that links this {@link TemporalNode} to the <i>previous</i> {@link TemporalEdge}.
     * In other word, link this {@link TemporalNode} to the {@link TemporalEdge} for which is the end.
     * 
     * @return association that links this {@link TemporalNode} to the <i>previous</i> {@link TemporalEdge}.
     */
    @UML(identifier="previousEdge", obligation=MANDATORY, specification=ISO_19108)
    TemporalEdge getPreviousEdge();

    /**
     * Returns association that links this {@link TemporalNode} to the <i>next</i> {@link TemporalEdge}. 
     * In other word, link this {@link TemporalNode} to the {@link TemporalEdge} for which is the start.
     * 
     * @return association that links this {@link TemporalNode} to the <i>next</i> {@link TemporalEdge}.
     */
    @UML(identifier="nextEdge", obligation=MANDATORY, specification=ISO_19108)
    TemporalEdge getNextEdge();
}
