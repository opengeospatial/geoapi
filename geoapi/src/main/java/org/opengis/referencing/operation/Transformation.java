/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
 * An operation on coordinates that usually includes a change of datum.
 * The parameters of a coordinate transformation are empirically derived from data
 * containing the coordinates of a series of points in both coordinate reference systems.
 * This computational process is usually "over-determined",
 * allowing derivation of error (or accuracy) estimates for the transformation.
 * Also, the stochastic nature of the parameters may result in multiple (different) versions
 * of the same coordinate transformation.
 * Because of this, several transformations may exist for a given pair of coordinate reference systems,
 * differing in their transformation method, parameter values and accuracy characteristics.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see Conversion
 */
@UML(identifier="CC_Transformation", specification=ISO_19111, version=2007)
public interface Transformation extends SingleOperation {
    /**
     * Returns the source CRS.
     *
     * @return the source CRS (never {@code null}).
     */
    @Override
    @UML(identifier="sourceCRS", obligation=MANDATORY, specification=ISO_19111)
    CoordinateReferenceSystem getSourceCRS();

    /**
     * Returns the target CRS.
     *
     * @return the target CRS (never {@code null}).
     */
    @Override
    @UML(identifier="targetCRS", obligation=MANDATORY, specification=ISO_19111)
    CoordinateReferenceSystem getTargetCRS();

    /**
     * Version of the coordinate transformation (i.e., instantiation due to the stochastic
     * nature of the parameters). This attribute is mandatory in a Transformation.
     *
     * @return the coordinate operation version.
     */
    @Override
    @UML(identifier="operationVersion", obligation=MANDATORY, specification=ISO_19111)
    String getOperationVersion();
}
