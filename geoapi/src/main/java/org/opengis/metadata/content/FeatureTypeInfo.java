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
package org.opengis.metadata.content;

import org.opengis.annotation.UML;
import org.opengis.util.GenericName;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about the occurring feature type.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_FeatureTypeInfo", specification=ISO_19115)
public interface FeatureTypeInfo {
    /**
     * Name of the feature type.
     *
     * @return name of the feature type.
     *
     * @see org.opengis.feature.FeatureType#getName()
     */
    @UML(identifier="featureTypeName", obligation=MANDATORY, specification=ISO_19115)
    GenericName getFeatureTypeName();

    /**
     * Number of occurrence of feature instances for this feature types.
     * May be {@code null} if unspecified.
     *
     * @return number of occurrence of feature instances for this feature types, or {@code null} if none.
     */
    @UML(identifier="featureInstanceCount", obligation=OPTIONAL, specification=ISO_19115)
    Integer getFeatureInstanceCount();
}
