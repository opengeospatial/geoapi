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
package org.opengis.metadata.quality;

import java.util.Collection;

import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.maintenance.ScopeDescription;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Description of the data specified by the scope.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 *
 * @deprecated As of ISO 19115:2014, {@code DQ_Scope} has been replaced by {@code MD_Scope}.
 *             The later is defined in the {@link org.opengis.metadata.maintenance} package.
 */
@Deprecated
@Classifier(Stereotype.DATATYPE)
@UML(identifier="DQ_Scope", specification=ISO_19115, version=2003)
public interface Scope extends org.opengis.metadata.maintenance.Scope {
    /**
     * Hierarchical level of the data specified by the scope.
     *
     * @return hierarchical level of the data.
     */
    @Override
    @UML(identifier="level", obligation=MANDATORY, specification=ISO_19115)
    ScopeCode getLevel();

    /**
     * Information about the spatial, vertical and temporal extent of the data specified by the scope.
     *
     * @return information about the extent of the data, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getExtents()}.
     */
    @Deprecated
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Extent getExtent();

    /**
     * Detailed description about the level of the data specified by the scope.
     * Shall be defined only if the {@linkplain #getLevel() level} is not equal
     * to {@link ScopeCode#DATASET} or {@link ScopeCode#SERIES}.
     *
     * @return detailed description about the level of the data.
     *
     * @since 2.1
     */
    @Override
    @UML(identifier="levelDescription", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends ScopeDescription> getLevelDescription();
}
