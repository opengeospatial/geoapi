/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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

import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.operation.Projection;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 2D coordinate reference system used to approximate the shape of the earth on a planar surface.
 * It is done in such a way that the distortion that is inherent to the approximation is carefully
 * controlled and known. Distortion correction is commonly applied to calculated bearings and
 * distances to produce values that are a close match to actual field values.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.CartesianCS Cartesian}
 * </TD></TR></TABLE>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - GeodeticDatum
 * @navassoc 1 - - CartesianCS
 */
@UML(identifier="SC_ProjectedCRS", specification=ISO_19111)
public interface ProjectedCRS extends GeneralDerivedCRS {
    /**
     * Returns the base coordinate reference system, which must be geographic.
     */
    @Override
    GeographicCRS getBaseCRS();

    /**
     * Returns the map projection from the {@linkplain #getBaseCRS base CRS} to this CRS.
     */
    @Override
    Projection getConversionFromBase();

    /**
     * Returns the coordinate system, which must be cartesian.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CartesianCS getCoordinateSystem();

    /**
     * Returns the datum.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    GeodeticDatum getDatum();
}
