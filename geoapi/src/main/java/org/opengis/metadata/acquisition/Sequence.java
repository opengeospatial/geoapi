/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.acquisition;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Temporal relation of activation.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_SequenceCode", specification=ISO_19115_2)
public final class Sequence extends CodeList<Sequence> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -3995216796996596661L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Sequence> VALUES = new ArrayList<>(3);

    /**
     * Beginning of a collection.
     */
    @UML(identifier="start", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Sequence START = new Sequence("START");

    /**
     * End of a collection.
     */
    @UML(identifier="end", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Sequence END = new Sequence("END");

    /**
     * Collection without a significant duration
     */
    @UML(identifier="instantaneous", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final Sequence INSTANTANEOUS = new Sequence("INSTANTANEOUS");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Sequence(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Sequence}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Sequence[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(Sequence[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public Sequence[] family() {
        return values();
    }

    /**
     * Returns the sequence that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Sequence valueOf(String code) {
        return valueOf(Sequence.class, code);
    }
}
