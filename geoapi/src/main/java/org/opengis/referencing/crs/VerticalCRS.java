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

import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 1D coordinate reference system used for recording heights or depths. Vertical CRSs make use
 * of the direction of gravity to define the concept of height or depth, but the relationship with
 * gravity may not be straightforward.
 * <p>
 * By implication, ellipsoidal heights (<var>h</var>) cannot be captured in a vertical coordinate
 * reference system. Ellipsoidal heights cannot exist independently, but only as inseparable part
 * of a 3D coordinate tuple defined in a geographic 3D coordinate reference system. However GeoAPI
 * does not enforce this rule. Some applications may relax this rule and accept ellipsoidal heights
 * in the following context:
 *
 * <ul>
 *   <li><p>As a transient state while parsing <A HREF="../doc-files/WKT.html">Well Known Text</A>,
 *       or any other format based on legacy specifications where ellipsoidal heights were allowed
 *       as an independent axis.</p></li>
 *
 *   <li><p>As short-lived objects to be passed or returned by methods enforcing type safety, for
 *       example {@link org.opengis.metadata.extent.VerticalExtent#getVerticalCRS}.</p></li>
 *
 *   <li><p>Other cases at implementor convenience. However implementors are encouraged to
 *       assemble the full 3D CRS as soon as they can.</p></li>
 * </ul>
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.VerticalCS Vertical}
 * </TD></TR></TABLE>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - VerticalDatum
 * @navassoc 1 - - VerticalCS
 */
@UML(identifier="SC_VerticalCRS", specification=ISO_19111)
public interface VerticalCRS extends SingleCRS {
    /**
     * Returns the coordinate system, which must be vertical.
     */
    @Override
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    VerticalCS getCoordinateSystem();

    /**
     * Returns the datum, which must be vertical.
     */
    @Override
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    VerticalDatum getDatum();
}
