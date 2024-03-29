/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.style;

import java.util.List;

import org.opengis.annotation.XmlElement;

/**
 * A UserStyle is at the same semantic level as a NamedStyle used in the context of a WMS.
 * In a sense, a named style can be thought of as a reference to a hidden UserStyle
 * that is stored inside of a map server.
 *
 * <p>A portrayal catalog consists of a set of feature portrayal objects. Many may
 * exist for each feature type that may occur in the dataset. each feature object
 * has assigned a set of portrayal rules.</p>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 */
@XmlElement("UserStyle")
public interface Style {
    /**
     * Style name (machine readable, don't show to users)
     *
     * @return identification name of this style
     */
    @XmlElement("UserStyle")
    String getName();

    /**
     * Returns the description of this style.
     *
     * @return description with usual informations used for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * The IsDefault element identifies whether a style is the default style of a layer, for use in
     * SLD ‘library mode’ when rendering or for storing inside of a map server. IsDefault uses
     * “1” or “true” for true and “0” or “false” for false. The default value is “0”.
     */
    @XmlElement("IsDefault")
    boolean isDefault();

    /**
     * Returns a collection of feature type style.
     */
    @XmlElement("FeatureTypeStyle")
    List<? extends FeatureTypeStyle> featureTypeStyles();

    /**
     * Returns the default specification used if no rule return true.
     * This specification should not use any external functions.
     * This specification should use at least one spatial attribute.
     */
    Symbolizer getDefaultSpecification();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
