/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.acquisition;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Code indicating whether the data contained in this packet is real (originates from live-fly
 * or other non-simulated operational sources), simulated (originates from target simulator sources),
 * or synthesized (a mix of real and simulated data).
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_OperationTypeCode", specification=ISO_19115_2)
public final class OperationType extends CodeList<OperationType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4952647967684867284L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<OperationType> VALUES = new ArrayList<>(3);

    /**
     * Originates from live-fly or other non-simulated operational source.
     */
    @UML(identifier="real", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final OperationType REAL = new OperationType("REAL");

    /**
     * Originates from target simulator sources.
     */
    @UML(identifier="simulated", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final OperationType SIMULATED = new OperationType("SIMULATED");

    /**
     * Mix of real and simulated data.
     */
    @UML(identifier="synthesized", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final OperationType SYNTHESIZED = new OperationType("SYNTHESIZED");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private OperationType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code OperationType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static OperationType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new OperationType[VALUES.size()]);
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
    public OperationType[] family() {
        return values();
    }

    /**
     * Returns the operation type that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static OperationType valueOf(String code) {
        return valueOf(OperationType.class, code);
    }
}
