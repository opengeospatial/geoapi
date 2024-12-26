/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Transform function to be used when scaling a physical value for a given element.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@Vocabulary(capacity=3)
@UML(identifier="MI_TransferFunctionTypeCode", specification=ISO_19115_2)
public final class TransferFunctionType extends CodeList<TransferFunctionType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8238532116096874717L;

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
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private TransferFunctionType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code TransferFunctionType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static TransferFunctionType[] values() {
        return values(TransferFunctionType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public TransferFunctionType[] family() {
        return values();
    }

    /**
     * Returns the transfer function type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static TransferFunctionType valueOf(String code) {
        return valueOf(TransferFunctionType.class, code, TransferFunctionType::new).get();
    }
}
