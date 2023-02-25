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

import org.opengis.referencing.cs.AffineCS;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.datum.ImageDatum;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An engineering coordinate reference system applied to locations in images. Image coordinate
 * reference systems are treated as a separate sub-type because a separate user community exists
 * for images with its own terms of reference.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.CartesianCS Cartesian},
 *   {@link org.opengis.referencing.cs.AffineCS    Affine}
 * </TD></TR></TABLE>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - ImageDatum
 * @navassoc 1 - - AffineCS
 */
@UML(identifier="SC_ImageCRS", specification=ISO_19111)
public interface ImageCRS extends SingleCRS {
    /**
     * Returns the cartesian coordinate system.
     */
    @UML(identifier="coordinateSystem", obligation=MANDATORY, specification=ISO_19111)
    AffineCS getCoordinateSystem();

    /**
     * Returns the datum, which must be an image one.
     */
    @UML(identifier="datum", obligation=MANDATORY, specification=ISO_19111)
    ImageDatum getDatum();
}
