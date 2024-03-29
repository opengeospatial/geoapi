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
package org.opengis.metadata.citation;

import java.net.URI;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about on-line sources from which the dataset, specification, or
 * community profile name and extended metadata elements can be obtained.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="CI_OnlineResource", specification=ISO_19115)
public interface OnlineResource {
    /**
     * Location (address) for on-line access using a Uniform Resource Locator address or
     * similar addressing scheme.
     *
     * <div class="note"><b>Example:</b>
     * {@code "http://www.statkart.no/isotc211"}.
     * </div>
     *
     * @return location for on-line access using a Uniform Resource Locator address or similar scheme.
     */
    @UML(identifier="linkage", obligation=MANDATORY, specification=ISO_19115)
    URI getLinkage();

    /**
     * Connection protocol to be used.
     *
     * <div class="note"><b>Example:</b>
     * ftp, http get KVP, http POST, <i>etc</i>.
     * </div>
     *
     * @return connection protocol to be used, or {@code null}.
     */
    @UML(identifier="protocol", obligation=OPTIONAL, specification=ISO_19115)
    default String getProtocol() {
        return null;
    }

    /**
     * Name of an application profile that can be used with the online resource.
     *
     * @return application profile that can be used with the online resource, or {@code null}.
     */
    @UML(identifier="applicationProfile", obligation=OPTIONAL, specification=ISO_19115)
    default String getApplicationProfile() {
        return null;
    }

    /**
     * Name of the online resource.
     *
     * @return name of the online resource, or {@code null}.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getName() {
        return null;
    }

    /**
     * Detailed text description of what the online resource is/does.
     *
     * @return text description of what the online resource is/does, or {@code null}.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Code for function performed by the online resource.
     *
     * @return function performed by the online resource, or {@code null}.
     */
    @UML(identifier="function", obligation=OPTIONAL, specification=ISO_19115)
    default OnLineFunction getFunction() {
        return null;
    }

    /**
     * Request used to access the resource depending on the protocol.
     * This is used mainly for POST requests.
     * Example:
     *
     * {@snippet lang="xml" :
     * <GetFeature service="WFS" version="2.0.0"
     *             outputFormat="application/gml+xml;version=3.2"
     *             xmlns="(…snip…)">
     *     <Query typeNames="Roads"/>
     * </GetFeature>
     * }
     *
     * @return request used to access the resource, or {@code null}.
     *
     * @since 3.1
     */
    @UML(identifier="protocolRequest", obligation=OPTIONAL, specification=ISO_19115)
    default String getProtocolRequest() {
        return null;
    }
}
