/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
 * A coordinate reference system that is defined by its coordinate
 * {@linkplain Conversion conversion} from another coordinate reference system.
 * Derived CRS are not directly associated to a {@linkplain org.opengis.referencing.datum.Datum datum}.
 *
 * <p>In principle, all sub-types of {@link CoordinateReferenceSystem} may take on the role of either source or
 * derived CRS with the exception of a {@link GeocentricCRS} and a {@link ProjectedCRS}. The latter is modelled
 * as an object class under its own name, rather than as a general derived CRS of type "projected".
 * This has been done to honour common practice, which acknowledges projected CRSs as one of the best known
 * types of coordinate reference systems.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="SC_GeneralDerivedCRS", specification=ISO_19111, version=2007)
public interface GeneralDerivedCRS extends SingleCRS {
    /**
     * Returns the base coordinate reference system.
     *
     * @return the base coordinate reference system.
     */
    @UML(identifier="baseCRS", obligation=MANDATORY, specification=ISO_19111)
    SingleCRS getBaseCRS();

    /**
     * Returns the conversion from the {@linkplain #getBaseCRS() base CRS} to this CRS.
     *
     * @return the conversion from the base CRS.
     *
     * @departure rename
     *   "{@code conversion}" may be confusing as a method name
     *   since it does not indicate which CRS is the source or which is the target.
     *   The OGC 01-009 specification used the {@code toBase()} method name.
     *   By analogy with 01-009, GeoAPI defines a method name which contains the "{@code FromBase}" expression.
     */
    @UML(identifier="conversion", obligation=MANDATORY, specification=ISO_19111)
    Conversion getConversionFromBase();
}
