/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import java.util.List;
import java.util.ArrayList;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Class of information to which the referencing entity applies.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SV_CouplingType", specification=ISO_19115)
public final class CouplingType  extends CodeList<CouplingType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6836260354860513463L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<CouplingType> VALUES = new ArrayList<>(3);

    /**
     * Service instance is loosely coupled with a data instance, which means no
     * {@link DataIdentification} class has to be described.
     */
    @UML(identifier="loose", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CouplingType LOOSE = new CouplingType("LOOSE");

    /**
     * Service instance is mixed coupled with a data instance, which means
     * {@link DataIdentification} describes the associated data instance and
     * additionally the service instance might work with other external data instances.
     */
    @UML(identifier="mixed", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CouplingType MIXED = new CouplingType("MIXED");

    /**
     * Service instance is tightly coupled with a data instance, which means
     * {@link DataIdentification} class MUST be described.
     */
    @UML(identifier="tight", obligation=CONDITIONAL, specification=ISO_19115)
    public static final CouplingType TIGHT = new CouplingType("TIGHT");

    /**
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private CouplingType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code CouplingType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    @Override
    public CouplingType[] family() {
        return values();
    }

    /**
     * Returns the list of {@code CouplingType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static CouplingType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new CouplingType[VALUES.size()]);
        }
    }

    /**
     * Returns the {@code CouplingType} List that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static CouplingType valueOf(String code) {
        return valueOf(CouplingType.class, code);
    }
}
