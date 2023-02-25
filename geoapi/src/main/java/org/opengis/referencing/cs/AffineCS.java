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
package org.opengis.referencing.cs;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A two- or three-dimensional coordinate system with straight axes that are not necessarily orthogonal.
 * An {@code AffineCS} shall have two or three {@linkplain #getAxis axis associations}.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CRS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.crs.EngineeringCRS Engineering},
 *   {@link org.opengis.referencing.crs.ImageCRS       Image}
 * </TD></TR></TABLE>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @see CartesianCS
 */
@UML(identifier="CS_AffineCS", specification=ISO_19111)
public interface AffineCS extends CoordinateSystem {
}
