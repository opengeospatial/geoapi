/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
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
    private static final List<OnLineFunction> VALUES = new ArrayList<>(11);

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
     * Complete metadata provided.
     *
     * @since 3.1
     */
    @UML(identifier="completeMetadata", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction COMPLETE_METADATA = new OnLineFunction("COMPLETE_METADATA");

    /**
     * Browse graphic provided.
     *
     * @since 3.1
     */
    @UML(identifier="browseGraphic", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction BROWSE_GRAPHIC = new OnLineFunction("BROWSE_GRAPHIC");

    /**
     * Online resource upload capability provided.
     *
     * @since 3.1
     */
    @UML(identifier="upload", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction UPLOAD = new OnLineFunction("UPLOAD");

    /**
     * Online email service provided.
     *
     * @since 3.1
     */
    @UML(identifier="emailService", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction EMAIL_SERVICE = new OnLineFunction("EMAIL_SERVICE");

    /**
     * Online browsing provided.
     *
     * @since 3.1
     */
    @UML(identifier="browsing", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction BROWSING = new OnLineFunction("BROWSING");

    /**
     * Online file access provided.
     *
     * @since 3.1
     */
    @UML(identifier="fileAccess", obligation=CONDITIONAL, specification=ISO_19115)
    public static final OnLineFunction FILE_ACCESS = new OnLineFunction("FILE_ACCESS");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private OnLineFunction(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code OnLineFunction}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static OnLineFunction[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(OnLineFunction[]::new);
        }
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public OnLineFunction[] family() {
        return values();
    }

    /**
     * Returns the on line function that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static OnLineFunction valueOf(String code) {
        return valueOf(OnLineFunction.class, code);
    }
}
