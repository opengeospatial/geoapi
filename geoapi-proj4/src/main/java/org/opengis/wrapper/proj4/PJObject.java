/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.wrapper.proj4;

import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;


/**
 * Base class of GeoAPI implementations in this Proj4 wrappers package. This class defines
 * empty skeleton for the GeoAPI methods that we do not support in this package.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
class PJObject implements IdentifiedObject {
    /**
     * The name of this referencing object.
     */
    final ReferenceIdentifier name;

    /**
     * Creates a new object of the given name.
     */
    PJObject(final ReferenceIdentifier name) {
        this.name = name;
    }

    /**
     * Returns the name of this referencing object.
     */
    @Override
    public ReferenceIdentifier getName() {
        return name;
    }

    /**
     * Returns an empty set, since there is no additional identifiers
     * associated with our Proj4 wrappers.
     */
    @Override
    public Set<ReferenceIdentifier> getIdentifiers() {
        return Collections.emptySet();
    }

    /**
     * Returns an empty set, since there is no aliases associated with our Proj4 wrappers.
     */
    @Override
    public Collection<GenericName> getAlias() {
        return Collections.emptySet();
    }

    /**
     * Returns {@code null} since we do not define any scope in our Proj4 wrappers.
     * Note that this method is not inherited from {@link IdentifiedObject}, but is
     * defined in sub-interfaces like {@link org.opengis.referencing.crs.SingleCRS}.
     */
    public InternationalString getScope() {
        return null;
    }

    /**
     * Returns {@code null} since we do not define any domain of validity.
     * Note that this method is not inherited from {@link IdentifiedObject}, but is
     * defined in sub-interfaces like {@link org.opengis.referencing.crs.SingleCRS}.
     */
    public Extent getDomainOfValidity() {
        return null;
    }

    /**
     * Returns {@code null} since there is no remarks associated with our Proj4 wrappers.
     */
    @Override
    public InternationalString getRemarks() {
        return null;
    }

    /**
     * Throws unconditionally the exception since we do not support WKT formatting.
     */
    @Override
    public String toWKT() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
