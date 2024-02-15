/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.citation;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Class of information to which the referencing entity applies.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="CI_OnLineFunctionCode", specification=ISO_19115)
public final class OnLineFunction extends CodeList<OnLineFunction> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2333803519583053407L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<OnLineFunction> VALUES = new ArrayList<OnLineFunction>(5);

    /**
     * Online instructions for transferring data from one storage device or system to another.
     */
    @UML(identifier="download", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction DOWNLOAD = new OnLineFunction("DOWNLOAD");

    /**
     * Online information about the resource.
     */
    @UML(identifier="information", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction INFORMATION = new OnLineFunction("INFORMATION");

    /**
     * Online instructions for requesting the resource from the provider.
     */
    @UML(identifier="offlineAccess", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction OFFLINE_ACCESS = new OnLineFunction("OFFLINE_ACCESS");

    /**
     * Online order process for obtaining the resource.
     */
    @UML(identifier="order", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction ORDER = new OnLineFunction("ORDER");

    /**
     * Online search interface for seeking out information about the resource.
     */
    @UML(identifier="search", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction SEARCH = new OnLineFunction("SEARCH");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by another enum of this type.
     */
    private OnLineFunction(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code OnLineFunction}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static OnLineFunction[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new OnLineFunction[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind as this enum.
     */
    public OnLineFunction[] family() {
        return values();
    }

    /**
     * Returns the on line function that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static OnLineFunction valueOf(String code) {
        return valueOf(OnLineFunction.class, code);
    }
}
