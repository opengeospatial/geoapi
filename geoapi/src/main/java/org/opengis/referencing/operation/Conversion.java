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
package org.opengis.referencing.operation;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An operation on coordinates that does not include any change of Datum. The best-known
 * example of a coordinate conversion is a map projection. The parameters describing
 * coordinate conversions are defined rather than empirically derived.
 * <p>
 * Note that some conversions have no parameters.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see Transformation
 */
@UML(identifier="CC_Conversion", specification=ISO_19111)
public interface Conversion extends SingleOperation {
    /**
     * Returns the source CRS. Conversions may have a source CRS that
     * is not specified here, but through
     * {@link org.opengis.referencing.crs.GeneralDerivedCRS#getBaseCRS()} instead.
     *
     * @return The source CRS, or {@code null} if not available.
     */
    @UML(identifier="sourceCRS", obligation=OPTIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getSourceCRS();

    /**
     * Returns the target CRS. {@linkplain Conversion Conversions} may have a target CRS
     * that is not specified here, but through
     * {@link org.opengis.referencing.crs.GeneralDerivedCRS} instead.
     *
     * @return The target CRS, or {@code null} if not available.
     */
    @UML(identifier="targetCRS", obligation=OPTIONAL, specification=ISO_19111)
    CoordinateReferenceSystem getTargetCRS();

    /**
     * This attribute is declared in {@link CoordinateOperation} but is not used in a conversion.
     *
     * @return Always {@code null}.
     */
    @UML(identifier="operationVersion", obligation=FORBIDDEN, specification=ISO_19111)
    String getOperationVersion();
}
