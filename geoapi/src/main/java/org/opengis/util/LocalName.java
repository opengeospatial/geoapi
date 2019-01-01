/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.util.List;
import java.util.Collections;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identifier within a {@linkplain NameSpace name space} for a local object.
 * Local names are names which are directly accessible to and maintained by a {@link NameSpace}.
 * Names are local to one and only one name space.
 * The name space within which they are local is indicated by the {@linkplain #scope() scope}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Bryce Nordgren (USDA)
 * @version 3.0
 * @since   2.0
 *
 * @see NameFactory#createLocalName(NameSpace, CharSequence)
 */
@UML(identifier="LocalName", specification=ISO_19103)
public interface LocalName extends GenericName {
    /**
     * Returns always 1 for a local name.
     *
     * @return always 1 for a local name.
     */
    @Override
    int depth();

    /**
     * Returns a singleton containing only {@code this}, since this name is itself a local name.
     *
     * @return a {@linkplain Collections#singleton singleton} containing only {@code this}.
     */
    @Override
    @UML(identifier="parsedName", obligation=MANDATORY, specification=ISO_19103)
    List<? extends LocalName> getParsedNames();

    /**
     * Returns {@code this} since this object is already a local name.
     *
     * @since 2.2
     */
    @Override
    LocalName head();

    /**
     * Returns {@code this} since this object is already a local name.
     *
     * @since 2.1
     */
    @Override
    LocalName tip();

    /**
     * Returns a locale-independent string representation of this local name.
     *
     * @return the local-independent string representation of this name.
     */
    @Override
    @UML(identifier="aName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
