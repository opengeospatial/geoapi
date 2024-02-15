/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.content;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Transform function to be used when scaling a physical value for a given element.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_TransferFunctionTypeCode", specification=ISO_19115_2)
public final class TransferFunctionType extends CodeList<TransferFunctionType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8238532116096874717L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<TransferFunctionType> VALUES = new ArrayList<TransferFunctionType>(3);

    /**
     * Function used for transformation is first order polynomial.
     */
    @UML(identifier="linear", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final TransferFunctionType LINEAR = new TransferFunctionType("LINEAR");

    /**
     * Function used for transformation is logarithmic
     */
    @UML(identifier="logarithmic", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final TransferFunctionType LOGARITHMIC = new TransferFunctionType("LOGARITHMIC");

    /**
     * Function used for transformation is exponential.
     */
    @UML(identifier="exponential", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final TransferFunctionType EXPONENTIAL = new TransferFunctionType("EXPONENTIAL");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by another enum of this type.
     */
    private TransferFunctionType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code TransferFunctionType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static TransferFunctionType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new TransferFunctionType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind as this enum.
     */
    public TransferFunctionType[] family() {
        return values();
    }

    /**
     * Returns the transfer function type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static TransferFunctionType valueOf(String code) {
        return valueOf(TransferFunctionType.class, code);
    }
}
