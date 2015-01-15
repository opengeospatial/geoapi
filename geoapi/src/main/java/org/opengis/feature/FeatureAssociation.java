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
package org.opengis.feature;

import java.util.Collection;
import org.opengis.util.GenericName;


/**
 * An instance of an {@link FeatureAssociationRole} containing the associated feature.
 * {@code FeatureAssociation} can be instantiated by calls to {@link FeatureAssociationRole#newInstance()}.
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see FeatureAssociationRole
 */
public interface FeatureAssociation extends Property {
    /**
     * Returns the name of this association as defined by its {@linkplain #getRole() role}.
     * This convenience method delegates to {@link FeatureAssociationRole#getName()}.
     *
     * @return The association name specified by its role.
     */
    @Override
    GenericName getName();

    /**
     * Returns information about the association.
     *
     * @return Information about the association.
     */
    FeatureAssociationRole getRole();

    /**
     * Returns the associated feature, or {@code null} if none. This convenience method can be invoked in
     * the common case where the {@linkplain FeatureAssociationRole#getMaximumOccurs() maximum number} of
     * features is restricted to 1 or 0.
     *
     * @return The associated feature (may be {@code null}).
     * @throws IllegalStateException if this association contains more than one value.
     *
     * @see Feature#getPropertyValue(String)
     */
    @Override
    Feature getValue();

    /**
     * Sets the associated feature.
     *
     * <div class="note"><b>Note on validation:</b>
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer an other method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  value The new value, or {@code null}.
     * @throws IllegalArgumentException if this method verifies argument validity and the given value
     *         does not met the association constraints.
     *
     * @see Feature#setPropertyValue(String, Object)
     */
    void setValue(Feature value) throws IllegalArgumentException;

    /**
     * Returns all features, or an empty collection if none.
     *
     * <div class="note"><b>Implementation note:</b>
     * there is different approaches in the way that collection elements are related to this property values:
     * <ul>
     *   <li>The collection may be a snapshot of property values at the method invocation time.</li>
     *   <li>The collection may be an unmodifiable view of properties values.</li>
     *   <li>The collection may be <cite>live</cite> (changes in the collection are reflected in this association, and vis-versa).</li>
     * </ul>
     * This method does not mandate a particular approach.
     * However implementations should document which policy they choose.
     * </div>
     *
     * @return The features.
     */
    Collection<Feature> getValues();

    /**
     * Sets the features. All previous values are replaced by the given collection.
     *
     * <div class="note"><b>Note on validation:</b>
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer an other method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  values The new values.
     * @throws IllegalArgumentException if this method verifies argument validity and the given value
     *         does not met the association constraints.
     */
    void setValues(Collection<? extends Feature> values) throws IllegalArgumentException;
}
