/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.style.portrayal;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * AttributeDefinition is used to define the formal parameters of external functions and the underlying
 * rendering operations of the portrayal service.
 *
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_AttributeDefinition", specification=ISO_19117)
public interface AttributeDefinition {
    /**
     * Returns the name of the attribute definition.
     * It shall be a legal and unique name for this function.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19117)
    String getName();

    /**
     * Returns a description of the usage of this attribute.
     * It is a human readable value.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();

    /**
     * Returns the class type for this attribute.
     * It shall be a basic legal type.
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19117)
    Class getReturnType();

    /**
     * Returns a Default Value for this attribut.
     * This value is optional.
     */
    @UML(identifier="defaultValue", obligation=OPTIONAL, specification=ISO_19117)
    Object getDefault();
}
