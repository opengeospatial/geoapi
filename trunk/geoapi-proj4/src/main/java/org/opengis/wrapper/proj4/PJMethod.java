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

import java.util.Collection;
import org.opengis.util.GenericName;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.operation.Formula;
import org.opengis.referencing.operation.OperationMethod;


/**
 * Information about a Proj.4 projection method.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJMethod extends PJObject implements OperationMethod {
    /**
     * Creates a new operation method.
     */
    PJMethod(final ReferenceIdentifier name, final Collection<GenericName> aliases) {
        super(name, aliases);
    }

    /**
     * Returns {@code null}, since we have no information about the formulas used.
     */
    @Override
    public Formula getFormula() {
        return null;
    }

    /**
     * Returns the number of source dimensions, which is 2.
     */
    @Override
    public Integer getSourceDimensions() {
        return 2;
    }

    /**
     * Returns the number of target dimensions, which is 2.
     */
    @Override
    public Integer getTargetDimensions() {
        return 2;
    }

    /**
     * Creates a parameter group. We can not provides the descriptors for parameter values,
     * since we don't know them...
     */
    @Override
    public ParameterDescriptorGroup getParameters() {
        return new PJParameterGroup(name, aliases);
    }
}
