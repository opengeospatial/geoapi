/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.citation;

import java.util.List;
import java.util.ArrayList;

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
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<Role> VALUES = new ArrayList<>(20);

    /**
     * Party that supplies the resource.
     */
    @UML(identifier="resourceProvider", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role RESOURCE_PROVIDER = new Role("RESOURCE_PROVIDER");

    /**
     * Party that accepts accountability and responsibility for the data and ensures
     * appropriate care and maintenance of the resource.
     */
    @UML(identifier="custodian", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role CUSTODIAN = new Role("CUSTODIAN");

    /**
     * Party that owns the resource.
     */
    @UML(identifier="owner", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role OWNER = new Role("OWNER");

    /**
     * Party who uses the resource.
     */
    @UML(identifier="user", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role USER = new Role("USER");

    /**
     * Party who distributes the resource.
     */
    @UML(identifier="distributor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role DISTRIBUTOR = new Role("DISTRIBUTOR");

    /**
     * Party who created the resource.
     */
    @UML(identifier="originator", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role ORIGINATOR = new Role("ORIGINATOR");

    /**
     * Party who can be contacted for acquiring knowledge about or acquisition of the resource.
     */
    @UML(identifier="pointOfContact", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role POINT_OF_CONTACT = new Role("POINT_OF_CONTACT");

    /**
     * Key party responsible for gathering information and conducting research.
     */
    @UML(identifier="principalInvestigator", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role PRINCIPAL_INVESTIGATOR = new Role("PRINCIPAL_INVESTIGATOR");

    /**
     * Party who has processed the data in a manner such that the resource has been modified.
     */
    @UML(identifier="processor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role PROCESSOR = new Role("PROCESSOR");

    /**
     * Party who published the resource.
     */
    @UML(identifier="publisher", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role PUBLISHER = new Role("PUBLISHER");

    /**
     * Party who authored the resource.
     */
    @UML(identifier="author", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role AUTHOR = new Role("AUTHOR");

    /**
     * Party who speaks for the resource.
     *
     * @since 3.1
     */
    @UML(identifier="sponsor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role SPONSOR = new Role("SPONSOR");

    /**
     * Party who jointly authors the resource.
     *
     * @since 3.1
     */
    @UML(identifier="coAuthor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role CO_AUTHOR = new Role("CO_AUTHOR");

    /**
     * Party who assists with the generation of the resource other than the principal investigator.
     *
     * @since 3.1
     */
    @UML(identifier="collaborator", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role COLLABORATOR = new Role("COLLABORATOR");

    /**
     * Party who reviewed or modified the resource to improve the content.
     *
     * @since 3.1
     */
    @UML(identifier="editor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role EDITOR = new Role("EDITOR");

    /**
     * A class of entity that immediate access to the resource and for whom the resource is intended or useful.
     *
     * @since 3.1
     */
    @UML(identifier="mediator", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role MEDIATOR = new Role("MEDIATOR");

    /**
     * Party owning or managing rights over the resource.
     *
     * @since 3.1
     */
    @UML(identifier="rightsHolder", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role RIGHTS_HOLDER = new Role("RIGHTS_HOLDER");

    /**
     * Party contributing to the resource.
     *
     * @since 3.1
     */
    @UML(identifier="contributor", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role CONTRIBUTOR = new Role("CONTRIBUTOR");

    /**
     * Party providing monetary support for the resource.
     *
     * @since 3.1
     */
    @UML(identifier="funder", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role FUNDER = new Role("FUNDER");

    /**
     * Party who has an interest in the resource or the use of the resource.
     *
     * @since 3.1
     */
    @UML(identifier="stakeholder", obligation=CONDITIONAL, specification=ISO_19115)
    public static final Role STAKEHOLDER = new Role("STAKEHOLDER");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private Role(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Role}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static Role[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new Role[VALUES.size()]);
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
    public Role[] family() {
        return values();
    }

    /**
     * Returns the role that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static Role valueOf(String code) {
        return valueOf(Role.class, code);
    }
}
