/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.observation.sampling;

import org.opengis.observation.Measure;
import org.opengis.observation.ProcessModel;
import org.opengis.temporal.TemporalPrimitive;
import org.opengis.util.GenericName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A located object on which measurements may be made.
 *
 * A basic material classification is provided using the "material" property.
 * Its value may be relatively generic (rock, pulp) or may reflect a detailed classification (calcrete, adamellite, biotite-schist).
 * In the latter case it is wise to use the codeSpace attribute to provide a link to the classification scheme/vocabulary used.
 *
 * Note that if this specimen is a "processed" version of another (e.g. by grinding, sieving, etc) then
 * the predecessor (if known) may be recorded as a relatedSamplingFeature.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="Specimen", specification=OGC_07022)
public interface Specimen extends SamplingFeature {

    /**
     * Material type, usually taken from a controlled vocabulary.
     * Specialised domains may choose to fix the vocabulary to be used.
     */
    @UML(identifier="materialClass", obligation=MANDATORY, specification=OGC_07022)
    GenericName getMaterialClass();

    /**
     * Storage location of specimen if it still exists.
     * If destroyed in analysis, then either omit or use xlink:href to point to a suitable URN, e.g. urn:cgi:def:nil:destroyed.
     */
    @UML(identifier="currentLocation", obligation=OPTIONAL, specification=OGC_07022)
    Location getCurrentLocation();

    /**
     * Method used when retrieving specimen from host sampledFeature
     */
    @UML(identifier="samplingMethod", obligation=OPTIONAL, specification=OGC_07022)
    ProcessModel getSamplingMethod();

    /**
     * Time and date when the specimen was initially retrieved
     */
    @UML(identifier="samplingTime", obligation=MANDATORY, specification=OGC_07022)
    TemporalPrimitive getSamplingTime();

    /**
     * The size of the specimen: mass, length, volume, etc.
     */
    @UML(identifier="size", obligation=OPTIONAL, specification=OGC_07022)
    Measure getSize();

}
