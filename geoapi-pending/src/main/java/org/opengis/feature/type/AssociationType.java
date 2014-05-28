/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2014 Open Geospatial Consortium, Inc.
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
package org.opengis.feature.type;

import org.opengis.feature.Association;

/**
 * The type of an association; used to describe kind of relationship between two entities.
 * <p>
 * The notion of an "association" is similar to that of an association in UML
 * and is used to model a relationship among two attributes. See the javadoc for
 * {@link Association} for more info on the semantics of associations.
 * </p>
 * <p>
 * An association is used to relate one attribute to another. The type of the
 * association specifies the type of the related attribute with the
 * {@link #getRelatedType()} method.
 * </p>
 *
 * @author Jody Garnett, Refractions Research, Inc.
 * @author Justin Deoliveira, The Open Planning Project
 *
 * @deprecated Redesigned as {@link org.opengis.feature.FeatureAssociationRole} in the {@code org.opengis.feature} package.
 */
@Deprecated
public interface AssociationType extends org.opengis.feature.FeatureAssociationRole, PropertyType {

    /**
     * Override of {@link PropertyType#getSuper()} which type narrows to
     * {@link AssociationType}.
     *
     * @see PropertyType#getSuper()
     */
    AssociationType getSuper();

    /**
     * The attribute type of the related attribute in the association.
     *
     * @return The type of the related attribute.
     */
    AttributeType getRelatedType();

    /**
     * Override of {@link PropertyType#getBinding()} which specifies that this
     * method should return <code>getRelatedType().getBinding()</code>, that is
     * it returns the binding of the related type.
     */
    Class<?> getBinding();
}
