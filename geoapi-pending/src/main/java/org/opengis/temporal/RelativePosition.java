/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Specification.*;

/**
 * Values for relative temporal position based on the 13 temporal relationships identified by Allen (1993).
 * Define comparison between two {@link TemporalTopologicalPrimitive}.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (IRD)
 * @since   2.3
 * @version 4.0
 */
@Vocabulary(capacity=13)
@UML(identifier="TM_RelativePosition", specification=ISO_19108)
public final class RelativePosition extends CodeList<RelativePosition> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2918422623747953495L;

    /**
     * This {@link TemporalTopologicalPrimitive} is earlier in the sequence than <i>other</i>
     * and is not linked to <i>other</i> in an <i>Initiation</i> or <i>Termination</i> association.
     */
    public static final RelativePosition BEFORE = new RelativePosition("BEFORE");

    /**
     * This {@link TemporalTopologicalPrimitive} is later in the sequence than <i>other</i>
     * and is not linked to <i>other</i> in an <i>Initiation</i> or <i>Termination</i> association.
     */
    public static final RelativePosition AFTER = new RelativePosition("AFTER");

    /**
     * This two {@link TemporalTopologicalPrimitive} are linked in an <i>Initiation</i> association.
     */
    public static final RelativePosition BEGINS = new RelativePosition("BEGINS");

    /**
     * This two {@link TemporalTopologicalPrimitive} are linked in an <i>Termination</i> association.
     */
    public static final RelativePosition ENDS = new RelativePosition("ENDS");

    public static final RelativePosition DURING = new RelativePosition("DURING");

    /**
     * This {@link TemporalTopologicalPrimitive} and <i>other</i> are the same.
     */
    public static final RelativePosition EQUALS = new RelativePosition("EQUALS");

    public static final RelativePosition CONTAINS = new RelativePosition("CONTAINS");
    public static final RelativePosition OVERLAPS = new RelativePosition("OVERLAPS");

    /**
     * The two {@link TemporalTopologicalPrimitive} are {@link TemporalEdge} associated
     * to the same {@link TemporalNode}, where this {@link TemporalEdge} is linked to the
     * {@link TemporalNode} as a <i>previousEdge</i> in a <i>Termination</i> association,
     * and <i>other</i> is linked to the {@link TemporalNode} as a <i>nextEdge</i> in a
     * <i>Initiation</i> association.
     */
    public static final RelativePosition MEETS = new RelativePosition("MEETS");

    public static final RelativePosition OVERLAPPED_BY = new RelativePosition("OVERLAPPED_BY");

    /**
     * The two {@link TemporalTopologicalPrimitive} are {@link TemporalEdge} associated
     * to the same {@link TemporalNode}, where this {@link TemporalEdge} is linked to the
     * {@link TemporalNode} as a <i>nextEdge</i> in a <i>Initiation</i> association,
     * and <i>other</i> is linked to the {@link TemporalNode} as a <i>previousEdge</i>
     * in a <i>Termination</i> association.
     */
    public static final RelativePosition MET_BY = new RelativePosition("MET_BY");

    /**
     * This {@link TemporalTopologicalPrimitive} is a {@link TemporalEdge},
     * <i>other</i> is a {@link TemporalNode} and these two {@link TemporalTopologicalPrimitive}
     * are linked in an <i>Initiation</i> association.
     */
    public static final RelativePosition BEGUN_BY = new RelativePosition("BEGUN_BY");

    /**
     * This {@link TemporalTopologicalPrimitive} is a {@link TemporalEdge},
     * <i>other</i> is a {@link TemporalNode} and these two {@link TemporalTopologicalPrimitive}
     * are linked in an <i>Termination</i> association.
     */
    public static final RelativePosition ENDED_BY = new RelativePosition("ENDED_BY");

    /**
     * Constructs an element of the given name.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private RelativePosition(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code RelativePosition}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static RelativePosition[] values() {
        return values(RelativePosition.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public RelativePosition[] family() {
        return values();
    }

    /**
     * Returns the relative position that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static RelativePosition valueOf(String code) {
        return valueOf(RelativePosition.class, code, RelativePosition::new).get();
    }
}
