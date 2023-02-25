/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
     * @return the association name specified by its role.
     */
    @Override
    GenericName getName();

    /**
     * Returns information about the association.
     *
     * @return information about the association.
     */
    FeatureAssociationRole getRole();

    /**
     * Returns the associated feature, or {@code null} if none. This convenience method can be invoked in
     * the common case where the {@linkplain FeatureAssociationRole#getMaximumOccurs() maximum number} of
     * features is restricted to 1 or 0.
     *
     * @return the associated feature (may be {@code null}).
     * @throws MultiValuedPropertyException if this association contains more than one value.
     *
     * @see Feature#getPropertyValue(String)
     */
    @Override
    Feature getValue() throws MultiValuedPropertyException;

    /**
     * Sets the associated feature.
     *
     * <div class="note"><b>Note on validation</b>:
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer another method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  value  the new value, or {@code null}.
     * @throws InvalidPropertyValueException if this method verifies argument validity and the given value
     *         does not met the association constraints.
     *
     * @see Feature#setPropertyValue(String, Object)
     */
    void setValue(Feature value) throws InvalidPropertyValueException;

    /**
     * Returns all features, or an empty collection if none.
     *
     * <div class="note"><b>Implementation note</b>:
     * there is different approaches in the way that collection elements are related to this property values:
     * <ul>
     *   <li>The collection may be a snapshot of property values at the method invocation time.</li>
     *   <li>The collection may be an unmodifiable view of properties values.</li>
     *   <li>The collection may be <cite>live</cite> (changes in the collection are reflected in this association, and vis-versa).</li>
     * </ul>
     * This method does not mandate a particular approach.
     * However, implementations should document which policy they choose.
     * </div>
     *
     * @return the features.
     */
    Collection<Feature> getValues();

    /**
     * Sets the features. All previous values are replaced by the given collection.
     *
     * <div class="note"><b>Note on validation</b>:
     * the verifications performed by this method is implementation dependent.
     * For performance reasons, an implementation may verify only the most basic constraints
     * and offer another method for performing more extensive validation.
     * Implementations should document their validation process.</div>
     *
     * @param  values  the new values.
     * @throws InvalidPropertyValueException if this method verifies argument validity and the given values
     *         do not met the association constraints.
     */
    void setValues(Collection<? extends Feature> values) throws InvalidPropertyValueException;
}
