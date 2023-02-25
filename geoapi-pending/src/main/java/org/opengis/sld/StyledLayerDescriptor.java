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
package org.opengis.sld;

import java.util.List;

import org.opengis.style.Description;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;

/**
 * The WMS-layers level of SLD is defined in the “StyledLayerDescriptor.xsd” XML-
 * Schema file and provides the “glue” between feature styling as defined by Symbology
 * Encoding and WMS layers. This level of definitions has been decoupled from the feature-
 * style and symbol definitions to make it convenient to perform feature styling in
 * environments other than inside of a WMS.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("StyledLayerDescriptor")
public interface StyledLayerDescriptor {

    /**
     * The Name element allows a symbolic name to be associated with a given SLD document.
     * This element is used with most “objects” defined by SE and SLD to allow them to be
     * referenced. Names must be unique in the context in which they are defined.
     */
    @XmlParameter("Name")
    String getName();

    /**
     * The Description element is also reused throughout SE and SLD and gives an informative
     * description of the “object” being defined. This information can be extracted and used for
     * such purposes as creating informal searchable metadata in catalogue systems. More
     * metadata fields may be added to this element in the future. The Name is not considered
     * to be part of a description since a name has a functional use that is more than just
     * descriptive.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * The UseSLDLibrary element provides the ability of handling external SLD documents
     * to be used in library-mode even when using XML-encoded POST requests with a WMS.
     * (The library mode can be accessed with the HTTP-GET method by supplying an SLD
     * CGI parameter in addition to LAYERS and STYLES CGI parameters.) This addition
     * merely exercises pre-existing functionality in WMS, but it does add the wrinkle of
     * making SLD-library references iterative and (syntactically) recursive. Successive
     * definitions are applied “on top of” previous ones to determine the scoping of overlapping
     * style definitions.
     */
    @XmlElement("UseSLDLibrary")
    List<? extends SLDLibrary> libraries();

    /**
     * The styled layers can correspond to either named layers (NamedLayer) or user-defined
     * layers (UserLayer), which are described in subsequent subclauses. There may be any
     * number of either type of styled layer, including zero, mixed in any order. The order that
     * the layer references appear in the SLD document will be the order that the styled layers
     * are rendered, with successive styled layers rendered on top of previous styled layers.
     */
    @XmlElement("NamedLayer,UserLayer")
    List<? extends Layer> layers();

    /**
     * The version attribute gives the SLD version of an SLD document, to
     * facilitate backward compatibility with static documents stored in various different
     * versions of the SLD specification. The string has the format “x.y.z”, the same as in other
     * OGC Implementation specifications. For example, an SLD document stored according to
     * this specification would have the version string “1.1.0”. The attribute is required.
     */
    @XmlParameter("Version")
    String getVersion();

    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    Object accept(SLDVisitor visitor, Object extraData);


}
