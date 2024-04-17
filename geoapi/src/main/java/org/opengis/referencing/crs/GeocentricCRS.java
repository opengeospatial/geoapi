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
import org.opengis.referencing.cs.SphericalCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 3D coordinate reference system with the origin at the approximate centre of mass of the earth.
 * A geocentric CRS deals with the earth's curvature by taking a 3D spatial view, which obviates
 * the need to model the earth's curvature.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.CartesianCS Cartesian},
 *   {@link org.opengis.referencing.cs.SphericalCS Spherical}
 * </TD></TR></TABLE>
 *
 * @departure historic
 *   This interface is kept conformant with the specification published in 2003.
 *   The 2007 revision of ISO 19111 removed the <code>GeographicCRS</code> and
 *   <code>GeocentricCRS</code> types, handling both using the <code>GeodeticCRS</code> parent type.
 *   GeoAPI keeps them since the distinction between those two types is in wide use.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - GeodeticDatum
 */
@UML(identifier="SC_GeocentricCRS", specification=ISO_19111)
public interface GeocentricCRS extends GeodeticCRS {
    /**
     * Returns the coordinate system, which must be {@linkplain CartesianCS cartesian}
     * or {@linkplain SphericalCS spherical}.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    CoordinateSystem getCoordinateSystem();
}
