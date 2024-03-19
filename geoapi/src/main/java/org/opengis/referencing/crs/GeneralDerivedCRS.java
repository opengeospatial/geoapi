/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.referencing.operation.Conversion;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A <abbr>CRS</abbr> that is defined by applying a coordinate conversion to another preexisting <abbr>CRS</abbr>.
 * The derived <abbr>CRS</abbr> inherits its datum (reference frame) or datum ensemble from its base <abbr>CRS</abbr>.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @deprecated As of ISO 19111:2019, this interface is renamed as {@link DerivedCRS}.
 */
@Deprecated(since="3.1", forRemoval=true)
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SC_GeneralDerivedCRS", specification=ISO_19111, version=2007)
public interface GeneralDerivedCRS extends SingleCRS {
    /**
     * Returns the base coordinate reference system.
     *
     * @return the base coordinate reference system.
     */
    @UML(identifier="baseCRS", obligation=MANDATORY, specification=ISO_19111, version=2007)
    CoordinateReferenceSystem getBaseCRS();

    /**
     * Returns the conversion from the {@linkplain #getBaseCRS() base CRS} to this CRS.
     *
     * @return the conversion from the base CRS.
     */
    @UML(identifier="conversion", obligation=MANDATORY, specification=ISO_19111, version=2007)
    Conversion getConversionFromBase();
}
