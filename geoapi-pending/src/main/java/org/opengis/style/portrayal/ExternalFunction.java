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
 * External functions are used to perform computations that sometimes are needed to evaluate the query
 * statements and/or perform the portrayal rules.
 *
 * <p>
 * There are no limitations to the operations it can perform or the return types it can have.<br>
 * External functions shall be modeled as operations, as described in ISO 19109.<br>
 * External functions shall not be used in the default portrayal specification.
 * </p>
 *
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_ExternalFunction", specification=ISO_19117)
public interface ExternalFunction {
    /**
     * Formal parameters for this function.
     * this is a immutable copy of the collection.
     *
     * @return collection of AttributeDefinition
     */
    @UML(identifier="formalParameter", obligation=MANDATORY, specification=ISO_19117)
    Collection<AttributeDefinition> getParameters();

    /**
     * Returns the name of the function.
     * It shall contain no spaces and always start with a letter
     * or and underscore character.
     */
    @UML(identifier="functionName", obligation=MANDATORY, specification=ISO_19117)
    String getName();

    /**
     * Returns the class type for this function.
     */
    @UML(identifier="returnType", obligation=MANDATORY, specification=ISO_19117)
    Class getReturnType();

    /**
     * Returns a description of the function.
     * It is a human readable value.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();
}
