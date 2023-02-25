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
package org.opengis.metadata.citation;

import java.net.URI;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.ComplianceLevel.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about on-line sources from which the dataset, specification, or
 * community profile name and extended metadata elements can be obtained.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - OnLineFunction
 */
@UML(identifier="CI_OnlineResource", specification=ISO_19115)
public interface OnlineResource {
    /**
     * Location (address) for on-line access using a Uniform Resource Locator address or
     * similar addressing scheme such as {@code "http://www.statkart.no/isotc211"}.
     *
     * @return Location for on-line access using a Uniform Resource Locator address or similar scheme.
     */
    @Profile(level=CORE)
    @UML(identifier="linkage", obligation=MANDATORY, specification=ISO_19115)
    URI getLinkage();

    /**
     * Connection protocol to be used. Returns {@code null} if none.
     *
     * @return Connection protocol to be used, or {@code null}.
     */
    @UML(identifier="protocol", obligation=OPTIONAL, specification=ISO_19115)
    String getProtocol();

    /**
     * Name of an application profile that can be used with the online resource.
     * Returns {@code null} if none.
     *
     * @return Application profile that can be used with the online resource, or {@code null}.
     */
    @UML(identifier="applicationProfile", obligation=OPTIONAL, specification=ISO_19115)
    String getApplicationProfile();

    /**
     * Name of the online resource. Returns {@code null} if none.
     *
     * @return Name of the online resource, or {@code null}.
     *
     * @since 2.1
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    String getName();

    /**
     * Detailed text description of what the online resource is/does.
     * Returns {@code null} if none.
     *
     * @return Text description of what the online resource is/does, or {@code null}.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getDescription();

    /**
     * Code for function performed by the online resource.
     * Returns {@code null} if unspecified.
     *
     * @return Function performed by the online resource, or {@code null}.
     */
    @UML(identifier="function", obligation=OPTIONAL, specification=ISO_19115)
    OnLineFunction getFunction();
}
