/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.metadata.quality;

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identifier of a measure fully described elsewhere.
 * At least one of {@linkplain #getMeasureIdentification() measure identification}
 * and {@linkplain #getNamesOfMeasure() measure names} shall be provided.
 * The whole description can be found within a measure register or catalogue.
 *
 * <h2>Catalogue of data quality measures</h2>
 * Catalogues of data quality measures may be available online to fully describe
 * the measures referenced in the data quality report of the data evaluated.
 * The catalogue may contain the set of measures used in one or several data quality reports.
 * The catalogue (as a register) enables the user to describe the measure,
 * and store the information in order to be able to refer to it each time needed,
 * instead of re-describing the measure within a data quality report.
 * This {@code MeasureReference} represents a reference to a measure in a catalogue.
 * The full measure description is given by {@link Measure}.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see Element#getMeasureReference()
 * @see Measure
 *
 * @since 3.1
 */
@UML(identifier="DQ_MeasureReference", specification=ISO_19157)
public interface MeasureReference {
    /**
     * Identifier of the measure, value uniquely identifying the measure within a namespace.
     *
     * @return code identifying a registered measure, or {@code null} if none.
     *
     * @see Measure#getMeasureIdentifier()
     */
    @UML(identifier="measureIdentification", obligation=OPTIONAL, specification=ISO_19157)
    default Identifier getMeasureIdentification() {
        return null;
    }

    /**
     * Name(s) of the test applied to the data.
     * Mandatory if {@linkplain #getMeasureIdentification() measure identification} is not provided.
     *
     * @return name of the test applied to the data.
     *
     * @see Measure#getName()
     */
    @UML(identifier="nameOfMeasure", obligation=CONDITIONAL, specification=ISO_19157)
    default Collection<? extends InternationalString> getNamesOfMeasure() {
        return Collections.emptyList();
    }

    /**
     * Description of the measure.
     *
     * @return description of the measure being determined, or {@code null} if none.
     *
     * @see Measure#getDefinition()
     */
    @UML(identifier="measureDescription", obligation=OPTIONAL, specification=ISO_19157)
    default InternationalString getMeasureDescription() {
        return null;
    }
}
