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
package org.opengis.metadata.constraint;

import java.util.List;
import java.util.ArrayList;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Limitation(s) placed upon the access or use of the data.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_RestrictionCode", specification=ISO_19115)
public final class Restriction extends CodeList<Restriction> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7949159742645339894L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Restriction> VALUES = new ArrayList<>(17);

    /**
     * Exclusive right to the publication, production, or sale of the rights to a literary,
     * dramatic, musical, or artistic work, or to the use of a commercial print or label,
     * granted by law for a specified period of time to an author, composer, artist, distributor.
     */
    @UML(identifier="copyright", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction COPYRIGHT = new Restriction("COPYRIGHT");

    /**
     * Government has granted exclusive right to make, sell, use or license an invention
     * or discovery.
     */
    @UML(identifier="patent", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction PATENT = new Restriction("PATENT");

    /**
     * Produced or sold information awaiting a patent.
     */
    @UML(identifier="patentPending", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction PATENT_PENDING = new Restriction("PATENT_PENDING");

    /**
     * A name, symbol, or other device identifying a product, officially registered and
     * legally restricted to the use of the owner or manufacturer.
     */
    @UML(identifier="trademark", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction TRADEMARK = new Restriction("TRADEMARK");

    /**
     * Formal permission to do something.
     *
     * @since 3.1
     */
    @UML(identifier="licence", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction LICENCE = new Restriction("LICENCE");

    /**
     * Formal permission to do something (ISO 19115:2003 spelling).
     *
     * @deprecated As of ISO 19115:2014, renamed {@link #LICENCE}.
     */
    @Deprecated
    @UML(identifier="license", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final Restriction LICENSE = LICENCE;

    /**
     * Rights to financial benefit from and control of distribution of non-tangible property
     * that is a result of creativity.
     */
    @UML(identifier="intellectualPropertyRights", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction INTELLECTUAL_PROPERTY_RIGHTS = new Restriction("INTELLECTUAL_PROPERTY_RIGHTS");

    /**
     * Withheld from general circulation or disclosure.
     */
    @UML(identifier="restricted", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction RESTRICTED = new Restriction("RESTRICTED");

    /**
     * Limitation not listed.
     */
    @UML(identifier="otherRestrictions", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction OTHER_RESTRICTIONS = new Restriction("OTHER_RESTRICTIONS");

    /**
     * No constraints exist.
     *
     * @since 3.1
     */
    @UML(identifier="unrestricted", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction UNRESTRICTED = new Restriction("UNRESTRICTED");

    /**
     * Formal permission not required to use the resource.
     *
     * @since 3.1
     */
    @UML(identifier="licenceUnrestricted", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction LICENCE_UNRESTRICTED = new Restriction("LICENCE_UNRESTRICTED");

    /**
     * Formal permission required for a person or an entity to use the resource
     * and that may differ from the person that orders or purchases it.
     *
     * @since 3.1
     */
    @UML(identifier="licenceEndUser", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction LICENCE_END_USER = new Restriction("LICENCE_END_USER");

    /**
     * Formal permission required for a person or an entity to commercialize or
     * distribute the resource.
     *
     * @since 3.1
     */
    @UML(identifier="licenceDistributor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction LICENCE_DISTRIBUTOR = new Restriction("LICENCE_DISTRIBUTOR");

    /**
     * Protects rights of individual or organisations from observation, intrusion,
     * or attention of others.
     *
     * @since 3.1
     */
    @UML(identifier="private", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction PRIVATE = new Restriction("PRIVATE");

    /**
     * Prescribed by law.
     *
     * @since 3.1
     */
    @UML(identifier="statutory", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction STATUTORY = new Restriction("STATUTORY");

    /**
     * Not available to the public contains information that could be prejudicial
     * to a commercial, industrial, or national interest.
     *
     * @since 3.1
     */
    @UML(identifier="confidential", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction CONFIDENTIAL = new Restriction("CONFIDENTIAL");

    /**
     * Although unclassified, requires strict controls over its distribution.
     *
     * @since 3.1
     */
    @UML(identifier="sensitiveButUnclassified", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction SENSITIVE_BUT_UNCLASSIFIED = new Restriction("SENSITIVE_BUT_UNCLASSIFIED");

    /**
     * With trust.
     *
     * @since 3.1
     */
    @UML(identifier="in-confidence", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Restriction IN_CONFIDENCE = new Restriction("IN_CONFIDENCE");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Restriction(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the programmatic name and the UML identifier of this code, together with legacy UML identifier
     * if any. In particular, {@link #LICENCE} is known as both {@code "licence"} (from ISO 19115:2014) and
     * {@code "license"} (from ISO 19115:2003).
     *
     * @return Names of this code, including legacy names if any.
     */
    @Override
    public String[] names() {
        if (this == LICENSE) {
            return new String[] {name(), "LICENSE", identifier(), "license"};
        }
        return super.names();
    }

    /**
     * Returns the list of {@code Restriction}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Restriction[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(Restriction[]::new);
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
    public Restriction[] family() {
        return values();
    }

    /**
     * Returns the restriction that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * <p>For compatibility reasons, the {@code "LICENSE"} string (derived from ISO 19115:2003)
     * is taken as synonymous to {@code "LICENCE"} (derived from ISO 19115:2014).</p>
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Restriction valueOf(String code) {
        if ("LICENSE".equals(code)) {
            code = "LICENCE";           // For compatibility between ISO 19115:2003 and ISO 19115:2014.
        }
        return valueOf(Restriction.class, code);
    }
}
