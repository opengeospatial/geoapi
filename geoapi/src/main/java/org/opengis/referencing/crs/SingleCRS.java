/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base type of {@linkplain CoordinateSystem coordinate systems} related to an object by a {@linkplain Datum datum}.
 * For {@linkplain org.opengis.referencing.datum.GeodeticDatum geodetic}
 * and {@linkplain org.opengis.referencing.datum.VerticalDatum vertical} datums, the object will be the Earth.
 *
 * <p>The valid coordinate system type and the datum type are constrained by the CRS type.
 * For example, {@code GeographicCRS} can be associated only to {@code EllipsoidalCS} and
 * {@code GeodeticDatum}.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @see org.opengis.referencing.cs.CoordinateSystem
 * @see org.opengis.referencing.datum.Datum
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SC_SingleCRS", specification=ISO_19111)
public interface SingleCRS extends CoordinateReferenceSystem {
    /**
     * Returns the coordinate system associated to this CRS.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();

    /**
     * Returns the datum associated directly or indirectly to this CRS.
     * In the case of {@link GeneralDerivedCRS}, this method returns the
     * datum of the {@linkplain GeneralDerivedCRS#getBaseCRS() base CRS}.
     *
     * @return the datum.
     *
     * @departure easeOfUse
     *   The ISO specification declares the datum as absent when the association is indirect.
     *   GeoAPI recommends to follow the link to the base CRS for users convenience.
     */
    @UML(identifier="datum", obligation=CONDITIONAL, specification=ISO_19111)
    Datum getDatum();
}
