/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.GenericName;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19109;


/**
 * Indicates the role played by the association between two features.
 * In the area of geographic information, there exist multiple kinds of associations:
 *
 * <ul>
 *   <li><b>Aggregation</b> represents associations between features which can exist even if the aggregate is destroyed.</li>
 *   <li><b>Composition</b> represents relationships where the owned features are destroyed together with the composite.</li>
 *   <li><b>Spatial</b> association represents spatial or topological relationships that may exist between features (e.g. “<cite>east of</cite>”).</li>
 *   <li><b>Temporal</b> association may represent for example a sequence of changes over time involving the replacement of some
 *       feature instances by other feature instances.</li>
 * </ul>
 *
 * @author  Jody Garnett (Refractions Research, Inc.)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see FeatureAssociation
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="FeatureAssociationRole", specification=ISO_19109)
public interface FeatureAssociationRole extends PropertyType {
    /**
     * Returns the name of this association role.
     * For {@code FeatureAssociationRole}, the name is mandatory.
     *
     * @return the association role name.
     */
    @Override
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19109)
    GenericName getName();

    /**
     * Returns the type of feature values.
     *
     * @return the type of feature values.
     */
    @UML(identifier="valueType", obligation=MANDATORY, specification=ISO_19109)
    FeatureType getValueType();

    /**
     * Returns the minimum number of occurrences of the association within its containing entity.
     * The returned value is greater than or equal to zero.
     *
     * @return the minimum number of occurrences of the association within its containing entity.
     */
    @UML(identifier="cardinality", obligation=MANDATORY, specification=ISO_19109)
    int getMinimumOccurs();

    /**
     * Returns the maximum number of occurrences of the association within its containing entity.
     * The returned value is greater than or equal to the {@link #getMinimumOccurs()} value.
     * If there is no maximum, then this method returns {@link Integer#MAX_VALUE}.
     *
     * @return the maximum number of occurrences of the association within its containing entity,
     *         or {@link Integer#MAX_VALUE} if none.
     */
    @UML(identifier="cardinality", obligation=MANDATORY, specification=ISO_19109)
    int getMaximumOccurs();

    /**
     * Creates a new feature association instance of this role.
     *
     * @return a new feature association instance.
     * @throws UnsupportedOperationException if this role does not support new instance creation.
     */
    FeatureAssociation newInstance() throws UnsupportedOperationException;
}
