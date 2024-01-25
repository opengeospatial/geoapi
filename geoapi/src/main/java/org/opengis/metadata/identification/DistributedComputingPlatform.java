/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Distributed computing platform (DCP) on which an operation has been implemented.
 *
 * @departure rename
 *   Renamed from "{@code DCPList}" to "{@code DistributedComputingPlatform}" for the following reasons:
 *   <ol>
 *     <li>GeoAPI avoids the "{@code List}" suffix because instances of this class are not list.
 *         The concept of list rather applies to the list of predefined static constants in this class.</li>
 *     <li>"{@code DCP}" is an abbreviation, and Java usage is to avoid abbreviations unless they are well known.</li>
 *   </ol>
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="DCPList", specification=ISO_19115)
public final class DistributedComputingPlatform extends CodeList<DistributedComputingPlatform> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5092358242686893115L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<DistributedComputingPlatform> VALUES = new ArrayList<>(10);

    /**
     * Extensible Markup Language.
     */
    @UML(identifier="XML", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform XML = new DistributedComputingPlatform("XML");

    /**
     * Common Object Request Broker Architecture.
     */
    @UML(identifier="CORBA", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform CORBA = new DistributedComputingPlatform("CORBA");

    /**
     * Object - oriented programming language.
     */
    @UML(identifier="JAVA", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform JAVA = new DistributedComputingPlatform("JAVA");

    /**
     * Component Object Model.
     */
    @UML(identifier="COM", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform COM = new DistributedComputingPlatform("COM");

    /**
     * Structured Query Language.
     */
    @UML(identifier="SQL", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform SQL = new DistributedComputingPlatform("SQL");

    /**
     * Simple Object Access Protocole.
     */
    @UML(identifier="SOAP", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform SOAP = new DistributedComputingPlatform("SOAP");

    /**
     * ISO 23950.
     */
    @UML(identifier="Z3950", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform Z3950 = new DistributedComputingPlatform("Z3950");

    /**
     * Extensible Markup Language.
     */
    @UML(identifier="HTTP", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform HTTP = new DistributedComputingPlatform("HTTP");

    /**
     * File Transfert Protocol.
     */
    @UML(identifier="FTP", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform FTP = new DistributedComputingPlatform("FTP");

    /**
     * Web Services.
     */
    @UML(identifier="WebServices", obligation=CONDITIONAL, specification=ISO_19115)
    public static final DistributedComputingPlatform WEB_SERVICES = new DistributedComputingPlatform("WEB_SERVICES");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private DistributedComputingPlatform(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code DistributedComputingPlatform}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static DistributedComputingPlatform[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(DistributedComputingPlatform[]::new);
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
    public DistributedComputingPlatform[] family() {
        return values();
    }

    /**
     * Returns the DCP List that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static DistributedComputingPlatform valueOf(String code) {
        return valueOf(DistributedComputingPlatform.class, code);
    }
}
