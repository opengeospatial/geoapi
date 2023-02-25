/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.lineage;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Distance between consistent parts of (centre, left side, right side) adjacent pixels.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="LE_NominalResolution", specification=ISO_19115_2)
public interface NominalResolution {
    /**
     * Distance between consistent parts of (centre, left side, right side) adjacent pixels
     * in the scan plane.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change in GeoAPI
     *     3.1. It may be replaced by the {@link javax.measure.quantity.Length} type in order to
     *     provide unit of measurement together with the value.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Distance between consistent parts of adjacent pixels in the scan plane.
     * @unitof Distance
     */
    @UML(identifier="scanningResolution", obligation=MANDATORY, specification=ISO_19115_2)
    Double getScanningResolution();

    /**
     * Distance between consistent parts of (centre, left side, right side) adjacent pixels
     * in the object space.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change in GeoAPI
     *     3.1. It may be replaced by the {@link javax.measure.quantity.Length} type in order to
     *     provide unit of measurement together with the value.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Distance between consistent parts of adjacent pixels in the object space.
     * @unitof Distance
     */
    @UML(identifier="groundResolution", obligation=MANDATORY, specification=ISO_19115_2)
    Double getGroundResolution();
}
