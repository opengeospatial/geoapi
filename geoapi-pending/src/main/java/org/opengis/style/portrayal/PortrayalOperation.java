/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.style.portrayal;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * A portrayal operation holds the details for a particular portrayal operation.
 * It declares a set of formal parameters that are need when invoking the
 * underlying rendering functions.
 *
 * <p>They should be one instance of portrayal specification class for each operation
 * defined by the portrayal service.</p>
 *
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_PortrayalOperation", specification=ISO_19117)
public interface PortrayalOperation {
    /**
     * Returns the name of the operation.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19117)
    String getName();

    /**
     * Returns a description of the operation.
     * It is a human readable value.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();

    /**
     * Returns the list of External functions.
     */
    @UML(identifier="externalFunction", obligation=OPTIONAL, specification=ISO_19117)
    Collection<ExternalFunction> getExternalFunctions();

    /**
     * Returns a list of attributDefinition used by this operation.
     */
    @UML(identifier="formalParameter{ordered}", obligation=MANDATORY, specification=ISO_19117)
    Collection<AttributeDefinition> getFormalParameters();

    /**
     * Parameterset to use.
     *
     * Those parameter are given when we invoke a
     * portrayal operation, depending on the rendering device, this may result
     * on a return value or not.
     *
     * <p><b>Caution</b> This method may change!</p>
     */
    void portray(ParameterSet parameters);
}
