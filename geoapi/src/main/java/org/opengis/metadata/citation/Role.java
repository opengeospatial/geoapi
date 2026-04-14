/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Function performed by the responsible party.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="CI_RoleCode", specification=ISO_19115)
public final class Role extends CodeList<Role> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -7763516018565534103L;

    /**
     * Party that supplies the resource.
     */
    @UML(identifier="resourceProvider", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role RESOURCE_PROVIDER;

    /**
     * Party that accepts accountability and responsibility for the data and ensures
     * appropriate care and maintenance of the resource.
     */
    @UML(identifier="custodian", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role CUSTODIAN;

    /**
     * Party that owns the resource.
     */
    @UML(identifier="owner", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role OWNER;

    /**
     * Party who uses the resource.
     */
    @UML(identifier="user", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role USER;

    /**
     * Party who distributes the resource.
     */
    @UML(identifier="distributor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role DISTRIBUTOR;

    /**
     * Party who created the resource.
     */
    @UML(identifier="originator", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role ORIGINATOR;

    /**
     * Party who can be contacted for acquiring knowledge about or acquisition of the resource.
     */
    @UML(identifier="pointOfContact", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role POINT_OF_CONTACT;

    /**
     * Key party responsible for gathering information and conducting research.
     */
    @UML(identifier="principalInvestigator", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role PRINCIPAL_INVESTIGATOR;

    /**
     * Party who has processed the data in a manner such that the resource has been modified.
     */
    @UML(identifier="processor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role PROCESSOR;

    /**
     * Party who published the resource.
     */
    @UML(identifier="publisher", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role PUBLISHER;

    /**
     * Party who authored the resource.
     */
    @UML(identifier="author", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role AUTHOR;

    /**
     * Party who speaks for the resource.
     *
     * @since 3.1
     */
    @UML(identifier="sponsor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role SPONSOR;

    /**
     * Party who jointly authors the resource.
     *
     * @since 3.1
     */
    @UML(identifier="coAuthor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role CO_AUTHOR;

    /**
     * Party who assists with the generation of the resource other than the principal investigator.
     *
     * @since 3.1
     */
    @UML(identifier="collaborator", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role COLLABORATOR;

    /**
     * Party who reviewed or modified the resource to improve the content.
     *
     * @since 3.1
     */
    @UML(identifier="editor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role EDITOR;

    /**
     * A class of entity that immediate access to the resource and for whom the resource is intended or useful.
     *
     * @since 3.1
     */
    @UML(identifier="mediator", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role MEDIATOR;

    /**
     * Party owning or managing rights over the resource.
     *
     * @since 3.1
     */
    @UML(identifier="rightsHolder", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role RIGHTS_HOLDER;

    /**
     * Party contributing to the resource.
     *
     * @since 3.1
     */
    @UML(identifier="contributor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role CONTRIBUTOR;

    /**
     * Party providing monetary support for the resource.
     *
     * @since 3.1
     */
    @UML(identifier="funder", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role FUNDER;

    /**
     * Party who has an interest in the resource or the use of the resource.
     *
     * @since 3.1
     */
    @UML(identifier="stakeholder", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role STAKEHOLDER;

    /**
     * All code list values created in the currently running <abbr>JVM</abbr>.
     */
    private static final List<Role> VALUES = initialValues(
        // Inline assignments for getting compiler error if a field is missing or duplicated.
        RESOURCE_PROVIDER      = new Role("RESOURCE_PROVIDER"),
        CUSTODIAN              = new Role("CUSTODIAN"),
        OWNER                  = new Role("OWNER"),
        USER                   = new Role("USER"),
        DISTRIBUTOR            = new Role("DISTRIBUTOR"),
        ORIGINATOR             = new Role("ORIGINATOR"),
        POINT_OF_CONTACT       = new Role("POINT_OF_CONTACT"),
        PRINCIPAL_INVESTIGATOR = new Role("PRINCIPAL_INVESTIGATOR"),
        PROCESSOR              = new Role("PROCESSOR"),
        PUBLISHER              = new Role("PUBLISHER"),
        AUTHOR                 = new Role("AUTHOR"),
        SPONSOR                = new Role("SPONSOR"),
        CO_AUTHOR              = new Role("CO_AUTHOR"),
        COLLABORATOR           = new Role("COLLABORATOR"),
        EDITOR                 = new Role("EDITOR"),
        MEDIATOR               = new Role("MEDIATOR"),
        RIGHTS_HOLDER          = new Role("RIGHTS_HOLDER"),
        CONTRIBUTOR            = new Role("CONTRIBUTOR"),
        FUNDER                 = new Role("FUNDER"),
        STAKEHOLDER            = new Role("STAKEHOLDER"));

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Role(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code Role}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Role[] values() {
        return VALUES.toArray(Role[]::new);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public Role[] family() {
        return values();
    }

    /**
     * Returns the role that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Role valueOf(String code) {
        return valueOf(VALUES, code, Role::new);
    }
}
